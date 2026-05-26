package com.harshit.bajaj.controller;

import com.harshit.bajaj.dto.BfhlRequestDto;
import com.harshit.bajaj.dto.BfhlResponseDto;
import com.harshit.bajaj.service.IBfhlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller exposing /bfhl and /health endpoints.
 */
@RestController
public class BfhlController {

    private final IBfhlService bfhlService;

    public BfhlController(IBfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    /**
     * GET /health — returns service health status.
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "user_id", "harshit_goud_29092005",
                "email", "harshitgoud230779@acropolis.in",
                "roll_number", "0827IT231051"
        ));
    }

    /**
     * POST /bfhl — processes the input array and returns categorised data.
     *
     * @param request the request body containing {@code data} array
     * @return 200 OK with the populated response DTO
     */
    @PostMapping("/bfhl")
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
