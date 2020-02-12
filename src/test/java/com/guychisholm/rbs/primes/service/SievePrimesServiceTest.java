package com.guychisholm.rbs.primes.service;

class SievePrimesServiceTest extends PrimesServiceTest<SievePrimesService> {

    @Override
    protected SievePrimesService createService() {
        return new SievePrimesService();
    }
}