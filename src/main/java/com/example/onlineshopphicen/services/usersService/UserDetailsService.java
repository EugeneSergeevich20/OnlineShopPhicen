package com.example.onlineshopphicen.services.usersService;

import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.repositories.UserRepository;
import com.example.onlineshopphicen.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }

        return new UserDetailsImpl(user.get());
    }

    public User getAuthUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDerails;

        if (authentication instanceof AnonymousAuthenticationToken)
            return null;
        else
            userDerails = (UserDetailsImpl) authentication.getPrincipal();

        /*if (authentication.getPrincipal() == "anonymousUser")
            return null;
        else
            userDerails = (UserDetailsImpl) authentication.getPrincipal();*/
        return userDerails.getUser();
    }
}
