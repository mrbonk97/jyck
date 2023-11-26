package com.mrbonk97.ourmemory.service;

import com.mrbonk97.ourmemory.Exception.ErrorCode;
import com.mrbonk97.ourmemory.Exception.OurMemoryException;
import com.mrbonk97.ourmemory.model.MediaFile;
import com.mrbonk97.ourmemory.model.Provider;
import com.mrbonk97.ourmemory.model.User;
import com.mrbonk97.ourmemory.repository.UserRepository;
import com.mrbonk97.ourmemory.utils.JwtTokenUtils;
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
    public User createUser(String email, String name, String password, String phoneNumber, String profileImage) {
        userRepository.findByEmail(email).ifPresent(it -> {throw new OurMemoryException(ErrorCode.DUPLICATED_EMAIL);});

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setProfileImage(new MediaFile(profileImage));
        user.setProvider(Provider.local);
        return userRepository.save(user);
    }

    @Transactional
    public String loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) throw new OurMemoryException(ErrorCode.USER_NOT_FOUND);
        if(!Objects.equals(user.get().getPassword(), password)) throw new OurMemoryException(ErrorCode.INVALID_PASSWORD);
        return JwtTokenUtils.generateToken(user.get().getId());
    }
}
