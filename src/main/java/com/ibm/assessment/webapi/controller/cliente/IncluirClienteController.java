package com.ibm.assessment.webapi.controller.cliente;

import com.ibm.assessment.application.usecase.cliente.IncluirClienteUseCase;
import com.ibm.assessment.domain.cliente.model.CPF;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.domain.cliente.model.Endereco;
import com.ibm.assessment.domain.common.exception.DomainException;
import com.ibm.assessment.webapi.common.ApiController;
import com.ibm.assessment.webapi.presenter.cliente.IncluirClientePresenter;
import com.ibm.assessment.webapi.request.cliente.IncluirClienteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;
import java.util.UUID;

@ApiController
@RequiredArgsConstructor
public class IncluirClienteController {

    private final IncluirClienteUseCase incluirClienteUseCase;

    @PostMapping("/cliente")
    public ResponseEntity<?> incluirCliente(@RequestBody IncluirClienteRequest incluirClienteRequest) throws DomainException {
        IncluirClientePresenter presenter = new IncluirClientePresenter();
        final IncluirClienteUseCase.Command command = IncluirClienteUseCase.Command
                .builder()
                .cliente(toCliente(incluirClienteRequest))
                .build();

        incluirClienteUseCase.execute(command, presenter);

        return presenter.getViewModel();
    }


    private Cliente toCliente(IncluirClienteRequest incluirClienteRequest) throws DomainException{
        Endereco endereco = null;
        if(Objects.nonNull(incluirClienteRequest.getEndereco())){
            endereco = Endereco.builder()
                    .bairro(incluirClienteRequest.getEndereco().getBairro())
                    .estado(incluirClienteRequest.getEndereco().getEstado())
                    .cidade(incluirClienteRequest.getEndereco().getCidade())
                    .endereco(incluirClienteRequest.getEndereco().getEndereco())
                    .numero(incluirClienteRequest.getEndereco().getNumero())
                    .tipoEndereco(incluirClienteRequest.getEndereco().getTipoEnderecoEnum())
                    .uuid(UUID.randomUUID().toString())
                    .build();
        }
        return Cliente.builder()
                .nome(incluirClienteRequest.getNome())
                .uuid(UUID.randomUUID().toString())
                .cpf(new CPF(incluirClienteRequest.getCpf()))
                .endereco(endereco)
                .build();
    }

}
