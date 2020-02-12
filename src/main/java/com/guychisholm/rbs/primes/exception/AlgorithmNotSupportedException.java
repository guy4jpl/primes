package com.guychisholm.rbs.primes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Unsupported algorithm for prime number generation")
public class AlgorithmNotSupportedException extends RuntimeException  {
}
