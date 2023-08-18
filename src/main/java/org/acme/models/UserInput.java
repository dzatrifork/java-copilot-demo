package org.acme.models;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserInput {

        private String name;
        private int age;
        private Profession profession;
}
