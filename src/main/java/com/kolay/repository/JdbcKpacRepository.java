package com.kolay.repository;

import com.kolay.model.Kpac;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcKpacRepository implements KpacRepository {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertKpac;

    @Autowired
    public JdbcKpacRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Kpac> findAll() {
        return jdbcTemplate.query("select * from kpac",
                BeanPropertyRowMapper.newInstance(Kpac.class));
    }

    @Override
    public List<Kpac> findBySetId(Integer setId) {
        return jdbcTemplate.query("SELECT * From kpac \n"
                + "inner join pac_set\n"
                + "on kpac.id = pac_set.kpac_id\n"
                + "where pac_set.kset_id = ?",
                BeanPropertyRowMapper.newInstance(Kpac.class), setId);
    }

    @Override
    public void save(Kpac kpac) {
        jdbcTemplate.update("INSERT INTO kpac (title, description, creation_date) " +
                "values (?, ?, ?)", kpac.getTitle(), kpac.getDescription(),
                kpac.getCreationDate());
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM kpac WHERE id = ?", id);
    }
}
