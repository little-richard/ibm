package com.ibm.assessment.domain.common.factories;

import com.ibm.assessment.domain.common.interfaces.IValidator;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class ValidatorFactory {

    @SuppressWarnings({"rawtypes"})
    public static IValidator getInstance(String typeName){
        IValidator validator;

        Reflections reflections = new Reflections("com.ibm.assessment.domain.cliente.model.validators");

        Set<Class<? extends IValidator>> classes = reflections.getSubTypesOf(IValidator.class);

        Class cs = classes
                .stream()
                .filter(i-> Arrays.stream(i.getGenericInterfaces())
                        .filter(c-> c.getTypeName().contains(typeName)).map(obj -> true)
                        .findFirst()
                        .orElse(false))
                .findAny()
                .orElse(null);

        try{
            if(Objects.nonNull(cs)){
                validator = (IValidator) cs.newInstance();
            }else{
                throw new IllegalStateException("Model não possui Validador Implementado: " + typeName);
            }
        }catch (Exception e){
            throw new IllegalStateException("Model não possui Validador Implementado: " + typeName);
        }

        return validator;
    }
}
