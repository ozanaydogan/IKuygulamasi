package com.example.demo.entites.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SkillsDto {

    private Integer Id;

    @NotEmpty(message = "usta olunan alan boş bırakılamaz")
    private String theLanguageOfMastery;

    @NotEmpty(message = "ustalık puanı boş bırakılamaz")
    private int masteryPoints;

}
