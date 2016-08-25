package com.renison.jackson;

public class View {
	public static interface Public {

	}

	public static interface AdminSummary extends Public {

	}

	public static interface Admin extends Student { // admin has access to see
													// what students can see
	}

	public static interface Student extends Public {
	}
}
