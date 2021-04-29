package com.ibm.assessment.application.usecase.cliente;

import com.ibm.assessment.domain.cliente.exception.ClienteException;
import com.ibm.assessment.domain.cliente.model.Cliente;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

public interface ExcluirClienteUseCase {

    void execute(Command command, OutputHandler handler);

    interface OutputHandler {

        void handle(Output output);

        void handleClienteNaoEncontrado(String uuid);

    }

    @Data
    @Builder
    class Command {

        private String uuidCliente;

        public Command(String uuid) {
            if(Objects.isNull(uuid)){
                throw new ClienteException("Não é possivel excluir um cliente com uuid null");
            }

            this.uuidCliente = uuid;
        }

    }

    @Data
    @RequiredArgsConstructor
    @Builder
    class Output {

        private final String mensagem;

    }
}
