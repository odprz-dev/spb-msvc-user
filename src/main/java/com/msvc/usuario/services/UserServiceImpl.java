package com.msvc.usuario.services;

import com.msvc.usuario.entities.User;
import com.msvc.usuario.exceptions.BadRequestException;
import com.msvc.usuario.exceptions.ResourceNotFoundException;
import com.msvc.usuario.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->  new ResourceNotFoundException("El id " + userId + " no existe"));
    }

    @Override
    public User postUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String userId, User userUpdate) {

        if(!userId.equals(userUpdate.getUserId())) {
            throw new BadRequestException("El id " + userId + " no coincide con el id del proporcionado");
        }

        User user = getUserById(userId);
        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());
        user.setInformation(userUpdate.getInformation());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId)
                .orElse(null);
        if (user != null) {
            userRepository.delete(user);
        } else  {
            throw new ResourceNotFoundException();
        }
    }
}
