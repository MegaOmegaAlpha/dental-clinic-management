package com.vladimir.dentalclinic.service;

import java.util.List;

public interface Service<E, D> {

    List<D> findAll();
    D findById(long id);
    D save(E entity);

}
