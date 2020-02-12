package com.guychisholm.rbs.primes.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Service to retrieve primes based on a basic brute force check
 */
@Service("basic")
@Primary
public class BasicPrimesService implements PrimesService {

    @Override
    public List<Integer> getPrimes(Integer initial) {

        return IntStream
                .rangeClosed(2, initial)
                .parallel()
                .filter(BasicPrimesService::isPrime)
                .boxed()
                .collect(Collectors.toList());
    }

    private static boolean isPrime(Integer number) {
        return number > 1 && IntStream
                // for integers up to the square root
                .rangeClosed(2, (int) Math.sqrt(number))
                // check for factors
                .noneMatch(i -> number % i == 0);
    }
}
