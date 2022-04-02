package com.nero.socialmedia.analysis.instagram.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "follower")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Follower {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "follower_account_name", nullable = false)
    private String followerAccountName;

    @Column(name = "account", nullable = false)
    private String account;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="calc_job_id", nullable=false)
    private CalcJob calcJob;

}
