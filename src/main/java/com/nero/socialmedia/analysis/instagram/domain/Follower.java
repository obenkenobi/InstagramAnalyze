package com.nero.socialmedia.analysis.instagram.domain;

import javax.persistence.*;

@Entity(name = "follower")
public class Follower {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "follower_account_name", nullable = false)
    private String followerAccountName;

    @Column(name = "account", nullable = false)
    private String account;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="calc_job_id", nullable=false)
    private CalcJob calcJob;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFollowerAccountName() {
        return followerAccountName;
    }

    public void setFollowerAccountName(String followerAccountName) {
        this.followerAccountName = followerAccountName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public CalcJob getCalcJob() {
        return calcJob;
    }

    public void setCalcJob(CalcJob calcJob) {
        this.calcJob = calcJob;
    }
}
