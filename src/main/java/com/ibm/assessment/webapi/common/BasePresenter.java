package com.ibm.assessment.webapi.common;

import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public abstract class BasePresenter {

	protected ResponseEntity<?> viewModel;

}
