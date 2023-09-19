package org.acme.models;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserInput {
        String name;
        int age;
}
