package com.example.demo.business.abstracts;

import com.example.demo.entites.dtos.CandidateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface CandidateService {

    ResponseEntity<CandidateDto> getCandidateForId(Integer id);
    ResponseEntity<CandidateDto> add(CandidateDto candidateDto);
    ResponseEntity<CandidateDto> update(CandidateDto candidateDto);
    List<CandidateDto> getAllCandidates();
    ResponseEntity<Map<String, Boolean>> deleteCandidate(Integer id);
    List<CandidateDto> findCandidatesWithPagination(int offset, int pageSize);
}
