package com.ibm.assessment.domain.cliente.model.validators;

import com.ibm.assessment.domain.cliente.exception.ClienteException;
import com.ibm.assessment.domain.cliente.model.Cliente;
import com.ibm.assessment.domain.common.interfaces.IValidator;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class ClienteValidator implements IValidator<Cliente>{

    @Override
    public void validate(Cliente cliente) {

        if(Objects.isNull(cliente)){
            throw new ClienteException("Erro ao instanciar um cliente.");
        }

        if(Strings.isBlank(cliente.getNome()) || cliente.getNome().length() < 3) {
            throw new ClienteException("Erro ao instanciar um cliente: Nome inválido.");
        }

        if(Objects.isNull(cliente.getCpf())){
            throw new ClienteException("Erro ao instanciar um cliente: CPF inválido.");
        }
    }
}
