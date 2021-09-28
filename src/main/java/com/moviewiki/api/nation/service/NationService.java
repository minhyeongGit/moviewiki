package com.moviewiki.api.nation.service;

import com.moviewiki.api.nation.domain.Nation;

import java.util.List;
import java.util.Optional;

public interface NationService {

    Optional<Nation> findById(String nationId);

    List<Nation> findAll();
}
