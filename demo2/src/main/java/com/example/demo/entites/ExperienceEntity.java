package com.example.demo.entites;


import java.io.Serializable;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "experience")
public class ExperienceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "last_company")
    private String lastCompany;

    @Column(name = "identity_number")
    private int lastSalary;

    @Column(name = "which_field_did_work")
    private String whichFieldDidWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private CandidateEntity candidateEntity;
}
