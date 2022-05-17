package com.example.demo.entites.dtos;


import com.example.demo.entites.Gender;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CandidateDto{

    private List<ExperienceDto> experiences;

    private List<SkillsDto> skills;

    private Integer id;

    @NotEmpty(message = "Ad Soyad boş geçilemez")
    private String fullName;

    @NotEmpty(message = "Kimlik numarası boş geçilemez")
    private String identityNumber;

    @Positive(message = "Aylık gelir negatif değer olamaz")
    private int salaryExpectation;

    @NotEmpty(message = "Telefon numarası boş geçilemez")
    private String phoneNumber;

    @NotEmpty(message = "şu an çalışıyor mu bilgisi boş geçilemez")
    private boolean isItWorkingNow;

    @NotEmpty(message ="cinsiyet bilgisi boş geçilemez")
    private Gender gender;

    @NotEmpty(message = "yaş bilgisi boş geçilemez")
    private int age;


}
