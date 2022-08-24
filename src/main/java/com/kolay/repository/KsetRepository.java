package com.kolay.repository;

import com.kolay.model.Kset;

import java.util.List;

public interface KsetRepository {

    List<Kset> findAll();

    Kset findById(Integer id);

    Integer save(Kset kset);

    void deleteById(Integer id);

    void addPacToSet(Integer ksetId, Integer kpacId);

}
