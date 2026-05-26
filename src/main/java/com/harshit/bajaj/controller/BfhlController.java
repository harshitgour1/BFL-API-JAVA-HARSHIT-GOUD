package com.harshit.bajaj.controller;

import com.harshit.bajaj.dto.BfhlRequestDto;
import com.harshit.bajaj.dto.BfhlResponseDto;
import com.harshit.bajaj.service.IBfhlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller exposing the /bfhl endpoint.
 *
 * <p>POST /bfhl — accepts a mixed array of strings and returns categorised data.
 */
@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private final IBfhlService bfhlService;

    public BfhlController(IBfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    /**
     * POST /bfhl
     *
     * <p>Processes the input array and returns:
     * <ul>
     *   <li>is_success, user_id, email, roll_number</li>
     *   <li>even_numbers, odd_numbers, alphabets, special_characters</li>
     *   <li>sum, concat_string</li>
     * </ul>
     *
     * @param request the request body containing {@code data} array
     * @return 200 OK with the populated response DTO
     */
    @PostMapping
    public ResponseEntity<BfhlResponseDto> handlePost(@RequestBody BfhlRequestDto request) {
        BfhlResponseDto response = bfhlService.process(request);
        return ResponseEntity.ok(response);
    }

    // -----------------------------------------------------------------------
    // Global exception handler — returns is_success: false on any error
    // -----------------------------------------------------------------------

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        Map<String, Object> errorResponse = Map.of(
                "is_success", false,
                "error", ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
