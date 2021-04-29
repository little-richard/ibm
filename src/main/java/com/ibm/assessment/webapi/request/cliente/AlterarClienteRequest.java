package com.ibm.assessment.webapi.request.cliente;

import com.ibm.assessment.domain.cliente.model.enums.TipoEnderecoEnum;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlterarClienteRequest {

    @NotNull private String uuid;
    @NotNull private String nome;
    @NotNull private String cpf;
    private EnderecoRequest endereco;

    @Getter
    @Setter
    public class EnderecoRequest {
        @NotNull private String endereco;
        @NotNull private String cidade;
        @NotNull private String estado;
        private String numero;
        private String bairro;
        @Enumerated(EnumType.STRING) private TipoEnderecoEnum tipoEnderecoEnum;
    }
}
