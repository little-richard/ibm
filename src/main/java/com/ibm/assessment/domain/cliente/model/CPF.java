package com.ibm.assessment.domain.cliente.model;

import com.ibm.assessment.domain.common.DomainGeneric;
import com.ibm.assessment.domain.common.UtilTexto;
import lombok.*;

@Value
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
@Builder
public class CPF extends DomainGeneric<CPF> {

    String numero;

    public CPF(String numero) {
        this.numero = UtilTexto.formatarSomenteNumeros(numero);
        this.validate(this);
    }
}
