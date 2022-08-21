package com.kolay.repository;

import com.kolay.model.Kpac;

import java.util.List;

public interface KpacRepository {
    public List<Kpac> findAll();

    public List<Kpac> findBySetId(Integer setId);
}
