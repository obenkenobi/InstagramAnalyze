package com.nero.socialmedia.analysis.instagram.repositories;

import com.nero.socialmedia.analysis.instagram.domain.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SettingRepository extends JpaRepository<Setting, UUID> {
}
