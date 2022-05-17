package com.example.demo.api.controller;


import com.example.demo.business.abstracts.CandidateService;
import com.example.demo.entites.dtos.CandidateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/candidates")
public class CandidateController {


    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    // http://localhost:8081/rest/candidates/add
    @PostMapping("/add")
    public ResponseEntity<CandidateDto> add(@Valid @RequestBody CandidateDto candidateDto) {
        return candidateService.add(candidateDto);
    }

    // http://localhost:8081/rest/candidates/get/candidate/1
    @GetMapping("/get/candidate/{candidateId}")
    public ResponseEntity<CandidateDto> getCandidateForId(@PathVariable(name="candidateId") Integer candidateId) {
        return candidateService.getCandidateForId(candidateId);
    }

    // http://localhost:8081/rest/candidates/update
    @PutMapping("/update")
    public ResponseEntity<CandidateDto> update(@Valid @RequestBody CandidateDto candidateDto) {
        return candidateService.update(candidateDto);
    }

    // http://localhost:8081/rest/candidates/allcandidates
    @GetMapping("/allcandidates")
    public List<CandidateDto> getAllCandidates() {
        List<CandidateDto> candidateDto =  candidateService.getAllCandidates();
        return candidateDto;
    }

    // http://localhost:8081/rest/candidates/users/4
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCandidate(@PathVariable Integer id) {
        candidateService.deleteCandidate(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // http://localhost:8081/rest/candidates/pagination/0/5
    @GetMapping("/pagination/{offset}/{pageSize}")
    public List<CandidateDto> findCandidatesWithPagination(@PathVariable int offset, @PathVariable int pageSize){
        List<CandidateDto> candidateDtoList = candidateService.findCandidatesWithPagination(offset,pageSize);
        return candidateDtoList;

    }

}