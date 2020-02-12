package com.guychisholm.rbs.primes.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Service to retrieve primes based on an efficient sieve method
 */
@Service("sieve")
public class SievePrimesService implements PrimesService {

    @Override
    public List<Integer> getPrimes(Integer initial) {

        boolean[] prime = new boolean[initial + 1];
        Arrays.fill(prime, Boolean.TRUE);

        for (int p = 2; p * p <= initial; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= initial; i += p)
                    prime[i] = false;
            }
        }

        List<Integer> primes = new ArrayList<>();

        for (Integer i = 2; i <= initial; i++) {
            if (prime[i])
                primes.add(i);
        }
        return primes;
    }
}
