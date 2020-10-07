package com.vladimir.dentalclinic.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityAndDTOConverter {

    private ModelMapper modelMapper;

    @Autowired
    public EntityAndDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <E, D> D convert(E object, Class<D> clazz) {
        return modelMapper.map(object, clazz);
    }

}
