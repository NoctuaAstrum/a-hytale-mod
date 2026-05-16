package com.github.NoctuaAstrum.ChemLib.core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonConverter {
    private static final ObjectMapper MAPPER;
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger("JsonConverter");
        MAPPER = new ObjectMapper();
        MAPPER.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        MAPPER.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    }

    public static class Deserializer<T> {

        /*private void readFile(String filepath, Class<T> type) throws IOException {
            try (FileReader reader = new FileReader(filepath)) {
                //Element temp = gson.fromJson(reader,Element.class);
                mapper.readValue(reader, type).reformat();

            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }*/
        private T from0(String input,Class<T> type) throws JsonProcessingException {
            return MAPPER.readValue(input, type);
        }

        public T from(String input,Class<T> type){
            try{
                return from0(input,type);
            }catch (JsonProcessingException e){
                LOGGER.log(Level.SEVERE,e.getMessage());
            }
            return  null;
        }
    }
    public static class Serializer<T> {

        public String from(T input){
            try {
                return MAPPER.writeValueAsString(input);
            } catch (JsonProcessingException e) {
                LOGGER.log(Level.SEVERE,e.getMessage());
            }
            return null;
        }

    }


}
