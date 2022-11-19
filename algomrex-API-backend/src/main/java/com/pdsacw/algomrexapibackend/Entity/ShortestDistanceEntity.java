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
public class ShortestDistanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "city_a")
    private String cityA;
    @Column(name = "city_b")
    private String cityB;
    @Column(name = "city_c")
    private String cityC;
    @Column(name = "city_d")
    private String cityD;
    @Column(name = "city_e")
    private String cityE;
    @Column(name = "city_f")
    private String cityF;
    @Column(name = "city_g")
    private String cityG;
    @Column(name = "city_h")
    private String cityH;
    @Column(name = "city_i")
    private String cityI;
    @Column(name = "city_j")
    private String cityJ;
}