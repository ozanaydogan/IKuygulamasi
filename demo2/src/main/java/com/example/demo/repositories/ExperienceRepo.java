package com.example.demo.repositories;

import com.example.demo.entites.ExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ExperienceRepo extends JpaRepository<ExperienceEntity,Integer> {

}
