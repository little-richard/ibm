package com.ibm.assessment.infra.orm.repository;

import com.ibm.assessment.domain.cliente.model.CPF;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.domain.cliente.model.Endereco;
import com.ibm.assessment.domain.repository.ClienteRepository;
import com.ibm.assessment.infra.orm.entity.cliente.ClienteEntity;
import com.sun.istack.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.ibm.assessment.infra.orm.entity.cliente.mapper.ClienteMapper.*;

@Repository
public class ClienteJpaRepositoryImpl implements ClienteRepository {

    private final ClienteJpaRepository clienteJpaRepository;

    public ClienteJpaRepositoryImpl(@NotNull @Lazy ClienteJpaRepository clienteJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
    }

    @Override
    public String incluir(Cliente cliente) {
        final ClienteEntity response = clienteJpaRepository.save(Objects.requireNonNull(mapToClienteEntity(cliente)));
        return Objects.nonNull(response.getUuid()) ? response.getUuid() : null;
    }

    @Override
    public String alterar(Cliente cliente) {
        final ClienteEntity response = clienteJpaRepository.save(Objects.requireNonNull(mapToClienteEntity(cliente)));
        return Objects.nonNull(response.getId()) ? response.getUuid() : null;
    }

    @Override
    public String excluir(Long idCliente) {
        clienteJpaRepository.deleteById(idCliente);
        return "Cliente excluido com sucesso.";
    }

    @Override
    public Cliente localizar(String nome, CPF cpf, Endereco endereco) {
        ClienteEntity clienteBusca;

        if(Objects.nonNull(endereco)){
            clienteBusca = clienteJpaRepository.consultarClientePorNomeECpfEEndereco("%" + nome + "%", cpf.getNumero(), endereco.getEndereco(), endereco.getBairro(), endereco.getNumero(), endereco.getCidade(), endereco.getEstado());
        }else{
            clienteBusca = clienteJpaRepository.findByNomeLikeAndCpf("%" + nome + "%", Objects.nonNull(cpf.getNumero()) ? cpf.getNumero() : null);
        }

        return mapToCliente(clienteBusca);
    }

    @Override
    public Cliente consultarPorUUID(String uuid) {
        ClienteEntity entidade = this.clienteJpaRepository.findByUuid(uuid);
        return mapToCliente(entidade);
    }

}
