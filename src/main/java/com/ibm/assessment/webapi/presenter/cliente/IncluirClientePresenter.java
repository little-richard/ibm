package com.ibm.assessment.webapi.presenter.cliente;

import com.ibm.assessment.application.usecase.cliente.IncluirClienteUseCase;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.webapi.common.*;
import com.ibm.assessment.webapi.response.IncluirAlterarClienteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class IncluirClientePresenter extends BasePresenter implements IncluirClienteUseCase.OutputHandler {

    @Override
    public void handle(IncluirClienteUseCase.Output output) {
        final BaseStatus status = BaseStatus.successful();

        final Object response = new DataResponse<>(status, IncluirAlterarClienteResponse.builder().mensagem(output.getMensagemInclusao()).uuidCliente(output.getUuidCliente()).build());

        viewModel = ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public void handleClienteDuplicado(Cliente cliente) {
        final String message = String.format("O cliente %s com CPF: %s já está cadastrado no sistema.", cliente.getNome(), cliente.getCpf().getNumero());
        final MessageStatus status = MessageStatus.withFailure(message);

        final Object response = new BaseResponse(status);

        viewModel = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
