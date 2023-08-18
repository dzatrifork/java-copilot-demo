package org.acme.services;

import com.google.cloud.firestore.*;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import lombok.SneakyThrows;
import org.acme.models.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Dependent
public class UserRepository {
    Firestore firestore;
    CollectionReference userCollection;


    @Inject
    UserRepository(Firestore firestore) {
        this.firestore = firestore;
        this.userCollection = firestore.collection("users");
    }

    @SneakyThrows
    public String createUser(UserInput userInput) {
        User user = User.builder()
                .name(userInput.getName())
                .age(userInput.getAge())
                .profession(userInput.getProfession().toString())
                .build();
        var future = userCollection.add(user);
        return future.get().getId();
    }

    public User getUser(String id) {
        var userDocument = assertUserExists(id);
        return User.builder()
                .name(userDocument.getString("name"))
                .age(userDocument.getLong("age").intValue())
                .profession(userDocument.getString("profession"))
                .build();

    }

    public void updateUserName(String id, String name) {
        assertUserExists(id);
        userCollection.document(id).set(new HashMap<>(Map.of("name", name)), SetOptions.merge());
    }

    public void updateUserAge(String id, int age) {
        assertUserExists(id);
        userCollection.document(id).set(new HashMap<>(Map.of("age", age)), SetOptions.merge());
    }

    public void updateUserProfession(String id, Profession profession) {
        assertUserExists(id);
        userCollection.document(id).set(new HashMap<>(Map.of("profession", profession.toString())), SetOptions.merge());
    }

    public DocumentSnapshot assertUserExists(String id) {
        try {
            DocumentSnapshot documentSnapshot = userCollection.document(id).get().get();
            if (!documentSnapshot.exists()) {
                throw new NotFoundException(String.format("User with id %s not found", id));
            }
            return documentSnapshot;
        } catch (ExecutionException | InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
