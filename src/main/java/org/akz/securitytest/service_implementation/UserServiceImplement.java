package org.akz.securitytest.service_implementation;

import lombok.RequiredArgsConstructor;
import org.akz.securitytest.dto.UserDto;
import org.akz.securitytest.entity.User;
import org.akz.securitytest.repository.UserRepository;
import org.akz.securitytest.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void createUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(userDto.getRole())
                .build();

        userRepository.save(user);
    }
}
