package com.guychisholm.rbs.primes.controller;

import com.guychisholm.rbs.primes.service.PrimesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PrimesController.class)
class PrimesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrimesService service;

    @Mock
    private ApplicationContext context;

    @Test
    public void initialPathValuePassedToService() throws Exception {
        // when
        this.mockMvc.perform(get("/primes/10"));

        // then
        verify(service).getPrimes(10);
    }

    @Test
    public void primeRequestWithInitialShouldReturnPrimes() throws Exception {
        // given
        List<Integer> mockPrimes = Arrays.asList(2, 3, 5, 7);
        when(service.getPrimes(10)).thenReturn(mockPrimes);

        // when
        this.mockMvc.perform(get("/primes/10"))

         // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.initial", is(10)))
                .andExpect(jsonPath("$.primes", contains(2, 3, 5, 7)));
    }

    @Test
    public void primeRequestWithInitialShouldReturnPrimesinXml() throws Exception {
        // given
        List<Integer> mockPrimes = Arrays.asList(2, 3, 5, 7);
        when(service.getPrimes(10)).thenReturn(mockPrimes);

        // when
        this.mockMvc.perform(get("/primes/10").header("Accept", "application/xml"))

         // then
                .andExpect(status().isOk())
                .andExpect(xpath("/Primes/initial").string(is("10")))
                .andExpect(xpath("/Primes/primes").string(is("2357")));
    }

    @Test
    public void unsupportedAlgorithRequestReturns4xx() throws Exception {
        // when
        this.mockMvc.perform(get("/primes/10").param("algorithm","unsupported"))

                // then
                .andExpect(status().is4xxClientError());
    }
}