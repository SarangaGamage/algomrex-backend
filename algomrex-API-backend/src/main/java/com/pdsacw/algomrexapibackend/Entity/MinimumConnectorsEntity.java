package com.pdsacw.algomrexapibackend.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="shortest_distance")
public class MinimumConnectorsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "link_1")
    private String link1;
    @Column(name = "link_2")
    private String link2;
    @Column(name = "link_3")
    private String link3;
    @Column(name = "link_4")
    private String link4;
    @Column(name = "link_5")
    private String link5;
    @Column(name = "link_6")
    private String link6;
    @Column(name = "link_7")
    private String link7;
    @Column(name = "link_8")
    private String link8;
    @Column(name = "link_9")
    private String link9;

}