package com.ibm.assessment.domain.common;

import com.ibm.assessment.domain.common.factories.ValidatorFactory;
import com.ibm.assessment.domain.common.interfaces.IDomainGeneric;
import com.ibm.assessment.domain.common.interfaces.IValidator;

import java.lang.reflect.ParameterizedType;

public abstract class DomainGeneric<T> implements IDomainGeneric<T> {

    private final IValidator<T> validator;

    @SuppressWarnings("unchecked")
    protected DomainGeneric() {

        String typeName = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getTypeName();

        this.validator = ValidatorFactory.getInstance(typeName);
    }

    @Override
    public void validate(T obj) {
        this.validator.validate(obj);
    }


}
