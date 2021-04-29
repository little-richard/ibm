package com.ibm.assessment.webapi.controller.cliente;

import com.ibm.assessment.application.usecase.cliente.ConsultarClienteUseCase;
import com.ibm.assessment.application.usecase.cliente.IncluirClienteUseCase;
import com.ibm.assessment.domain.cliente.model.CPF;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.domain.cliente.model.Endereco;
import com.ibm.assessment.domain.common.UtilTexto;
import com.ibm.assessment.domain.common.exception.DomainException;
import com.ibm.assessment.webapi.common.ApiController;
import com.ibm.assessment.webapi.presenter.cliente.ConsultarClientePresenter;
import com.ibm.assessment.webapi.presenter.cliente.IncluirClientePresenter;
import com.ibm.assessment.webapi.request.cliente.ConsultarClienteRequest;
import com.ibm.assessment.webapi.request.cliente.IncluirClienteRequest;
import com.ibm.assessment.webapi.response.ConsultarClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@ApiController
@RequiredArgsConstructor
public class ConsultarClienteController {

    private final ConsultarClienteUseCase consultarClienteUseCase;

    @GetMapping("/cliente")
    public ResponseEntity<?> consultarCliente(@RequestParam String nome,
                                              @RequestParam String cpf,
                                              @RequestParam(required = false) String endereco,
                                              @RequestParam(required = false) String bairro,
                                              @RequestParam(required = false) String cidade,
                                              @RequestParam(required = false) String estado) throws DomainException {
        ConsultarClientePresenter presenter = new ConsultarClientePresenter();
        final ConsultarClienteUseCase.Command command = ConsultarClienteUseCase.Command
                .builder()
                .consultarClienteRequest(toRequest(nome, UtilTexto.formatarSomenteNumeros(cpf), endereco, bairro, cidade, estado))
                .build();

        consultarClienteUseCase.execute(command, presenter);

        return presenter.getViewModel();
    }


    private ConsultarClienteRequest toRequest(String nome,
                              String cpf,
                              String endereco,
                              String bairro,
                              String cidade,
                              String estado) throws DomainException{
        ConsultarClienteRequest.EnderecoRequest enderecoReq = ConsultarClienteRequest.EnderecoRequest
                .builder()
                .endereco(endereco)
                .bairro(bairro)
                .cidade(cidade)
                .estado(estado)
                .build();

        return ConsultarClienteRequest.builder()
                .cpf(cpf)
                .nome(nome)
                .endereco(enderecoReq)
                .build();
    }

}
