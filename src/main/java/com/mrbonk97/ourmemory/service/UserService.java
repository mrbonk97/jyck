package com.mrbonk97.ourmemory.service;

import com.mrbonk97.ourmemory.model.User;
import com.mrbonk97.ourmemory.repository.UserRepository;
import com.mrbonk97.ourmemory.utils.MediaFileUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 계정은 이미 삭제 됨"));
        userRepository.deleteById(id);
    }

    // TODO : 예외
    public User getUser(Long userId) { return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("asd")); }

    public User updateUser(Long userId, String email, String password, String name, MultipartFile profileImage) throws IOException {
        System.out.println(profileImage + "외없음");
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) throw new RuntimeException("유저 없음");
        user.get().setEmail(email);
        user.get().setPassword(password);
        user.get().setName(name);
        if(profileImage != null) user.get().setProfileImage(MediaFileUtils.convertMultiPartFile(profileImage));
        return userRepository.saveAndFlush(user.get());
    }
}
