package com.utn.UTNphones.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponseDto {

    @JsonProperty
    int code;
    @JsonProperty
    String description;
    @JsonProperty
    Throwable cause;

    public ErrorResponseDto(int code, String description,Throwable cause) {
        this.code = code;
        this.description = description;
        this.cause=cause;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}