package com.moviewiki.api.nation.repository;

import com.moviewiki.api.nation.domain.Nation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationRepository extends JpaRepository<Nation, String> {
}
