package com.guychisholm.rbs.primes;

import com.guychisholm.rbs.primes.service.PrimesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * These tests help us populate our API, plus they test component integration
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class ApiDocumentation {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PrimesService service;

    @Test
    public void request10Primes() throws Exception {
        mockMvc.perform(get("/primes/{initial}", 10))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        pathParameters(
                            parameterWithName("initial").description("Return primes up to this number"))
                        )
                );
    }

    @Test
    public void request10PrimesInXml() throws Exception {
        mockMvc.perform(get("/primes/{initial}", 10).header("Accept", "application/xml"))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}/xml",
                        pathParameters(
                            parameterWithName("initial").description("Return primes up to this number"))
                        )
                );
    }

    @Test
    public void request10PrimesWithSieve() throws Exception {
        mockMvc.perform(get("/primes/{initial}", 10).param("algorithm", "sieve"))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        pathParameters(
                                parameterWithName("initial").description("Return primes up to this number")),
                        queryParameters(
                                parameterWithName("algorithm").description("Algorithm used to generate primes"))
                        )
                );
    }

    @Test
    public void requestUnsupportedFails() throws Exception {
        mockMvc.perform(get("/primes/{initial}", 10).param("algorithm", "unsupported"))
                .andExpect(status().is4xxClientError())
                .andDo(document("{class-name}/{method-name}",
                        pathParameters(
                                parameterWithName("initial").description("Return primes up to this number")),
                        queryParameters(
                                parameterWithName("algorithm").description("Algorithm used to generate primes"))
                        )
                );
    }

    @Test
    public void requestTooManyPrimes() throws Exception {
        mockMvc.perform(get("/primes/{initial}", 1_000_000_001).param("algorithm", "unsupported"))
                .andExpect(status().is4xxClientError())
                .andDo(document("{class-name}/{method-name}",
                        pathParameters(
                                parameterWithName("initial").description("Return primes up to this number")),
                        queryParameters(
                                parameterWithName("algorithm").description("Algorithm used to generate primes"))
                        )
                );
    }
}
