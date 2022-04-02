package com.nero.socialmedia.analysis.instagram.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity(name = "setting")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Setting {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "multiple", nullable = false)
    private boolean multiple;

    @Column(name = "field_name", nullable = false)
    private String fieldName;

    @Column(name = "value", nullable = false)
    private String value;
}
