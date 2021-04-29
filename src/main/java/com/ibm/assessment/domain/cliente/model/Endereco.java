package com.ibm.assessment.domain.cliente.model;

import com.ibm.assessment.domain.cliente.model.enums.TipoEnderecoEnum;
import com.ibm.assessment.domain.common.DomainGeneric;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Builder
@Getter
public class Endereco extends DomainGeneric<Endereco> {

    private final Long id;

    private final String uuid;

    private String endereco;

    @Setter
    private String numero;

    @Setter
    private String bairro;

    private String cidade;

    private String estado;

    @Setter
    private TipoEnderecoEnum tipoEndereco;

    private Endereco(Long id, String uuid, String endereco, String numero, String bairro, String cidade, String estado, TipoEnderecoEnum tipoEndereco){
        super();
        this.id = id;
        this.uuid = uuid;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.tipoEndereco = Objects.isNull(tipoEndereco) ? TipoEnderecoEnum.NAO_ESPECIFICADO : tipoEndereco;
        this.validate(this);
    }

    public static class EnderecoBuilder {
        private Long id;

        private String uuid;

        private String endereco;

        private String numero;

        private String bairro;

        private String cidade;

        private String estado;

        private TipoEnderecoEnum tipoEndereco;

        public EnderecoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public EnderecoBuilder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public EnderecoBuilder endereco(String endereco){
            this.endereco = endereco;
            return this;
        }

        public EnderecoBuilder numero(String numero){
            this.numero = numero;
            return this;
        }

        public EnderecoBuilder bairro(String bairro){
            this.bairro = bairro;
            return this;
        }

        public EnderecoBuilder cidade(String cidade){
            this.cidade = cidade;
            return this;
        }

        public EnderecoBuilder estado(String estado){
            this.estado = estado;
            return this;
        }

        public EnderecoBuilder tipoEndereco(TipoEnderecoEnum tipoEnderecoEnum){
            this.tipoEndereco = tipoEnderecoEnum;
            return this;
        }

        public Endereco build(){
            return new Endereco(this.id, this.uuid, this.endereco, this.numero, this.bairro, this.cidade, this.estado, this.tipoEndereco);
        }

    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
        this.validate(this);
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
        this.validate(this);
    }

    public void setEstado(String estado){
        this.estado = estado;
        this.validate(this);
    }

}
