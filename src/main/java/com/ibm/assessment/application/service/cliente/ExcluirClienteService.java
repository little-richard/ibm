package com.ibm.assessment.application.service.cliente;

import com.ibm.assessment.application.usecase.cliente.ExcluirClienteUseCase;
import com.ibm.assessment.application.usecase.cliente.IncluirClienteUseCase;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.domain.repository.ClienteRepository;
import com.sun.istack.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class ExcluirClienteService implements ExcluirClienteUseCase {

    private final ClienteRepository clienteRepository;

    public ExcluirClienteService(@NotNull @Lazy ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void execute(Command command, OutputHandler handler) {
        final Cliente cliente = clienteRepository.consultarPorUUID(command.getUuidCliente());

        if(Objects.isNull(cliente)){
            handler.handleClienteNaoEncontrado(command.getUuidCliente());
            return;
        }

        String mensagem = this.clienteRepository.excluir(cliente.getId());

        final Output output = buildOutput(mensagem);

        handler.handle(output);

    }

    private Output buildOutput(String mensagem) {
        return Output.builder()
                .mensagem(mensagem)
                .build();
    }
}
