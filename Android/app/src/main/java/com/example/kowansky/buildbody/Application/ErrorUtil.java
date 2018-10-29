package com.example.kowansky.buildbody.Application;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtil {
    public static ValidationError parseError(Response<?> response) {
        Converter<ResponseBody, ValidationError> converter =
                ApiClient.getApiClient().responseBodyConverter(ValidationError.class, new Annotation[0]);

        ValidationError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ValidationError();
        }

        return error;
    }
}
