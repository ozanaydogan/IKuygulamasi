package com.example.demo.repositories;


import com.example.demo.entites.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidateRepo extends JpaRepository<CandidateEntity,Integer> {
}
