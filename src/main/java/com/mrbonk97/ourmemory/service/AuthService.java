package com.mrbonk97.ourmemory.service;

import com.mrbonk97.ourmemory.Exception.ErrorCode;
import com.mrbonk97.ourmemory.Exception.OurMemoryException;
import com.mrbonk97.ourmemory.model.MediaFile;
import com.mrbonk97.ourmemory.model.AuthProvider;
import com.mrbonk97.ourmemory.model.User;
import com.mrbonk97.ourmemory.repository.UserRepository;
import com.mrbonk97.ourmemory.utils.JwtTokenUtils;
import com.mrbonk97.ourmemory.utils.RandomNumberUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class AuthService {
    private static final String AUTH_CODE_PREFIX = "AuthCode ";
    private static final String RESET_CODE_PREFIX = "ResetPassword ";
    private final long authCodeExpirationMillis = 1800000L;
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final RedisService redisService;

    @Transactional
    public User createUser(String email, String name, String password, String phoneNumber, String profileImage) {
        userRepository.findByEmail(email).ifPresent(it -> {throw new OurMemoryException(ErrorCode.DUPLICATED_EMAIL);});

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setProfileImage(new MediaFile(profileImage));
        user.setAuthProvider(AuthProvider.local);
        return userRepository.save(user);
    }

    @Transactional
    public String loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) throw new OurMemoryException(ErrorCode.USER_NOT_FOUND);
        if(!Objects.equals(user.get().getPassword(), password)) throw new OurMemoryException(ErrorCode.INVALID_PASSWORD);
        return JwtTokenUtils.generateToken(user.get().getId());
    }

    @Async
    public CompletableFuture<String> sendAuthEmail(String toEmail) {
        String authCode = RandomNumberUtils.generateNumber();
        // redisService.setValues(AUTH_CODE_PREFIX + toEmail, authCode, Duration.ofMillis(authCodeExpirationMillis));

        System.out.println(toEmail);

        String text = "회원 가입을 위해 인증코드 확인 후 이메일 인증을 완료해 주세요.\n아래 인증코드를 복사하여 입력해 주시기 바랍니다.\n * 이메일 인증 코드: " + authCode;

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);
        msg.setSubject("우만시 인증 코드 발송");
        msg.setText(text);
        mailSender.send(msg);
        return CompletableFuture.completedFuture(authCode);
    }

    public boolean verifyCode(String email, String authCode, String type) {
        String redisAuthCode = redisService.getValues(AUTH_CODE_PREFIX + email);
        return redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode);
    }

    public void resetPassword(String toEmail) {
        userRepository.findByEmail(toEmail).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        String uuid = UUID.randomUUID().toString();
        redisService.setValues(RESET_CODE_PREFIX + uuid, toEmail, Duration.ofMillis(authCodeExpirationMillis));
        String url = "http://101.101.216.129:5173/reset-password&code=" + uuid;
        String text = "아래 주소로 접속하여 패스워드를 재설정 해주시기 바랍니다.\n * 주소: " + url;

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);
        msg.setSubject("우만시 패스워드 초기화 인증 코드 발송");
        msg.setText(text);
        mailSender.send(msg);
    }

    public boolean validateCodeExpiration(String code) {
        String value = redisService.getValues(RESET_CODE_PREFIX + code);
        return redisService.checkExistsValue(value);
    }

    public void changePassword(String code, String password) {
        String email = redisService.getValues(RESET_CODE_PREFIX+code);
        boolean isRequestValid = redisService.checkExistsValue(email);
        if(!isRequestValid) throw new OurMemoryException(ErrorCode.REQUEST_EXPIRED);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        user.setPassword(password);
        redisService.expireValues(RESET_CODE_PREFIX+code, 0);
        userRepository.saveAndFlush(user);
    }

    public void redisTest() {
        redisService.setValues(RESET_CODE_PREFIX + "asdasd", "hyunsuk1997@naver.com", Duration.ofMillis(authCodeExpirationMillis));
    }
}
