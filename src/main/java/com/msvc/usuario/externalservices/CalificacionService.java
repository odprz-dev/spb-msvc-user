package com.msvc.usuario.externalservices;

import com.msvc.usuario.entities.Calificacion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "QUALIFICATION-SERVICE")
public interface CalificacionService {

    @GetMapping("/api/v1//calificacion/usuario/{id}")
    public List<Calificacion> getCalificationByUserId(@PathVariable String id);

}
