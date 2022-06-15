package com.nero.socialmedia.analysis.instagram.repositories;

import com.nero.socialmedia.analysis.instagram.domain.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower, Integer> {
}
