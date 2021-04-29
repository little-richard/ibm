package com.ibm.assessment.application.service.cliente;

import com.ibm.assessment.application.usecase.cliente.AlterarClienteUseCase;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.domain.cliente.model.Endereco;
import com.ibm.assessment.domain.repository.ClienteRepository;
import com.sun.istack.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class AlterarClienteService implements AlterarClienteUseCase {
    private final ClienteRepository clienteRepository;

    public AlterarClienteService(@NotNull @Lazy ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void execute(Command command, OutputHandler handler) {
        final Cliente cliente = clienteRepository.localizar(command.getCliente().getNome(), command.getCliente().getCpf(), command.getCliente().getEndereco());

        if(Objects.nonNull(cliente) && !cliente.getUuid().equals(command.getCliente().getUuid())){
            handler.handleClienteDuplicado(cliente);
            return;
        }

        Cliente clienteAtualizar = clienteRepository.consultarPorUUID(command.getCliente().getUuid());

        if(Objects.isNull(clienteAtualizar)){
            handler.handleClienteNaoencontrado(command.getCliente());
            return;
        }

        this.persistirDadosCliente(clienteAtualizar, command.getCliente());

        final String uuid = this.clienteRepository.alterar(clienteAtualizar);

        final Output output = buildOutput(uuid);

        handler.handle(output);

    }

    private Output buildOutput(String uuid){
        return Output.builder()
                .mensagem("Cliente atualizado com sucesso.")
                .uuidCliente(uuid)
                .build();
    }

    public void persistirDadosCliente(Cliente clienteAtualizar, Cliente clienteTemp){
        clienteAtualizar.setNome(Objects.nonNull(clienteTemp.getNome()) ? clienteTemp.getNome() : clienteAtualizar.getNome());
        clienteAtualizar.setCPF(Objects.nonNull(clienteTemp.getCpf()) ? clienteTemp.getCpf() : clienteAtualizar.getCpf());

        if(Objects.nonNull(clienteTemp.getEndereco())){
            Endereco endereco = clienteTemp.getEndereco();
            if(Objects.nonNull(clienteAtualizar.getEndereco())){
                clienteAtualizar.getEndereco().setEndereco(endereco.getEndereco());
                clienteAtualizar.getEndereco().setTipoEndereco(Objects.nonNull(endereco.getTipoEndereco()) ? endereco.getTipoEndereco() : clienteAtualizar.getEndereco().getTipoEndereco());
                clienteAtualizar.getEndereco().setBairro(Objects.nonNull(endereco.getBairro()) ? endereco.getBairro() : clienteAtualizar.getEndereco().getBairro());
                clienteAtualizar.getEndereco().setCidade(endereco.getCidade());
                clienteAtualizar.getEndereco().setEstado(endereco.getEstado());
                clienteAtualizar.getEndereco().setNumero(Objects.nonNull(endereco.getNumero()) ? endereco.getNumero() : clienteAtualizar.getEndereco().getNumero());
            } else {
                clienteAtualizar.setEndereco(endereco);
            }
        }

        clienteAtualizar.setNome(Objects.nonNull(clienteTemp.getNome()) ? clienteTemp.getNome() : clienteAtualizar.getNome());
    }
}
