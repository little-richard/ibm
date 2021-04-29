package com.ibm.assessment.webapi.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseResponse {
	
	private final BaseStatus status;
	
}
