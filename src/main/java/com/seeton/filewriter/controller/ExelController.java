package com.seeton.filewriter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/exel")
public class ExelController {
    @ResponseBody
    @GetMapping(value = "/get")
    public ResponseEntity<Void> getExel() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
