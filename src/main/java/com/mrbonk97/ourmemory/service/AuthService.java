package com.mrbonk97.ourmemory.service;

import com.mrbonk97.ourmemory.model.User;
import com.mrbonk97.ourmemory.repository.UserRepository;
import com.mrbonk97.ourmemory.utils.JwtTokenUtils;
import com.mrbonk97.ourmemory.utils.MediaFileUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(String email, String password, String name, MultipartFile profileImage) throws IOException {
        userRepository.findByEmail(email).ifPresent(it -> {throw new RuntimeException("아이디 중복됨");});
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        if(profileImage != null) user.setProfileImage(MediaFileUtils.convertMultiPartFile(profileImage));
        return userRepository.save(user);
    }

    @Transactional
    public String loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) throw new RuntimeException("아이디 없음");
        if(!Objects.equals(user.get().getPassword(), password)) throw new RuntimeException("패스워드 틀림");
        return JwtTokenUtils.generateToken(user.get().getId());
    }

}
