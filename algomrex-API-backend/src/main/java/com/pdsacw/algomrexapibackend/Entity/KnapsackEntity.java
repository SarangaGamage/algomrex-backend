package com.pdsacw.algomrexapibackend.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Knapsack")
public class KnapsackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "game_id")
    private String gameId;

    @Column(name = "maximum_profit")
    private Integer maximumProfit;

}
