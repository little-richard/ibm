package com.ibm.assessment.application.usecase.cliente;

import com.ibm.assessment.domain.cliente.exception.ClienteException;
import com.ibm.assessment.domain.cliente.model.Cliente;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

public interface AlterarClienteUseCase {

    void execute(Command command, OutputHandler handler);

    interface OutputHandler {

        void handle(Output output);

        void handleClienteDuplicado(Cliente cliente);

        void handleClienteNaoencontrado(Cliente cliente);
    }

    @Data
    @Builder
    class Command {
        private Cliente cliente;

        public Command(Cliente cliente) {
            if(Objects.isNull(cliente)){
                throw new ClienteException("Não é possivel atualizar um cliente null");
            }

            this.cliente = cliente;
        }

    }

    @Data
    @RequiredArgsConstructor
    @Builder
    class Output {

        private final String mensagem;

        private final String uuidCliente;

    }
}
