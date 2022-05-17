package com.example.demo.entites;

import java.io.Serializable;

import lombok.*;

import javax.persistence.*;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "skill")
public class SkillEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "the_language_of_mastery")
    private String theLanguageOfMastery;

    @Column(name = "mastery_points")
    private int masteryPoints;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private CandidateEntity candidateEntity;
}
