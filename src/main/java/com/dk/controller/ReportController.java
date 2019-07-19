package com.dk.controller;

import com.dk.model.Report;
import com.dk.repository.ReportRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/report")
@Api(value="Report", description="Operations pertaining to report")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping
    @ApiOperation(value = "View a list of available reports", response = Report.class)
    public @ResponseBody Iterable<Report> findAll() {
        return reportRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "View a single record of available reports", response = Report.class)
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
    @ApiOperation(value = "Create a single record of reports", response = Report.class)
    public Report create(@RequestBody Report resource) {
        return reportRepository.save(resource);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a single record of available reports", response = Report.class)
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
    @ApiOperation(value = "Delete a single record of available reports")
    public void delete(@PathVariable("id") Long id) {
        reportRepository.deleteById(id);
    }

}
