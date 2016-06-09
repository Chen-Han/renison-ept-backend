package com.renison.util;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.renison.jackson.View;
import com.renison.model.Student;

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

	@JsonView(View.Report.class)
	public static ObjectNode parsePOJO(Object object, Class<?> jsonViewType) {
		SerializationConfig original = mapper.getSerializationConfig();
		mapper.setConfig(original.withView(jsonViewType));
		ObjectNode objectNode = mapper.valueToTree(object);
		mapper.setConfig(original);
		return objectNode;
	}

	public static void main(String[] args) {
		Student student = new Student();
		student.setFirstName("Han");
		System.out.println(parsePOJO(student, View.Report.class).toString());
	}
}
