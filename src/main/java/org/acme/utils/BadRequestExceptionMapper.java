package org.acme.utils;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {
    public Response toResponse(BadRequestException e) {
        // Create a new Response object with the desired status code and message
        return Response.status(400).entity("Invalid request: " + e.getMessage()).build();
    }
}