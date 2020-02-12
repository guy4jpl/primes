package com.guychisholm.rbs.primes.model;

import java.util.List;

public class Primes {

    private Integer initial;

    private List<Integer> primes;

    public Primes(Integer initial, List<Integer> primes) {
        this.initial = initial;
        this.primes = primes;
    }

    public Integer getInitial() {
        return initial;
    }

    public List<Integer> getPrimes() {
        return primes;
    }
}
