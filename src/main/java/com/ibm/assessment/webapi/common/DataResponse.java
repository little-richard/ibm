package com.ibm.assessment.webapi.common;

import lombok.Getter;

@Getter
public class DataResponse<T> extends BaseResponse {

	private final T data;

	public DataResponse(BaseStatus status, T data) {
		super(status);
		this.data = data;
	}

}
