package com.renison.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static ObjectNode newJsonObject() {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;

        ObjectNode node = jsonNodeFactory.objectNode();
        return node;
    }

    public static ObjectNode parseJsonObject(String json) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(json.getBytes(), ObjectNode.class);
    }

    public static <T> T parseJson(String json, Class<T> classType) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(json.getBytes(), classType);
    }
}
