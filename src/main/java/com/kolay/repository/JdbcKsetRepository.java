package com.kolay.repository;

import com.kolay.model.Kset;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

    @Override
    public Integer save(Kset kset) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO kset (title) values (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, kset.getTitle());
            return ps;
        }, keyHolder);

        return ((BigInteger) keyHolder.getKey()).intValue();
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM kset WHERE id = ?", id);
    }

    @Override
    public void addPacToSet(Integer ksetId, Integer kpacId) {
        jdbcTemplate.update("INSERT INTO pac_set (kset_id, kpac_id) " +
                "values (?, ?)", ksetId, kpacId);
    }
}
