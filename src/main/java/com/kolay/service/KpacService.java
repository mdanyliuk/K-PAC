package com.kolay.service;

import com.kolay.model.Kpac;
import com.kolay.repository.KpacRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KpacService {

  @Autowired
  private KpacRepository kpacRepository;

  public List<Kpac> findAllKpacs() {
     return kpacRepository.findAll();
  }

  public List<Kpac> findBySetId() {
    return kpacRepository.findBySetId(2);
  }
}
