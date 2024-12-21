package com.ozhegov.laba3.dao;

import org.primefaces.model.LazyDataModel;

import java.util.List;

public interface DAO<T> {
    void save(T t);
    List<T> getAll();
}
