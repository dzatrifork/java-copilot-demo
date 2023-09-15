package org.acme.utils;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Provider
public class BadWordListener implements ContainerRequestFilter {
    private static final List<String> BAD_WORDS = List.of("fuck", "shit");

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        String jsonContent = IOUtils.toString(request.getEntityStream(), StandardCharsets.UTF_8);
        boolean containsBadWord = BAD_WORDS.stream().anyMatch(jsonContent::contains);
        if (containsBadWord) {
            throw new BadRequestException("No bad words allowed!");
        }
    }

    private static boolean isValidWord(String word) {
        return Arrays.asList("hello", "world").contains(word);
    }
}