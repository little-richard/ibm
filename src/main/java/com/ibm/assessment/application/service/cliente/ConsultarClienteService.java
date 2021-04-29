package com.ibm.assessment.application.service.cliente;

import com.ibm.assessment.application.usecase.cliente.ConsultarClienteUseCase;
import com.ibm.assessment.application.usecase.cliente.IncluirClienteUseCase;
import com.ibm.assessment.domain.cliente.model.CPF;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.domain.cliente.model.Endereco;
import com.ibm.assessment.domain.repository.ClienteRepository;
import com.ibm.assessment.webapi.response.ConsultarClienteResponse;
import com.sun.istack.NotNull;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class ConsultarClienteService implements ConsultarClienteUseCase {

    private final ClienteRepository clienteRepository;

    public ConsultarClienteService(@NotNull @Lazy ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void execute(Command command, OutputHandler handler) {
        CPF cpf = Strings.isNotBlank(command.getConsultarClienteRequest().getCpf()) ? new CPF(command.getConsultarClienteRequest().getCpf()) : null;
        Endereco endereco = null;

        if(Objects.nonNull(command.getConsultarClienteRequest().getEndereco())
                && Strings.isNotBlank(command.getConsultarClienteRequest().getEndereco().getEndereco())
                && Strings.isNotBlank(command.getConsultarClienteRequest().getEndereco().getCidade())
                && Strings.isNotBlank(command.getConsultarClienteRequest().getEndereco().getEstado())){

            endereco = Endereco.builder()
                    .bairro(command.getConsultarClienteRequest().getEndereco().getBairro())
                    .cidade(command.getConsultarClienteRequest().getEndereco().getCidade())
                    .estado(command.getConsultarClienteRequest().getEndereco().getEstado())
                    .endereco(command.getConsultarClienteRequest().getEndereco().getEndereco())
                    .numero(command.getConsultarClienteRequest().getEndereco().getNumero())
                    .build();

        }

        final Cliente cliente = clienteRepository.localizar(command.getConsultarClienteRequest().getNome(), cpf, endereco);

        if(Objects.isNull(cliente)){
            handler.handleClienteNaoencontrado();
            return;
        }

        final Output output = buildOutput(cliente);

        handler.handle(output);

    }

    private Output buildOutput(Cliente cliente) {
        return Output.builder()
                .cliente(cliente)
                .build();
    }
}
