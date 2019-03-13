package no.oslomet.s315615springdockerclientproject.service;

import no.oslomet.s315615springdockerclientproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.getUserByEmail(email);
        return getUserDetails(user);
    }

    private UserDetails getUserDetails(User user){
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole()).build();
    }
}
