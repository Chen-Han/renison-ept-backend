package com.renison.model;

public enum ComponentType {
	HTML(Value.HTML), QUESTION(Value.QUESTION), MULTIPLE_CHOICE(Value.MULTIPLE_CHOICE), FREE_TEXT(
			Value.FREE_TEXT), TRUE_FALSE(Value.TRUE_FALSE), SHORT_ANSWER(Value.SHORT_ANSWER), VIDEO(Value.VIDEO);
	private String value;

	private ComponentType(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}

	public static class Value {
		public static final String HTML = "HTML";
		public static final String QUESTION = "QUESTION";
		public static final String MULTIPLE_CHOICE = "MULTIPLE_CHOICE";
		public static final String SHORT_ANSWER = "SHORT_ANSWER";
		public static final String FREE_TEXT = "FREE_TEXT";
		public static final String TRUE_FALSE = "TRUE_FALSE";
		public static final String VIDEO = "VIDEO";
	}
}
