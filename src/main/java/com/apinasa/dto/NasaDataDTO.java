package com.apinasa.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NasaDataDTO {

    private Map<Integer, AtDTO> values = new LinkedHashMap<>();

    @JsonAnySetter
    public void setValues(String key, Object obj) {
        AtDTO at = null;
        String json = new Gson().toJson(obj);
        if (json.contains("AT") && json.contains("av")) at = new Gson().fromJson(json, AtDTO.class);

        if (at != null) values.put(Integer.parseInt(key), at);
    }

}
