package com.nero.socialmedia.analysis.instagram.repositories;

import com.nero.socialmedia.analysis.instagram.domain.CalcJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalcJobRepository extends JpaRepository<CalcJob, Integer> {
}
