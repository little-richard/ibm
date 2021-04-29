package com.ibm.assessment.webapi.controller.cliente;

import com.ibm.assessment.application.usecase.cliente.AlterarClienteUseCase;
import com.ibm.assessment.domain.cliente.model.CPF;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.domain.cliente.model.Endereco;
import com.ibm.assessment.domain.common.exception.DomainException;
import com.ibm.assessment.webapi.common.ApiController;
import com.ibm.assessment.webapi.presenter.cliente.AlterarClientePresenter;
import com.ibm.assessment.webapi.request.cliente.AlterarClienteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@ApiController
@RequiredArgsConstructor
public class AlterarClienteController {

    private final AlterarClienteUseCase alterarClienteUseCase;

    @PutMapping("/cliente")
    public ResponseEntity<?> alterarCliente(@RequestBody AlterarClienteRequest alterarClienteRequest) throws DomainException {
        AlterarClientePresenter presenter = new AlterarClientePresenter();
        final AlterarClienteUseCase.Command command = AlterarClienteUseCase.Command
                .builder()
                .cliente(toCliente(alterarClienteRequest))
                .build();

        alterarClienteUseCase.execute(command, presenter);

        return presenter.getViewModel();
    }


    private Cliente toCliente(AlterarClienteRequest incluirClienteRequest) throws DomainException{
        Endereco endereco = null;
        if(Objects.nonNull(incluirClienteRequest.getEndereco())){
            endereco = Endereco.builder()
                    .bairro(incluirClienteRequest.getEndereco().getBairro())
                    .estado(incluirClienteRequest.getEndereco().getEstado())
                    .cidade(incluirClienteRequest.getEndereco().getCidade())
                    .endereco(incluirClienteRequest.getEndereco().getEndereco())
                    .numero(incluirClienteRequest.getEndereco().getNumero())
                    .tipoEndereco(incluirClienteRequest.getEndereco().getTipoEnderecoEnum())
                    .build();
        }
        return Cliente.builder()
                .nome(incluirClienteRequest.getNome())
                .uuid(incluirClienteRequest.getUuid())
                .cpf(new CPF(incluirClienteRequest.getCpf()))
                .endereco(endereco)
                .build();
    }

}
