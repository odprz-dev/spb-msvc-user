package com.msvc.usuario.services;

import com.msvc.usuario.entities.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    User getUserById(String usrId);
    User postUser(User user);
    User updateUser(String userId, User user);
    void deleteUser(String userId);


}
