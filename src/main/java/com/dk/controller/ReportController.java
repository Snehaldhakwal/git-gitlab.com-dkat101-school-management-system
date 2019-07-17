package com.dk.controller;

import com.dk.model.Report;
import com.dk.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/report")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping
    public @ResponseBody Iterable<Report> findAll() {
        return reportRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Report findById(@PathVariable Long id) {
        Optional<Report> report = this.reportRepository.findById(id);
        if (report.isPresent()) {
            return report.get();
        } else {
            return null;
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Report create(@RequestBody Report resource) {
        return reportRepository.save(resource);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Report update(@PathVariable("id") Long id, @RequestBody Report newReport) {
        return reportRepository.findById(id)
            .map(report -> {
                report.setStudentId(newReport.getStudentId());
                report.setTerm(newReport.getTerm());
                report.setScores(newReport.getScores());

                return reportRepository.save(report);
            })
            .orElseGet(() -> {

                return null;
            });
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        reportRepository.deleteById(id);
    }

}
