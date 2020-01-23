package com.apinasa;

import com.apinasa.dto.NasaDataDTO;
import com.apinasa.dto.NasaTemperatureDTO;
import com.apinasa.service.ApiNasaService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ApiNasaServiceTests {

    @SpyBean
    private ApiNasaService apiNasaService;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationProperties appProperties;

    private NasaDataDTO nasaDataDTO = new NasaDataDTO();

    @BeforeEach
    public void init(){
        mockData();
        initMockApiData();
    }

    private void mockData() {
        String json = "{\n" +
                "    \"AT\": {\n" +
                "      \"av\": -50.007, \n" +
                "      \"ct\": 329504, \n" +
                "      \"mn\": -99.523, \n" +
                "      \"mx\": -16.838\n" +
                "    }\n" +
                "}";

        Object obj = new Gson().fromJson(json, Object.class);

        nasaDataDTO.setValues(
                "405",
                obj
        );
    }

    private void initMockApiData() {
        ResponseEntity<NasaDataDTO> responseEntity = new ResponseEntity<NasaDataDTO>(nasaDataDTO, HttpStatus.OK);
        when(restTemplate.getForEntity(
                appProperties.getNasaApiUrl(),
                NasaDataDTO.class
        )).thenReturn(responseEntity);
    }

    @Test
    public void testgetMarsAverageTemperature() {
        NasaTemperatureDTO nt = apiNasaService.getMarsAverageTemperature(405);
        assertTrue(nt.getAverageTemperature() == -50.007);
    }

    @Test
    void contextLoads() {
        assertThat(apiNasaService).isNotNull();
        assertThat(restTemplate).isNotNull();
        assertThat(appProperties).isNotNull();
    }

}