package com.kolay.service;

import com.kolay.model.Kpac;
import com.kolay.model.KpacDto;
import com.kolay.model.Kset;
import com.kolay.model.KsetDto;
import com.kolay.repository.KpacRepository;
import com.kolay.repository.KsetRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KpacService {

    @Autowired
    private KpacRepository kpacRepository;

    @Autowired
    private KsetRepository ksetRepository;

    public List<Kpac> findAllKpacs() {
        return kpacRepository.findAll();
    }

    public void saveKpac(Kpac kpac) {
        kpac.setCreationDate(LocalDate.now());
        kpacRepository.save(kpac);
    }

    public List<Kpac> findPacsBySetId(Integer id) {
        return kpacRepository.findBySetId(id);
    }

    public void deleteKpacById(Integer id) {
        kpacRepository.deleteById(id);
    }

    public List<Kset> findAllSets() {
        return ksetRepository.findAll();
    }

    public Kset findSetById(Integer id) {
        return ksetRepository.findById(id);
    }

    public Integer addKset(KsetDto ksetDto) {
        Kset kset = new Kset();
        kset.setTitle(ksetDto.getTitle());
        Integer ksetId = ksetRepository.save(kset);

        for (KpacDto kpacDto : ksetDto.getKpacs()) {
            if (kpacDto.isSelected()) {
                ksetRepository.addPacToSet(ksetId, kpacDto.getId());
            }
        }
        return ksetId;
    }

    public void deleteSetById(Integer id) {
        ksetRepository.deleteById(id);
    }
}
