package org.acme.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import io.quarkus.test.junit.QuarkusTest;
import lombok.SneakyThrows;
import org.acme.models.AgeInput;
import org.acme.models.User;
import org.acme.models.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class UserRepositoryTest {

    private UserRepository userRepository;

    @Mock
    private Firestore mockFirestore;
    @Mock
    private CollectionReference mockCollection;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() throws Exception {
        // Arrange
        UserInput userInput = UserInput.builder()
                .name("John Doe")
                .age(30)
                .build();
        var mockFuture = mock(ApiFuture.class);
        var mockDocumentRef = mock(DocumentReference.class);

        when(mockFirestore.collection("users")).thenReturn(mockCollection);
        when(mockCollection.add(any(User.class))).thenReturn(mockFuture);
        when(mockFuture.get()).thenReturn(mockDocumentRef);
        when(mockDocumentRef.getId()).thenReturn("12345");

        userRepository = new UserRepository(mockFirestore);

        // Act
        String userId = userRepository.createUser(userInput);

        // Assert
        assertEquals("12345", userId);
        verify(mockCollection, times(1)).add(any(User.class));
    }

    @Test
    public void testGetUser() throws Exception {
        // Arrange
        String userId = "12345";
        var mockDocumentSnapshot = mock(DocumentSnapshot.class);
        var mockDocumentRef = mock(DocumentReference.class);
        var mockFuture = mock(ApiFuture.class);

        when(mockFirestore.collection("users")).thenReturn(mockCollection);
        when(mockCollection.document(userId)).thenReturn(mockDocumentRef);
        when(mockDocumentRef.get()).thenReturn(mockFuture);
        when(mockFuture.get()).thenReturn(mockDocumentSnapshot);
        when(mockDocumentSnapshot.exists()).thenReturn(true);
        when(mockDocumentSnapshot.getString("name")).thenReturn("John Doe");
        when(mockDocumentSnapshot.getLong("age")).thenReturn(30L);
        when(mockDocumentSnapshot.getString("profession")).thenReturn("ENGINEER");

        userRepository = new UserRepository(mockFirestore);

        // Act
        User user = userRepository.getUser(userId);

        // Assert
        assertEquals("John Doe", user.getName());
        assertEquals(30, user.getAge());
        assertEquals("ENGINEER", user.getProfession());
        verify(mockDocumentRef, times(1)).get();
    }

    @Test
    @SneakyThrows
    public void testUpdateUserName() {
        // Arrange
        String userId = "12345";
        String newName = "Jane Doe";

        var mockDocumentSnapshot = mock(DocumentSnapshot.class);
        var mockDocumentRef = mock(DocumentReference.class);
        var mockFuture = mock(ApiFuture.class);

        when(mockFirestore.collection("users")).thenReturn(mockCollection);
        when(mockCollection.document(userId)).thenReturn(mockDocumentRef);
        when(mockDocumentRef.get()).thenReturn(mockFuture);
        when(mockFuture.get()).thenReturn(mockDocumentSnapshot);
        when(mockDocumentSnapshot.exists()).thenReturn(true);

        userRepository = new UserRepository(mockFirestore);

        // Act
        userRepository.updateUserName(userId, newName);

        // Assert
        ApiFuture<WriteResult> name = verify(mockDocumentRef, times(1)).set(new HashMap<>(Map.of("name", newName)), SetOptions.merge());
    }

    @Test
    @SneakyThrows
    public void testUpdateUserAge() {
        // Arrange
        String userId = "12345";
        AgeInput newAgeInput = AgeInput.builder().age(30).build();
        var mockDocumentSnapshot = mock(DocumentSnapshot.class);
        var mockDocumentRef = mock(DocumentReference.class);
        var mockFuture = mock(ApiFuture.class);

        when(mockFirestore.collection("users")).thenReturn(mockCollection);
        when(mockCollection.document(userId)).thenReturn(mockDocumentRef);
        when(mockDocumentRef.get()).thenReturn(mockFuture);
        when(mockFuture.get()).thenReturn(mockDocumentSnapshot);
        when(mockDocumentSnapshot.exists()).thenReturn(true);

        userRepository = new UserRepository(mockFirestore);

        // Act
        userRepository.updateUserAge(userId, newAgeInput.getAge());

        // Assert
        verify(mockDocumentRef, times(1)).set(new HashMap<>(Map.of("age", newAgeInput.getAge())), SetOptions.merge());
    }
}
