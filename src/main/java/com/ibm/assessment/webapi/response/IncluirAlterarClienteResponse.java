package com.ibm.assessment.webapi.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class IncluirAlterarClienteResponse {

    private String mensagem;

    private String uuidCliente;
}
