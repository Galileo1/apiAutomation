package co.nz.westpac.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Serializer {

    public Serializer() {}

    public ObjectMapper mapper = new ObjectMapper();

    public <T> String objectToJson(T obj) {
        String jsonString = "";

        try {
            jsonString = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public <T> T jsonToObject(File jsonString, Class<T> clazz) {
        T obj = null;
        try {
            obj = mapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
}

