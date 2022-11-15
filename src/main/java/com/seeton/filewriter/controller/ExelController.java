package com.seeton.filewriter.controller;

import com.seeton.filewriter.service.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping(value = "/v1/exel")
public class ExelController {
    @Autowired
    private Generator generator;

    @ResponseBody
    @GetMapping(value = "/get")
    public ResponseEntity<Void> getExel() {
        Instant dateFrom = Instant.parse("2020-05-01T12:00:00.00Z");
        Instant dateTo = Instant.parse("2048-11-07T03:45:00.00Z");
        generator.generate(dateFrom, dateTo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
