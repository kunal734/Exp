package com.tekpyramid.supportportal.utils;

import com.tekpyramid.supportportal.data.models.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    private ResponseUtil() {}
    private static ApiResponse initApiResponse() {
        return new ApiResponse();
    }

    public static ResponseEntity<ApiResponse> getCreatedResponse(Object response) {
        ApiResponse apiResponse = initApiResponse();
        apiResponse.setHttpStatus(HttpStatus.CREATED);
        apiResponse.setStatusCode(HttpStatus.CREATED.value());
        apiResponse.setResponse(response);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    public static ResponseEntity<ApiResponse> getBadRequestResponse(Object response) {
        ApiResponse apiResponse = initApiResponse();
        apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setResponse(response);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    public static ResponseEntity<ApiResponse> getOkResponse(Object response) {
        ApiResponse apiResponse = initApiResponse();
        apiResponse.setHttpStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setResponse(response);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    public static ResponseEntity<ApiResponse> getConflictResponse(Object response)
    {
        ApiResponse apiResponse=initApiResponse();
        apiResponse.setHttpStatus(HttpStatus.CONFLICT);
        apiResponse.setStatusCode(HttpStatus.CONFLICT.value());
        apiResponse.setResponse(response);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    public static ResponseEntity<ApiResponse> getNotFoundResponse(Object response){
        ApiResponse apiResponse=initApiResponse();
        apiResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setResponse(response);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    public static ResponseEntity<ApiResponse> getNoContentResponse(Object response){
        ApiResponse apiResponse = initApiResponse();
        apiResponse.setResponse(response);
        apiResponse.setHttpStatus(HttpStatus.NO_CONTENT);
        apiResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
