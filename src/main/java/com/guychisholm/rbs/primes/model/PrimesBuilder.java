package com.guychisholm.rbs.primes.model;

import java.util.List;

public class PrimesBuilder {
    private Integer initial;
    private List<Integer> primes;

    public PrimesBuilder setInitial(Integer initial) {
        this.initial = initial;
        return this;
    }

    public PrimesBuilder setPrimes(List<Integer> primes) {
        this.primes = primes;
        return this;
    }

    public Primes createPrimes() {
        return new Primes(initial, primes);
    }
}