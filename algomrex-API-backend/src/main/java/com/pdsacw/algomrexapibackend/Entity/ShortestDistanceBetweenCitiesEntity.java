package com.pdsacw.algomrexapibackend.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="distance_between_cities")
public class ShortestDistanceBetweenCitiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "game_id")
    private String gameId;

    @Column(name = "a_b")
    private String ab;
    @Column(name = "a_c")
    private String ac;
    @Column(name = "a_d")
    private String ad;
    @Column(name = "a_e")
    private String ae;
    @Column(name = "a_f")
    private String af;
    @Column(name = "a_g")
    private String ag;
    @Column(name = "a_h")
    private String ah;
    @Column(name = "a_i")
    private String ai;
    @Column(name = "a_j")
    private String aj;


    @Column(name = "b_c")
    private String bc;
    @Column(name = "b_d")
    private String bd;
    @Column(name = "b_e")
    private String be;
    @Column(name = "b_f")
    private String bf;
    @Column(name = "b_g")
    private String bg;
    @Column(name = "b_h")
    private String bh;
    @Column(name = "b_i")
    private String bi;
    @Column(name = "b_j")
    private String bj;


    @Column(name = "c_d")
    private String cd;
    @Column(name = "c_e")
    private String ce;
    @Column(name = "c_f")
    private String cf;
    @Column(name = "c_g")
    private String cg;
    @Column(name = "c_h")
    private String ch;
    @Column(name = "c_i")
    private String ci;
    @Column(name = "c_j")
    private String cj;


    @Column(name = "d_e")
    private String de;
    @Column(name = "d_f")
    private String df;
    @Column(name = "d_g")
    private String dg;
    @Column(name = "d_h")
    private String dh;
    @Column(name = "d_i")
    private String di;
    @Column(name = "d_j")
    private String dj;


    @Column(name = "e_f")
    private String ef;
    @Column(name = "e_g")
    private String eg;
    @Column(name = "e_h")
    private String eh;
    @Column(name = "e_i")
    private String ei;
    @Column(name = "e_j")
    private String ej;


    @Column(name = "f_g")
    private String fg;
    @Column(name = "f_h")
    private String fh;
    @Column(name = "f_i")
    private String fi;
    @Column(name = "f_j")
    private String fj;


    @Column(name = "g_h")
    private String gh;
    @Column(name = "g_i")
    private String gi;
    @Column(name = "g_j")
    private String gj;


    @Column(name = "h_i")
    private String hi;
    @Column(name = "h_j")
    private String hj;


    @Column(name = "i_j")
    private String ij;
}