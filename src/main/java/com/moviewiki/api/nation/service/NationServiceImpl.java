package com.moviewiki.api.nation.service;

import com.moviewiki.api.nation.domain.Nation;
import com.moviewiki.api.nation.repository.NationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NationServiceImpl implements NationService{

    @Autowired
    private NationRepository nationRepository;

    @Override
    public Optional<Nation> findById(String nationId){
        return nationRepository.findById(nationId);
    }

    @Override
    public List<Nation> findAll(){
        return nationRepository.findAll();
    }
}
