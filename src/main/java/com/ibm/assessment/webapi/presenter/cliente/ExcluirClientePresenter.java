package com.ibm.assessment.webapi.presenter.cliente;

import com.ibm.assessment.application.usecase.cliente.ExcluirClienteUseCase;
import com.ibm.assessment.application.usecase.cliente.IncluirClienteUseCase;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.webapi.common.*;
import com.ibm.assessment.webapi.response.IncluirAlterarClienteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class ExcluirClientePresenter extends BasePresenter implements ExcluirClienteUseCase.OutputHandler {

    @Override
    public void handle(ExcluirClienteUseCase.Output output) {

        final MessageStatus status = MessageStatus.withSuccess(output.getMensagem());

        viewModel = ResponseEntity.status(HttpStatus.CREATED).body(status);
    }

    @Override
    public void handleClienteNaoEncontrado(String uuid) {
        final String message = "Cliente não encontrado";
        final MessageStatus status = MessageStatus.withFailure(message);

        final Object response = new BaseResponse(status);

        viewModel = ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
