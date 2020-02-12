package com.guychisholm.rbs.primes.service;

class BasicPrimesServiceTest extends PrimesServiceTest<BasicPrimesService>{

    @Override
    protected BasicPrimesService createService() {
        return new BasicPrimesService();
    }
}