package com.guychisholm.rbs.primes.service;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class PrimesServiceTest<T extends PrimesService> {

    private T service;

    protected abstract T createService();

    @BeforeAll
    public void setUp() {
        service = createService();
    }

    @Test
    void initial1ShouldReturnEmpty() {
        List<Integer> primes = service.getPrimes(1);

        assertThat(primes, IsEmptyCollection.empty());
    }

    @Test
    void initial2ShouldReturnsJust2() {
        List<Integer> primes = service.getPrimes(2);

        assertThat(primes, contains(2));
    }

    @Test
    void initialOf10ReturnsSizeOf4() {
        List<Integer> primes = service.getPrimes(10);

        assertThat(primes, contains(2,3,5,7));
    }

    @Test
    void initialOf11ShouldInclude11() {
        List<Integer> primes = service.getPrimes(11);

        assertThat(primes, contains(2,3,5,7,11));
    }
}
