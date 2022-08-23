package com.kolay.repository;

import com.kolay.model.Kpac;

import java.util.List;

public interface KpacRepository {
    List<Kpac> findAll();

    List<Kpac> findBySetId(Integer setId);

    void save(Kpac kpac);

    void deleteById(Integer id);

}
