package com.renison;

import org.springframework.beans.factory.annotation.Value;

//@Component
public class OtherConfig {
	@Value("${dispatchOptionsRequest}")
	private boolean dispatchOptionsRequest = true;
}
