package com.kolay.repository;

import com.kolay.model.Kset;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
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

    @Override
    public Kset findById(Integer id) {
        return jdbcTemplate.queryForObject("select * from kset where id = ?",
            new Object[]{id}, BeanPropertyRowMapper.newInstance(Kset.class));
    }
}
