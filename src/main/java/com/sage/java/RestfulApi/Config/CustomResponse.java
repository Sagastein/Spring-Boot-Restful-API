package com.sage.java.RestfulApi.Config;

import lombok.Getter;

@Getter
public class CustomResponse {
    private final String error;

    public CustomResponse(String error) {
        this.error = error;
    }

}

