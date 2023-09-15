package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.models.*;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public String createUser(UserInput userInput) {
        return userRepository.createUser(userInput);
    }

    public User getUser(String id) {
        return userRepository.getUser(id);
    }

    public void updateUserName(String id, NameInput nameInput) {
        userRepository.updateUserName(id, nameInput.getName());
    }

    public void updateUserAge(String id, AgeInput ageInput) {
        userRepository.updateUserAge(id, ageInput.getAge());
    }
}
