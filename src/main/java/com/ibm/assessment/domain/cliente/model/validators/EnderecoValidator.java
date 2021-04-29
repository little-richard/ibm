package com.ibm.assessment.domain.cliente.model.validators;

import com.ibm.assessment.domain.cliente.exception.EnderecoException;
import com.ibm.assessment.domain.cliente.model.Endereco;
import com.ibm.assessment.domain.common.interfaces.IValidator;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class EnderecoValidator implements IValidator<Endereco> {

    @Override
    public void validate(Endereco endereco) {
        if(Objects.isNull(endereco)){
            throw new EnderecoException("Endereco inválido: objeto null");
        }

        if(Strings.isBlank(endereco.getEndereco())){
            throw new EnderecoException("Endereco inválido: endereço vazio");
        }

        if(Strings.isBlank(endereco.getCidade())){
            throw new EnderecoException("Endereco inválido: cidade do endereço vazia");
        }

        if(Strings.isBlank(endereco.getEstado())){
            throw new EnderecoException("Endereco inválido: estado do endereço vazio");
        }

        if(Objects.isNull(endereco.getTipoEndereco())){
            throw new EnderecoException("Endereco inválido: Tipo endereço vazio");
        }
    }
}
