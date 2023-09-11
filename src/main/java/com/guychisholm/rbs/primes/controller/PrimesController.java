package com.guychisholm.rbs.primes.controller;

import com.guychisholm.rbs.primes.exception.AlgorithmNotSupportedException;
import com.guychisholm.rbs.primes.model.Primes;
import com.guychisholm.rbs.primes.model.PrimesBuilder;
import com.guychisholm.rbs.primes.service.PrimesService;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/primes")
@Validated
public class PrimesController {

    private PrimesService primesService;

    private ApplicationContext context;

    public PrimesController(
            PrimesService primesService,
            ApplicationContext context) {
        this.primesService = primesService;
        this.context = context;
    }

    @GetMapping(value = "/{initial}")
    public Primes getPrimes(
            @PathVariable @Min(0) @Max(100_000_000) Integer initial,
            @RequestParam(required = false) String algorithm) {

        if (algorithm != null) {
            try {
                this.primesService = context.getBean(algorithm, PrimesService.class);
            } catch (NoSuchBeanDefinitionException e) {
                throw new AlgorithmNotSupportedException();
            }
        }
        return new PrimesBuilder()
                .setInitial(initial)
                .setPrimes(primesService.getPrimes(initial))
                .createPrimes();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
