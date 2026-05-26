package com.harshit.bajaj.service;

import com.harshit.bajaj.dto.BfhlRequestDto;
import com.harshit.bajaj.dto.BfhlResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of {@link IBfhlService}.
 *
 * <p>Processes a mixed array of strings and categorises each element into:
 * <ul>
 *   <li>Even numbers</li>
 *   <li>Odd numbers</li>
 *   <li>Alphabets (uppercase)</li>
 *   <li>Special characters</li>
 * </ul>
 * Also computes the numeric sum and the alternating-caps reversed concat string.
 */
@Service
public class BfhlServiceImpl implements IBfhlService {

    // -----------------------------------------------------------------------
    // Student metadata — hardcoded as per assignment specification
    // -----------------------------------------------------------------------
    private static final String USER_ID     = "harshit_goud_29092005";
    private static final String EMAIL       = "harshitgoud230779@acropolis.in";
    private static final String ROLL_NUMBER = "0827IT231051";

    // -----------------------------------------------------------------------
    // Public API
    // -----------------------------------------------------------------------

    @Override
    public BfhlResponseDto process(BfhlRequestDto request) {
        List<String> data = (request == null || request.getData() == null) 
                ? Collections.emptyList() 
                : request.getData();

        List<String> evenNumbers       = new ArrayList<>();
        List<String> oddNumbers        = new ArrayList<>();
        List<String> alphabets         = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        java.math.BigInteger numericSum = java.math.BigInteger.ZERO;

        // Individual alphabetical characters collected in input order for concat_string
        List<Character> allAlphaChars = new ArrayList<>();

        for (String element : data) {
            if (element == null) {
                continue;
            }

            if (isNumeric(element)) {
                // --- Numeric element ---
                java.math.BigInteger value = new java.math.BigInteger(element);
                numericSum = numericSum.add(value);

                // Check if even (value mod 2 == 0)
                if (value.mod(java.math.BigInteger.TWO).equals(java.math.BigInteger.ZERO)) {
                    evenNumbers.add(element);
                } else {
                    oddNumbers.add(element);
                }

            } else if (isAlphabetic(element)) {
                // --- Alphabetic element (single or multi-char, e.g. "ABCD") ---
                alphabets.add(element.toUpperCase());

                // Collect individual chars for concat_string
                for (char c : element.toCharArray()) {
                    allAlphaChars.add(c);
                }

            } else {
                // --- Special character element ---
                specialCharacters.add(element);

                // Collect any alphabetical chars embedded in special elements
                for (char c : element.toCharArray()) {
                    if (Character.isLetter(c)) {
                        allAlphaChars.add(c);
                    }
                }
            }
        }

        String concatString = buildConcatString(allAlphaChars);

        return BfhlResponseDto.builder()
                .isSuccess(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .evenNumbers(evenNumbers)
                .oddNumbers(oddNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(numericSum.toString())
                .concatString(concatString)
                .build();
    }


    // -----------------------------------------------------------------------
    // Private helpers
    // -----------------------------------------------------------------------

    /**
     * Returns {@code true} if the element consists entirely of digits (non-negative integer).
     * Handles negative numbers by checking for optional leading '-'.
     */
    private boolean isNumeric(String element) {
        if (element == null || element.isEmpty()) return false;
        int start = 0;
        if (element.charAt(0) == '-') {
            if (element.length() == 1) return false; // bare '-' is not numeric
            start = 1;
        }
        for (int i = start; i < element.length(); i++) {
            if (!Character.isDigit(element.charAt(i))) return false;
        }
        return true;
    }

    /**
     * Returns {@code true} if the element consists entirely of alphabetical characters.
     */
    private boolean isAlphabetic(String element) {
        if (element == null || element.isEmpty()) return false;
        for (char c : element.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    /**
     * Builds the concat_string:
     * <ol>
     *   <li>Take all individual alphabetical characters collected from the input (in order).</li>
     *   <li>Reverse the list.</li>
     *   <li>Apply alternating capitalisation: index 0 → UPPER, 1 → lower, 2 → UPPER, …</li>
     *   <li>Concatenate into a single String.</li>
     * </ol>
     *
     * <p>Examples (verified against spec):
     * <pre>
     *   ["a","R"]      → reverse → ['R','a'] → alt caps → "Ra"
     *   ["a","y","b"]  → reverse → ['b','y','a'] → alt caps → "ByA"
     *   [A,A,B,C,D,D,O,E] → reverse → [E,O,D,D,C,B,A,A] → "EoDdCbAa"
     * </pre>
     */
    private String buildConcatString(List<Character> alphaChars) {
        if (alphaChars.isEmpty()) return "";

        // Step 1: reverse
        Collections.reverse(alphaChars);

        // Step 2: alternating caps
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < alphaChars.size(); i++) {
            char c = alphaChars.get(i);
            if (i % 2 == 0) {
                sb.append(Character.toUpperCase(c)); // even index → UPPER
            } else {
                sb.append(Character.toLowerCase(c)); // odd index  → lower
            }
        }
        return sb.toString();
    }
}
