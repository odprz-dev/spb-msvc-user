package com.msvc.usuario.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuarios")
public class User {
    @Id
    @Column(name="id")
    private String userId;

    @Column(name="nombre", length = 20)
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="informacion")
    private String information;

    @Transient
    private List<Calificacion> calificaciones = new ArrayList<>();

}
