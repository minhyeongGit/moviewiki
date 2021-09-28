package com.moviewiki.api.director.service;

import com.moviewiki.api.director.domain.Director;
import com.moviewiki.api.director.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService{

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public void save(Director director) {
        directorRepository.save(director);
    }

    @Override
    public Optional<Director> findById(String directorId) {
        return directorRepository.findById(directorId);
    }

    @Override
    public List<Director> findByDirectorName(String directorName) {
        return directorRepository.findByDirectorName(directorName);
    }

    @Override
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    // 감독 상세 페이지 조회
    @Override
    public Director findByDirectorId(Long directorId){
        return directorRepository.findByDirectorId(directorId);
    }
}
