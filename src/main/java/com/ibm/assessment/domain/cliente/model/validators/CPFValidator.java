package com.ibm.assessment.domain.cliente.model.validators;

import com.ibm.assessment.domain.cliente.exception.CpfException;
import com.ibm.assessment.domain.cliente.model.CPF;
import com.ibm.assessment.domain.common.interfaces.IValidator;

import java.util.Objects;

public class CPFValidator implements IValidator<CPF> {

    private void cpfInvalid(String numero){
        if(Objects.isNull(numero) || !numero.matches("^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$")) {
            throw new CpfException("CPF Inválido");
        }
    }

    @Override
    public void validate(CPF cpf) {
        if(Objects.isNull(cpf)){
            throw new CpfException("CPF Inválido");
        }

        this.cpfInvalid(cpf.getNumero());
    }
}
