package com.example.onlineshopphicen.services.usersService;

import com.example.onlineshopphicen.model.Role;
import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final UserRepository userRepository;

    @Autowired
    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /***
     * Вывод все пользователей, доступно для админа
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')") //этот метод доступен только для админ
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User findUserById(Long id){
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void updateRole(Long id, User userUpdate) {
        userRepository.save(userUpdate);
    }

}
