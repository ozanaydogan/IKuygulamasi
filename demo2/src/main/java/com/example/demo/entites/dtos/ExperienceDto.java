package com.example.demo.entites.dtos;


import lombok.*;

import javax.validation.constraints.NotEmpty;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExperienceDto {


    private Integer id;

    @NotEmpty(message = "son çalışılan şirket boş bırakılamaz")
    private String lastCompany;

    @NotEmpty(message = "son maaş boş bırakılamaz")
    private int lastSalary;

    @NotEmpty(message = "son çalışılan alan boş bırakılamaz")
    private String whichFieldDidWork;



}
