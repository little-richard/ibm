package com.ibm.assessment.domain.cliente.model;

import com.ibm.assessment.domain.common.DomainGeneric;
import lombok.*;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@Getter
public class Cliente extends DomainGeneric<Cliente> {

    private Long id;

    private String uuid;

    private String nome;

    private CPF cpf;

    private Endereco endereco;

    private Cliente(Long id, String uuid, String nome, CPF cpf, Endereco endereco) {
        this.id = id;
        this.uuid = uuid;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.validate(this);
    }

    public static class ClienteBuilder {
        private String uuid;

        private Long id;

        private String nome;

        private CPF cpf;

        private Endereco endereco;

        public ClienteBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ClienteBuilder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public ClienteBuilder nome(String nome){
            this.nome = nome;
            return this;
        }

        public ClienteBuilder cpf(CPF cpf){
            this.cpf = cpf;
            return this;
        }

        public ClienteBuilder endereco(Endereco endereco){
            this.endereco = endereco;
            return this;
        }

        public Cliente build(){
            return new Cliente(this.id, this.uuid, this.nome, this.cpf, this.endereco);
        }

    }

    public void setNome(String nome){
        this.nome = nome;
        validate(this);
    }

    public void setCPF(CPF cpf){
        this.cpf = cpf;
        validate(this);
    }

    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }
}
