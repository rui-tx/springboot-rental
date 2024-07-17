package com.ruitx.babyboot.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Error {
    private final String timestamp;
    private final String message;
    private final String method;
    private final String path;

    private Error(Builder builder) {
        this.timestamp = builder.timestamp;
        this.message = builder.message;
        this.method = builder.method;
        this.path = builder.path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    // Method to convert Error object to JSON string
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    public static class Builder {
        private String timestamp;
        private String message;
        private String method;
        private String path;

        public Builder() {
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Error build() {

            return new Error(this);
        }
    }
}
