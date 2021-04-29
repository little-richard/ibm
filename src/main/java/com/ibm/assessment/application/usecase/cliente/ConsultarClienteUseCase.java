package com.ibm.assessment.application.usecase.cliente;

import com.ibm.assessment.domain.cliente.exception.ClienteException;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.webapi.request.cliente.ConsultarClienteRequest;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

public interface ConsultarClienteUseCase {

    void execute(Command command, OutputHandler handler);

    interface OutputHandler {

        void handle(Output output);

        void handleClienteNaoencontrado();
    }

    @Data
    @Builder
    class Command {
        private ConsultarClienteRequest consultarClienteRequest;

        public Command(ConsultarClienteRequest consultarClienteRequest) {
            if(Objects.isNull(consultarClienteRequest)){
                throw new ClienteException("Não é possivel consultar um cliente null");
            }

            this.consultarClienteRequest = consultarClienteRequest;
        }

    }

    @Data
    @RequiredArgsConstructor
    @Builder
    class Output {

        private final Cliente cliente;

    }
}
