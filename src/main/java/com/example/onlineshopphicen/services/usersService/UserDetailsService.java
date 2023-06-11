package com.example.onlineshopphicen.services.usersService;

import com.example.onlineshopphicen.model.Cart;
import com.example.onlineshopphicen.model.Product;
import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.model.Wishlist;
import com.example.onlineshopphicen.repositories.UserRepository;
import com.example.onlineshopphicen.security.UserDetailsImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Autowired
    public UserDetailsService(UserRepository userRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }

        return new UserDetailsImpl(user.get());
    }

    public User findById(Long id){
        return userRepository.findById(id).get();
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

    public void updateUser(Long id, User userUpdate){
        User user = userRepository.findById(id).get();
        user.setName(userUpdate.getName());
        user.setSurname(userUpdate.getSurname());
        user.setEmail(userUpdate.getEmail());
        user.setPhone(userUpdate.getPhone());
        userRepository.save(user);
    }
}
