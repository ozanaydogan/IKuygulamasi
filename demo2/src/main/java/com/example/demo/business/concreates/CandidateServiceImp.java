package com.example.demo.business.concreates;


import com.example.demo.business.abstracts.CandidateService;
import com.example.demo.entites.CandidateEntity;
import com.example.demo.entites.ExperienceEntity;
import com.example.demo.entites.SkillEntity;
import com.example.demo.entites.dtos.CandidateDto;
import com.example.demo.entites.dtos.ExperienceDto;
import com.example.demo.entites.dtos.SkillsDto;
import com.example.demo.repositories.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CandidateServiceImp implements CandidateService {

    public final CandidateRepo candidateRepo;

    public final ExperienceRepo experienceRepo;

    public final SkillsRepo skillsRepo;

    @Autowired // bean yapısı olduğu için autowired dedik, spring, bean yapılarını autowired anotasyonu ile kendisi otomatik oluşturuyor
    private ModelMapper modelMapper;

    public CandidateServiceImp(CandidateRepo candidateRepo, ExperienceRepo experienceRepo, SkillsRepo skillsRepo) {
        this.candidateRepo = candidateRepo;
        this.experienceRepo = experienceRepo;
        this.skillsRepo = skillsRepo;

    }

    @Override
    public ResponseEntity<CandidateDto> getCandidateForId(Integer id) {

        try {
            CandidateEntity candidateEntity = candidateRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(id + ""));
            CandidateDto candidateDto = EntityToDto(candidateEntity);
            return ResponseEntity.ok(candidateDto);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    @Override
    public ResponseEntity<CandidateDto> add(CandidateDto candidateDto) {
        CandidateEntity candidateEntity = CandidateEntity.builder()
                .fullName(candidateDto.getFullName())
                .identityNumber(candidateDto.getIdentityNumber())
                .phoneNumber(candidateDto.getPhoneNumber())
                .salaryExpectation(candidateDto.getSalaryExpectation())
                .isItWorkingNow(candidateDto.isItWorkingNow()) //  isitworkingnow neden get ile alamadım anlayamadım.
                .gender(candidateDto.getGender())
                .age(candidateDto.getAge())
                .build();

        candidateRepo.save(candidateEntity);

        if(candidateDto.getExperiences().size() != 0) {
            for (ExperienceDto item : candidateDto.getExperiences()) {

                ExperienceEntity experienceEntity = ExperienceEntity.builder()
                        .lastCompany(item.getLastCompany())
                        .lastSalary(item.getLastSalary())
                        .whichFieldDidWork(item.getWhichFieldDidWork())
                        .candidateEntity(candidateEntity)                                  // ???? kritik nokta olabilir
                        .build();

                experienceRepo.save(experienceEntity);
            }
        }


        if(candidateDto.getSkills().size() != 0) {
            for (SkillsDto item : candidateDto.getSkills()) {
                SkillEntity skillEntity = SkillEntity.builder()
                        .theLanguageOfMastery(item.getTheLanguageOfMastery())
                        .masteryPoints(item.getMasteryPoints())
                        .candidateEntity(candidateEntity)
                        .build();

                skillsRepo.save(skillEntity);
            }
        }

        return ResponseEntity.ok(candidateDto);
    }

    @Override
    public ResponseEntity<CandidateDto> update(CandidateDto candidateDto) {

        List<ExperienceEntity> experienceEntityList = new ArrayList<>();
        List<SkillEntity> skillEntityList = new ArrayList<>();

        CandidateEntity candidateEntity = CandidateEntity.builder()
                .id(candidateDto.getId())
                .fullName(candidateDto.getFullName())
                .identityNumber(candidateDto.getIdentityNumber())
                .salaryExpectation(candidateDto.getSalaryExpectation())
                .phoneNumber(candidateDto.getPhoneNumber())
                .isItWorkingNow(candidateDto.isItWorkingNow())
                .gender(candidateDto.getGender())
                .age(candidateDto.getAge())
                .build();

        if(candidateDto.getExperiences().size() != 0) {
            for (ExperienceDto item : candidateDto.getExperiences()) {

                    ExperienceEntity experienceEntity = ExperienceEntity.builder()
                        .id(item.getId())
                        .lastCompany(item.getLastCompany())
                        .lastSalary(item.getLastSalary())
                        .whichFieldDidWork(item.getWhichFieldDidWork())
                        .candidateEntity(candidateEntity)                                  // ???? kritik nokta olabilir
                        .build();
                        experienceEntityList.add(experienceEntity);
            }
        }


        if(candidateDto.getSkills().size() != 0) {
            for (SkillsDto item : candidateDto.getSkills()) {
                SkillEntity skillEntity = SkillEntity.builder()
                        .id(item.getId())
                        .theLanguageOfMastery(item.getTheLanguageOfMastery())
                        .masteryPoints(item.getMasteryPoints())
                        .candidateEntity(candidateEntity)
                        .build();
                        skillEntityList.add(skillEntity);

            }
        }

        candidateEntity.setExperiences(experienceEntityList);
        candidateEntity.setSkills(skillEntityList);

        candidateRepo.save(candidateEntity);
        CandidateDto candidateDto1 = EntityToDto(candidateRepo.getById(candidateEntity.getId()));
        return ResponseEntity.ok(candidateDto1);
    }

    @Override
    public  List<CandidateDto> getAllCandidates(){

        List<CandidateDto> candidateDto = new ArrayList<>();
        Iterable<CandidateEntity> candidateList = candidateRepo.findAll();
        for (CandidateEntity entity : candidateList) {
            CandidateDto userDto = EntityToDto(entity);//model
            candidateDto.add(userDto);
        }

        return candidateDto;
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteCandidate(Integer id) {
        CandidateEntity candidateEntity = candidateRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user not exist with id :" + id)); // yanlis olabilir.

        candidateRepo.delete(candidateEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @Override
    public List<CandidateDto> findCandidatesWithPagination(int offset, int pageSize) {
        List<CandidateDto> candidateDto = new ArrayList<>();
        Page<CandidateEntity> candidateList = candidateRepo.findAll(PageRequest.of(offset,pageSize));
        for (CandidateEntity entity : candidateList) {
            CandidateDto userDto = EntityToDto(entity);//model
            candidateDto.add(userDto);
        }
        return candidateDto;
    }


    // asagidaki fonksiyonları saglayan bir sinif olusturup kullanabilirdim. Generic sinif seklinde ? DRY (dont repeat yourself) ?

    //Model Mapper Entity ==> Dto
    public CandidateDto EntityToDto(CandidateEntity candidateEntity) {
        CandidateDto candidateDto = modelMapper.map(candidateEntity, CandidateDto.class);
        return candidateDto;
    }

    //Model Mapper Dto  ==> Entity
    public CandidateEntity DtoToEntity(CandidateDto candidateDto) {
        CandidateEntity candidateEntity = modelMapper.map(candidateDto, CandidateEntity.class);
        return candidateEntity;
    }

    ////////////////////////////////////////

    //Model Mapper Entity ==> Dto
    public ExperienceDto EntityToDto(ExperienceEntity experienceEntity) {
        ExperienceDto experienceDto = modelMapper.map(experienceEntity, ExperienceDto.class);
        return experienceDto;
    }

    //Model Mapper Dto  ==> Entity
    public ExperienceEntity DtoToEntity(ExperienceDto experienceDto) {
        ExperienceEntity experienceEntity = modelMapper.map(experienceDto, ExperienceEntity.class);
        return experienceEntity;
    }


    ///////////////////////////////////////////

    //Model Mapper Entity ==> Dto
    public SkillsDto EntityToDto(SkillEntity skillEntity) {
        SkillsDto skillsDto = modelMapper.map(skillEntity, SkillsDto.class);
        return skillsDto;
    }

    //Model Mapper Dto  ==> Entity
    public SkillEntity DtoToEntity(SkillsDto skillsDto) {
        SkillEntity skillEntity = modelMapper.map(skillsDto, SkillEntity.class);
        return skillEntity;
    }
}
