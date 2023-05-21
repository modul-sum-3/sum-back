package com.fitness.fitnessBack.carnets.controller;

import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Value
@CrossOrigin
@RequestMapping(value = "/carnets", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarnetController {
}
