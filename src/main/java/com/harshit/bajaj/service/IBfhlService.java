package com.harshit.bajaj.service;

import com.harshit.bajaj.dto.BfhlRequestDto;
import com.harshit.bajaj.dto.BfhlResponseDto;

/**
 * Service interface for BFHL data processing.
 * Defines the contract for processing the mixed input array.
 */
public interface IBfhlService {

    /**
     * Processes the input data array and returns a categorised response.
     *
     * @param request the request containing the mixed string array
     * @return fully populated {@link BfhlResponseDto}
     */
    BfhlResponseDto process(BfhlRequestDto request);
}
