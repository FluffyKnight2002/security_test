package org.akz.securitytest.config;

import lombok.RequiredArgsConstructor;
import org.akz.securitytest.entity.User;
import org.akz.securitytest.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user != null) {
            return new MyUserDetails(user);
        }

        throw new UsernameNotFoundException("User Not Found");
    }
}
