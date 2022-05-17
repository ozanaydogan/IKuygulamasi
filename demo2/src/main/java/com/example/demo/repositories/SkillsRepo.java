package com.example.demo.repositories;

import com.example.demo.entites.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SkillsRepo extends JpaRepository<SkillEntity,Integer> {

}
