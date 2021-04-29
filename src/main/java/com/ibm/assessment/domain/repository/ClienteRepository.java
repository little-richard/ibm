package com.ibm.assessment.domain.repository;

import com.ibm.assessment.domain.cliente.model.CPF;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.domain.cliente.model.Endereco;

public interface ClienteRepository {
    String incluir(Cliente cliente);
    String alterar(Cliente cliente);
    String excluir(Long idCliente);
    Cliente localizar(String nome, CPF cpf, Endereco endereco);
    Cliente consultarPorUUID(String uuid);
}
