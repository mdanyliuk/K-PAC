package com.kolay.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.kolay.config.DataSourceConfiguration;
import com.kolay.config.TestDataConfiguration;
import com.kolay.config.WebConfiguration;
import com.kolay.model.Kpac;
import com.kolay.model.KpacDto;
import com.kolay.model.Kset;
import com.kolay.model.KsetDto;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.ContextConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebConfiguration.class,
        DataSourceConfiguration.class, TestDataConfiguration.class})
@WebAppConfiguration
public class ServiceTests {
    private KpacService kpacService;

    @Autowired
    public void setService(KpacService kpacService) {
        this.kpacService = kpacService;
    }

    @Test
    @Transactional
    public void shouldInsertKpac() {
        List<Kpac> kpacList = this.kpacService.findAllKpacs();
        int found = kpacList.size();

        Kpac kpac = new Kpac();
        kpac.setTitle("title");
        kpac.setDescription("description");
        this.kpacService.saveKpac(kpac);
        kpacList = this.kpacService.findAllKpacs();

        assertThat(kpacList.size()).isEqualTo(found + 1);
    }

    @Test
    @Transactional
    public void shouldInsertSetAndPacToIt() {
        List<Kset> ksetList = this.kpacService.findAllSets();
        int found = ksetList.size();

        KsetDto ksetDto = new KsetDto();
        ksetDto.setTitle("test kset");
        List<KpacDto> kpacDtoList = new ArrayList<>();
        KpacDto kpacDto = new KpacDto();
        kpacDto.setSelected(false);
        kpacDto.setId(1);
        kpacDtoList.add(kpacDto);
        kpacDto = new KpacDto();
        kpacDto.setSelected(true);
        kpacDto.setId(2);
        kpacDtoList.add(kpacDto);
        kpacDto = new KpacDto();
        kpacDto.setSelected(true);
        kpacDto.setId(3);
        kpacDtoList.add(kpacDto);
        ksetDto.setKpacs(kpacDtoList);
        Integer newId = this.kpacService.addKset(ksetDto);
        ksetList = this.kpacService.findAllSets();

        assertThat(ksetList.size()).isEqualTo(found + 1);
        assertThat(this.kpacService.findPacsBySetId(newId).size()).isEqualTo(2);
    }
}