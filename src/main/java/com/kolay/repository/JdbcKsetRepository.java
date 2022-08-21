package com.kolay.repository;

import com.kolay.model.Kset;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcKsetRepository implements KsetRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcKsetRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Kset> findAll() {
        return jdbcTemplate.query("select * from kset", BeanPropertyRowMapper.newInstance(Kset.class));
    }
}
