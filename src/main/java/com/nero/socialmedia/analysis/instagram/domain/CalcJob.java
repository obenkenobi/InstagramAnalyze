package com.nero.socialmedia.analysis.instagram.domain;

import com.nero.socialmedia.analysis.instagram.constants.CalcFrequency;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "calc_job")
public class CalcJob {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "calc_frequency", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private CalcFrequency calcFrequency;

    @Column(name = "job_time", nullable = false)
    private int jobTime;

    @OneToMany(mappedBy="calcJob", fetch = FetchType.EAGER)
    private Set<Follower> followers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CalcFrequency getCalcFrequency() {
        return calcFrequency;
    }

    public void setCalcFrequency(CalcFrequency calcFrequency) {
        this.calcFrequency = calcFrequency;
    }

    public int getJobTime() {
        return jobTime;
    }

    public void setJobTime(int jobTime) {
        this.jobTime = jobTime;
    }

    public Set<Follower> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Follower> followers) {
        this.followers = followers;
    }
}
