package com.harshit.bajaj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Request DTO for POST /bfhl endpoint.
 * Accepts a mixed array of strings representing numbers, alphabets, and special characters.
 */
public class BfhlRequestDto {

    @JsonProperty("data")
    private List<String> data;

    public BfhlRequestDto() {}

    public BfhlRequestDto(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<String> data;

        public Builder data(List<String> data) {
            this.data = data;
            return this;
        }

        public BfhlRequestDto build() {
            return new BfhlRequestDto(data);
        }
    }
}
