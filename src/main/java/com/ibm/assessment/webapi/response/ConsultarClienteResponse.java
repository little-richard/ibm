package com.ibm.assessment.webapi.response;

import com.ibm.assessment.domain.cliente.model.enums.TipoEnderecoEnum;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
public class ConsultarClienteResponse {

    @NotNull
    private String uuid;
    @NotNull private String nome;
    @NotNull private String cpf;
    private EnderecoResponse endereco;

    @Builder
    @Getter
    @Setter
    public static class EnderecoResponse {
        @NotNull private String endereco;
        @NotNull private String cidade;
        @NotNull private String estado;
        private String numero;
        private String bairro;
        @Enumerated(EnumType.STRING) private TipoEnderecoEnum tipoEnderecoEnum;
    }

}
