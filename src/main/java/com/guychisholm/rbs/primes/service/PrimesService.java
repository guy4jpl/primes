package com.guychisholm.rbs.primes.service;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface PrimesService {

    /**
     * Retrieve primes up to an initial value
     *
     * Results are cached using Spring's cache utilities
     * @param initial
     * @return
     */
    @Cacheable(value="primes", key="#initial")
    List<Integer> getPrimes(Integer initial);

}
