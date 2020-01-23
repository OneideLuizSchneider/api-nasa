package com.apinasa.service;

import com.apinasa.ApplicationProperties;
import com.apinasa.dto.AtDTO;
import com.apinasa.dto.NasaDataDTO;
import com.apinasa.dto.NasaTemperatureDTO;
import org.slf4j.Logger;

import com.apinasa.helpers.Loggable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ApiNasaService {

    @Loggable
    private static Logger log;

    @Autowired
    private ApplicationProperties appProperties;

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultEmpty", commandProperties = { @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000") })
    public NasaTemperatureDTO getMarsAverageTemperature(Integer sol) {
        log.info("Starting Nasa API request");
        NasaDataDTO data = getNasaAverageTempeture();
        log.info("Finish Nasa API request");

        log.info("Starting Average");
        NasaTemperatureDTO result = getAverageTemperature(data, sol);
        log.info("Finish Average");

        return result;
    }

    private NasaTemperatureDTO getAverageTemperature(NasaDataDTO data, Integer sol) {
        if (sol == null) return getAverageTemperature(data);

        AtDTO value = data.getValues().get(sol);
        return (value != null) ? new NasaTemperatureDTO(value.getAT().getAv()) : defaultEmpty(0);
    }

    private NasaTemperatureDTO getAverageTemperature(NasaDataDTO data) {
        Double sum = 0.0;
        int count = 0;
        for (Map.Entry<Integer, AtDTO> entry : data.getValues().entrySet()) {
            sum += entry.getValue().getAT().getAv();
            count++;
        }

        return new NasaTemperatureDTO(sum / count);
    }

    private NasaDataDTO getNasaAverageTempeture() {
        return restTemplate.getForEntity(
                appProperties.getNasaApiUrl(),
                NasaDataDTO.class
        ).getBody();
    }

    public NasaTemperatureDTO defaultEmpty(Integer sol) {
        return new NasaTemperatureDTO();
    }

}
