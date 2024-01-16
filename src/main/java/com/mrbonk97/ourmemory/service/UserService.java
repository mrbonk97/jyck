package com.mrbonk97.ourmemory.service;

import com.mrbonk97.ourmemory.Exception.ErrorCode;
import com.mrbonk97.ourmemory.Exception.OurMemoryException;
import com.mrbonk97.ourmemory.model.MediaFile;
import com.mrbonk97.ourmemory.model.User;
import com.mrbonk97.ourmemory.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        userRepository.deleteById(id);
    }

    public User getUser(Long userId) { return userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND)); }


    public User updateUser(Long userId, String email, String name, String password, String phoneNumber, String profileImage) {
        User user = userRepository.findById(userId).orElseThrow( () -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        user.setEmail(email);
        user.setName(name);
        if(password != null) user.setPassword(password);
        user.setProfileImage(new MediaFile(profileImage));
        user.setPhoneNumber(phoneNumber);
        
        return userRepository.saveAndFlush(user);
    }
}
