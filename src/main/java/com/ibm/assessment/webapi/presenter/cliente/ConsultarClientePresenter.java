package com.ibm.assessment.webapi.presenter.cliente;

import com.ibm.assessment.application.usecase.cliente.ConsultarClienteUseCase;
import com.ibm.assessment.application.usecase.cliente.IncluirClienteUseCase;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.webapi.common.*;
import com.ibm.assessment.webapi.response.ConsultarClienteResponse;
import com.ibm.assessment.webapi.response.IncluirAlterarClienteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class ConsultarClientePresenter extends BasePresenter implements ConsultarClienteUseCase.OutputHandler {

    @Override
    public void handle(ConsultarClienteUseCase.Output output) {
        final BaseStatus status = BaseStatus.successful();

        Cliente clienteResponse = output.getCliente();

        ConsultarClienteResponse.EnderecoResponse enderecoResponse = null;


        if(Objects.nonNull(output.getCliente().getEndereco())){
            enderecoResponse = ConsultarClienteResponse.EnderecoResponse.builder()
                    .bairro(clienteResponse.getEndereco().getBairro())
                    .endereco(clienteResponse.getEndereco().getEndereco())
                    .numero(clienteResponse.getEndereco().getNumero())
                    .cidade(clienteResponse.getEndereco().getCidade())
                    .estado(clienteResponse.getEndereco().getEstado())
                    .tipoEnderecoEnum(clienteResponse.getEndereco().getTipoEndereco())
                    .build();
        }

        ConsultarClienteResponse consultarClienteResponse = ConsultarClienteResponse.builder()
                .uuid(clienteResponse.getUuid())
                .cpf(clienteResponse.getCpf().getNumero())
                .nome(clienteResponse.getNome())
                .endereco(enderecoResponse)
                .build();

        final Object response = new DataResponse<>(status, consultarClienteResponse);

        viewModel = ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @Override
    public void handleClienteNaoencontrado() {
        final String message = "Cliente não encontrado";
        final MessageStatus status = MessageStatus.withFailure(message);

        final Object response = new BaseResponse(status);

        viewModel = ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }
}
