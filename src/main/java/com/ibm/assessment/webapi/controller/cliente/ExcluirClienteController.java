package com.ibm.assessment.webapi.controller.cliente;

import com.ibm.assessment.application.usecase.cliente.ExcluirClienteUseCase;
import com.ibm.assessment.application.usecase.cliente.IncluirClienteUseCase;
import com.ibm.assessment.domain.cliente.model.CPF;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.domain.cliente.model.Endereco;
import com.ibm.assessment.domain.common.exception.DomainException;
import com.ibm.assessment.webapi.common.ApiController;
import com.ibm.assessment.webapi.presenter.cliente.ExcluirClientePresenter;
import com.ibm.assessment.webapi.presenter.cliente.IncluirClientePresenter;
import com.ibm.assessment.webapi.request.cliente.IncluirClienteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@ApiController
@RequiredArgsConstructor
public class ExcluirClienteController {

    private final ExcluirClienteUseCase excluirClienteUseCase;

    @DeleteMapping("/cliente/{uuidCliente}")
    public ResponseEntity<?> excluirCliente(@PathVariable String uuidCliente) throws DomainException {
        ExcluirClientePresenter presenter = new ExcluirClientePresenter();
        final ExcluirClienteUseCase.Command command = ExcluirClienteUseCase.Command
                .builder()
                .uuidCliente(uuidCliente)
                .build();

        excluirClienteUseCase.execute(command, presenter);

        return presenter.getViewModel();
    }

}
