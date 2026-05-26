package com.harshit.bajaj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Response DTO for POST /bfhl endpoint.
 * Contains categorized data derived from the input array along with student metadata.
 */
public class BfhlResponseDto {

    @JsonProperty("is_success")
    private boolean isSuccess;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("roll_number")
    private String rollNumber;

    @JsonProperty("odd_numbers")
    private List<String> oddNumbers;

    @JsonProperty("even_numbers")
    private List<String> evenNumbers;

    @JsonProperty("alphabets")
    private List<String> alphabets;

    @JsonProperty("special_characters")
    private List<String> specialCharacters;

    /** Sum of all numeric elements in the input, returned as a String. */
    @JsonProperty("sum")
    private String sum;

    /**
     * Concatenation of all individual alphabetical characters from the input,
     * taken in reverse order with alternating capitalisation (UPPER, lower, UPPER, ...).
     */
    @JsonProperty("concat_string")
    private String concatString;

    public BfhlResponseDto() {}

    // Getters
    public boolean isSuccess() { return isSuccess; }
    public String getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getRollNumber() { return rollNumber; }
    public List<String> getOddNumbers() { return oddNumbers; }
    public List<String> getEvenNumbers() { return evenNumbers; }
    public List<String> getAlphabets() { return alphabets; }
    public List<String> getSpecialCharacters() { return specialCharacters; }
    public String getSum() { return sum; }
    public String getConcatString() { return concatString; }

    // Setters
    public void setSuccess(boolean success) { isSuccess = success; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setEmail(String email) { this.email = email; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public void setOddNumbers(List<String> oddNumbers) { this.oddNumbers = oddNumbers; }
    public void setEvenNumbers(List<String> evenNumbers) { this.evenNumbers = evenNumbers; }
    public void setAlphabets(List<String> alphabets) { this.alphabets = alphabets; }
    public void setSpecialCharacters(List<String> specialCharacters) { this.specialCharacters = specialCharacters; }
    public void setSum(String sum) { this.sum = sum; }
    public void setConcatString(String concatString) { this.concatString = concatString; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private boolean isSuccess;
        private String userId;
        private String email;
        private String rollNumber;
        private List<String> oddNumbers;
        private List<String> evenNumbers;
        private List<String> alphabets;
        private List<String> specialCharacters;
        private String sum;
        private String concatString;

        public Builder isSuccess(boolean isSuccess) { this.isSuccess = isSuccess; return this; }
        public Builder userId(String userId) { this.userId = userId; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder rollNumber(String rollNumber) { this.rollNumber = rollNumber; return this; }
        public Builder oddNumbers(List<String> oddNumbers) { this.oddNumbers = oddNumbers; return this; }
        public Builder evenNumbers(List<String> evenNumbers) { this.evenNumbers = evenNumbers; return this; }
        public Builder alphabets(List<String> alphabets) { this.alphabets = alphabets; return this; }
        public Builder specialCharacters(List<String> sc) { this.specialCharacters = sc; return this; }
        public Builder sum(String sum) { this.sum = sum; return this; }
        public Builder concatString(String concatString) { this.concatString = concatString; return this; }

        public BfhlResponseDto build() {
            BfhlResponseDto dto = new BfhlResponseDto();
            dto.isSuccess = this.isSuccess;
            dto.userId = this.userId;
            dto.email = this.email;
            dto.rollNumber = this.rollNumber;
            dto.oddNumbers = this.oddNumbers;
            dto.evenNumbers = this.evenNumbers;
            dto.alphabets = this.alphabets;
            dto.specialCharacters = this.specialCharacters;
            dto.sum = this.sum;
            dto.concatString = this.concatString;
            return dto;
        }
    }
}
