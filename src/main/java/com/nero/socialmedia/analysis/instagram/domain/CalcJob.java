package com.nero.socialmedia.analysis.instagram.domain;

import com.nero.socialmedia.analysis.instagram.constants.CalcFrequency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity(name = "calc_job")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class CalcJob {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "calc_frequency", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private CalcFrequency calcFrequency;

    @Column(name = "job_time", nullable = false)
    private int jobTime;

    @OneToMany(mappedBy="calcJob", fetch = FetchType.EAGER)
    private Set<Follower> followers;
}
