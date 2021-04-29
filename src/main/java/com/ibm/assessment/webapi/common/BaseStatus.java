package com.ibm.assessment.webapi.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseStatus {

	private final boolean success;

	public static BaseStatus successful() {
		return new BaseStatus(true);
	}

	public static BaseStatus failure() {
		return new BaseStatus(false);
	}

}
