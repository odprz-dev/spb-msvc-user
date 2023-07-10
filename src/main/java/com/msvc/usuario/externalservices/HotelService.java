package com.msvc.usuario.externalservices;

import com.msvc.usuario.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/api/v1//hotel/{id}")
    public Hotel getHotelById(@PathVariable String id);

}
