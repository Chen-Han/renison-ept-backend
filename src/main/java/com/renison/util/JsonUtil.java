package com.renison.util;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	public static ObjectNode newJsonObject() {
		JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;

		ObjectNode node = jsonNodeFactory.objectNode();
		return node;
	}

	public static ArrayNode newArrayNode() {
		JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;

		ArrayNode node = jsonNodeFactory.arrayNode();
		return node;
	}

	public static ObjectNode parseJsonObject(String json) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json.getBytes(), ObjectNode.class);
	}

	public static <T> T parseJson(String json, Class<T> classType)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json.getBytes(), classType);
	}

	public static ObjectNode merge(ObjectNode o1, ObjectNode o2) {
		ObjectNode copy = o1.deepCopy();
		copy.setAll(o2);
		return copy;
	}

	// note that we find mapper serialization does not comply to view
	public static ObjectNode serializePOJO(Object object, Class<?> jsonViewType) {
		SerializationConfig original = mapper.getSerializationConfig();
		mapper.setConfig(original.withView(jsonViewType));
		ObjectNode objectNode = mapper.valueToTree(object);
		mapper.setConfig(original);
		return objectNode;
	}

	public static <T extends Object> ArrayNode serializeToArray(Collection<T> objs, Class<?> jsonViewType) {
		SerializationConfig original = mapper.getSerializationConfig();
		mapper.setConfig(original.withView(jsonViewType));
		ArrayNode arrayNode = mapper.valueToTree(objs);
		mapper.setConfig(original);
		return arrayNode;
	}
}
