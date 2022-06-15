package com.nero.socialmedia.analysis.instagram.repositories;

import com.nero.socialmedia.analysis.instagram.domain.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, Integer> {
}
