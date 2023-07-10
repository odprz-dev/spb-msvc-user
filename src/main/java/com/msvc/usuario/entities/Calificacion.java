package com.msvc.usuario.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Calificacion {

    private String id;
    private String usuarioId;
    private String hotelId;
    private String calificacion;
    private String observacion;
    private Hotel hotel;

}
