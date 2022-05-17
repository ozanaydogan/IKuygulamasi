package com.example.demo.entites;

import java.io.Serializable;
import java.util.List;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "candidate")
public class CandidateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "salary_expectation")
    private int salaryExpectation;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_it_working_now")
    private boolean isItWorkingNow;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "age")
    private int age;



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidateEntity", cascade = CascadeType.ALL)
    public List<ExperienceEntity> experiences;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidateEntity", cascade = CascadeType.ALL)
    public List<SkillEntity> skills;
}
