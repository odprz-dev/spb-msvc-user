package com.msvc.usuario.services;

import com.msvc.usuario.entities.Calificacion;
import com.msvc.usuario.entities.Hotel;
import com.msvc.usuario.entities.User;
import com.msvc.usuario.exceptions.BadRequestException;
import com.msvc.usuario.exceptions.ResourceNotFoundException;
import com.msvc.usuario.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private static final String QUALIFICATION_URI = "http://localhost:8083/api/v1/calificacion/usuario/";
    private static final String HOTEL_URI = "http://localhost:8082/api/v1/hotel/";

    private final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {


        User user = userRepository.findById(userId)
                .orElseThrow(() ->  new ResourceNotFoundException("El id " + userId + " no existe"));

        Calificacion[] userQualifications =
                restTemplate.getForObject(QUALIFICATION_URI + userId, Calificacion[].class);

        List<Calificacion> userQualificationsList = Arrays
                .stream(Objects.requireNonNull(userQualifications))
                .collect(Collectors.toList());

        logger.info("UserQualifications"+ userQualifications);

        List<Calificacion> mapUserQualifications = userQualificationsList.stream().map(calificacion -> {
            ResponseEntity<Hotel> hotelForEntity = restTemplate
                    .getForEntity(HOTEL_URI + calificacion.getHotelId(), Hotel.class);

            Hotel hotel = hotelForEntity.getBody();
            calificacion.setHotel(hotel);
            return calificacion;
        }).collect(Collectors.toList());

        user.setCalificaciones(mapUserQualifications);


        return user;

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
