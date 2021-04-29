package com.ibm.assessment.infra.orm.entity.cliente.mapper;

import com.ibm.assessment.domain.cliente.model.CPF;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.domain.cliente.model.Endereco;
import com.ibm.assessment.infra.orm.entity.cliente.ClienteEntity;
import com.ibm.assessment.infra.orm.entity.cliente.EnderecoEntity;

import java.util.Objects;

public class ClienteMapper {

    public static ClienteEntity mapToClienteEntity(Cliente cliente){
        if(Objects.isNull(cliente)){
            return null;
        }else{
            return ClienteEntity.builder()
                    .id(cliente.getId())
                    .uuid(cliente.getUuid())
                    .cpf(cliente.getCpf().getNumero())
                    .endereco(Objects.nonNull(cliente.getEndereco()) ? mapToEnderecoEntity(cliente.getEndereco()) : null)
                    .nome(cliente.getNome())
                    .build();
        }
    }

    public static EnderecoEntity mapToEnderecoEntity(Endereco endereco){
        if(Objects.isNull(endereco)){
            return null;
        }else {
            return EnderecoEntity.builder()
                    .id(endereco.getId())
                    .uuid(endereco.getUuid())
                    .bairro(endereco.getBairro())
                    .cidade(endereco.getCidade())
                    .endereco(endereco.getEndereco())
                    .tipoEndereco(endereco.getTipoEndereco())
                    .estado(endereco.getEstado())
                    .numero(endereco.getNumero())
                    .build();
        }
    }

    public static Endereco mapToEndereco(EnderecoEntity enderecoEntity){
        if(Objects.isNull(enderecoEntity)){
            return null;
        }else {
            return Endereco.builder()
                    .id(enderecoEntity.getId())
                    .uuid(enderecoEntity.getUuid())
                    .tipoEndereco(enderecoEntity.getTipoEndereco())
                    .cidade(enderecoEntity.getCidade())
                    .estado(enderecoEntity.getEstado())
                    .endereco(enderecoEntity.getEndereco())
                    .bairro(enderecoEntity.getBairro())
                    .build();
        }
    }

    public static Cliente mapToCliente(ClienteEntity clienteEntity) {
        if(Objects.isNull(clienteEntity)){
            return null;
        } else {
            return Cliente.builder()
                    .id(clienteEntity.getId())
                    .uuid(clienteEntity.getUuid())
                    .cpf(new CPF(clienteEntity.getCpf()))
                    .nome(clienteEntity.getNome())
                    .endereco(mapToEndereco(clienteEntity.getEndereco()))
                    .build();
        }
    }
}
