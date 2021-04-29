package com.ibm.assessment.application.service.cliente;

import com.ibm.assessment.application.usecase.cliente.IncluirClienteUseCase;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.domain.repository.ClienteRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class IncluirClienteService implements IncluirClienteUseCase {

    private final ClienteRepository clienteRepository;

    public IncluirClienteService(@NotNull @Lazy ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void execute(Command command, OutputHandler handler) {
        final Cliente cliente = clienteRepository.localizar(command.getCliente().getNome(), command.getCliente().getCpf(), command.getCliente().getEndereco());

        if(Objects.nonNull(cliente)){
            handler.handleClienteDuplicado(cliente);
            return;
        }

        final String uuidCliente = this.clienteRepository.incluir(command.getCliente());

        final Output output = buildOutput(uuidCliente);

        handler.handle(output);

    }

    private Output buildOutput(String uuidCliente) {
        return Output.builder()
                .mensagemInclusao("Cliente adicionado com sucesso.")
                .uuidCliente(uuidCliente)
                .build();
    }
}
