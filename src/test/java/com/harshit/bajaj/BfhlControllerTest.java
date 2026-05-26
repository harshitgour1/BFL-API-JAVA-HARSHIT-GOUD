package com.harshit.bajaj;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshit.bajaj.dto.BfhlRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for POST /bfhl endpoint.
 * Covers all three examples from the Acropolis Campus Hiring spec.
 */
@SpringBootTest
@AutoConfigureMockMvc
class BfhlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // -----------------------------------------------------------------------
    // Example A
    // Input : ["a", "1", "334", "4", "R", "$"]
    // Expected:
    //   odd_numbers      = ["1"]
    //   even_numbers     = ["334", "4"]
    //   alphabets        = ["A", "R"]
    //   special_chars    = ["$"]
    //   sum              = "339"
    //   concat_string    = "Ra"
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("Example A: mixed numbers, alphabets, and special character")
    void testExampleA() throws Exception {
        BfhlRequestDto request = BfhlRequestDto.builder()
                .data(List.of("a", "1", "334", "4", "R", "$"))
                .build();

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.user_id").value("harshit_goud_29092005"))
                .andExpect(jsonPath("$.email").value("harshitgoud230779@acropolis.in"))
                .andExpect(jsonPath("$.roll_number").value("0827IT231051"))
                .andExpect(jsonPath("$.odd_numbers[0]").value("1"))
                .andExpect(jsonPath("$.even_numbers[0]").value("334"))
                .andExpect(jsonPath("$.even_numbers[1]").value("4"))
                .andExpect(jsonPath("$.alphabets[0]").value("A"))
                .andExpect(jsonPath("$.alphabets[1]").value("R"))
                .andExpect(jsonPath("$.special_characters[0]").value("$"))
                .andExpect(jsonPath("$.sum").value("339"))
                .andExpect(jsonPath("$.concat_string").value("Ra"));
    }

    // -----------------------------------------------------------------------
    // Example B
    // Input : ["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"]
    // Expected:
    //   odd_numbers      = ["5"]
    //   even_numbers     = ["2", "4", "92"]
    //   alphabets        = ["A", "Y", "B"]
    //   special_chars    = ["&", "-", "*"]
    //   sum              = "103"
    //   concat_string    = "ByA"
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("Example B: multiple numbers, alphabets, and special characters")
    void testExampleB() throws Exception {
        BfhlRequestDto request = BfhlRequestDto.builder()
                .data(List.of("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"))
                .build();

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.odd_numbers[0]").value("5"))
                .andExpect(jsonPath("$.even_numbers[0]").value("2"))
                .andExpect(jsonPath("$.even_numbers[1]").value("4"))
                .andExpect(jsonPath("$.even_numbers[2]").value("92"))
                .andExpect(jsonPath("$.alphabets[0]").value("A"))
                .andExpect(jsonPath("$.alphabets[1]").value("Y"))
                .andExpect(jsonPath("$.alphabets[2]").value("B"))
                .andExpect(jsonPath("$.special_characters[0]").value("&"))
                .andExpect(jsonPath("$.special_characters[1]").value("-"))
                .andExpect(jsonPath("$.special_characters[2]").value("*"))
                .andExpect(jsonPath("$.sum").value("103"))
                .andExpect(jsonPath("$.concat_string").value("ByA"));
    }

    // -----------------------------------------------------------------------
    // Example C
    // Input : ["A", "ABCD", "DOE"]
    // Expected:
    //   odd_numbers      = []
    //   even_numbers     = []
    //   alphabets        = ["A", "ABCD", "DOE"]
    //   special_chars    = []
    //   sum              = "0"
    //   concat_string    = "EoDdCbAa"
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("Example C: only multi-character alphabetic strings")
    void testExampleC() throws Exception {
        BfhlRequestDto request = BfhlRequestDto.builder()
                .data(List.of("A", "ABCD", "DOE"))
                .build();

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.odd_numbers").isEmpty())
                .andExpect(jsonPath("$.even_numbers").isEmpty())
                .andExpect(jsonPath("$.alphabets[0]").value("A"))
                .andExpect(jsonPath("$.alphabets[1]").value("ABCD"))
                .andExpect(jsonPath("$.alphabets[2]").value("DOE"))
                .andExpect(jsonPath("$.special_characters").isEmpty())
                .andExpect(jsonPath("$.sum").value("0"))
                .andExpect(jsonPath("$.concat_string").value("EoDdCbAa"));
    }

    // -----------------------------------------------------------------------
    // Edge case: empty data array
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("Edge case: empty data array")
    void testEmptyArray() throws Exception {
        BfhlRequestDto request = BfhlRequestDto.builder()
                .data(List.of())
                .build();

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.sum").value("0"))
                .andExpect(jsonPath("$.concat_string").value(""));
    }
}
