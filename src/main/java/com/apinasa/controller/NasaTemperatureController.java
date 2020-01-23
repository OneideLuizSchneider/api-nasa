package com.apinasa.controller;

import com.apinasa.dto.NasaTemperatureDTO;
import com.apinasa.service.ApiNasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/nasa/")
public class NasaTemperatureController {

    @Autowired
    private ApiNasaService apiNasaService;

    @GetMapping("/temperature")
    public ResponseEntity<NasaTemperatureDTO> getAverageTemperature(@RequestParam(required = false) Integer sol) {
        return ResponseEntity.status(HttpStatus.OK).body(apiNasaService.getMarsAverageTemperature(sol));
    }


}
