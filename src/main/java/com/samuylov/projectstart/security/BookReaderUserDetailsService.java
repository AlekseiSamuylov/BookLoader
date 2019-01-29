package com.samuylov.projectstart.security;

import com.samuylov.projectstart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BookReaderUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getUserByName(username).map(user ->
                User.withUsername(username).passwordEncoder(passwordEncoder::encode)
                .password(user.getPassword())
                .authorities(user.getRole().toString())
                .build()
        ).orElseThrow(() -> new UsernameNotFoundException("No user with name '" + username + "'"));
    }
}
