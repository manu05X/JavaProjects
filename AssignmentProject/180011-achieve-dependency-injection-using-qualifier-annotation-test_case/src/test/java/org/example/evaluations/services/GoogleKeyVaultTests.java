package org.example.evaluations.services;

import org.example.evaluations.evaluation.services.GoogleKeyVault;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class GoogleKeyVaultTests {

    @Autowired
    private GoogleKeyVault keyVault;

    @Test
    void testSaveAndRetrieveSecret() {
        // Arrange
        String secretName = "mySecret";
        String secretValue = "superSecretValue";

        // Act
        keyVault.saveSecret(secretName, secretValue);
        String retrievedValue = keyVault.retrieveSecret(secretName);

        // Assert
        assertEquals(secretValue, retrievedValue, "The retrieved secret value should match the saved secret value.");
    }

    @Test
    void testRetrieveNonSavedSecret() {
        // Arrange
        String secretName = "nonExistentSecret";

        // Act
        String retrievedValue = keyVault.retrieveSecret(secretName);

        // Assert
        assertNull(retrievedValue, "The retrieved value should be null for a non-saved secret.");
    }

    @Test
    void testOverwriteSecret() {
        // Arrange
        String secretName = "overwriteSecret";
        String firstValue = "firstValue";
        String secondValue = "secondValue";

        // Act
        keyVault.saveSecret(secretName, firstValue);
        keyVault.saveSecret(secretName, secondValue);
        String retrievedValue = keyVault.retrieveSecret(secretName);

        // Assert
        assertEquals(secondValue, retrievedValue, "The retrieved secret value should be the most recently saved value.");
    }

    @Test
    void testSaveNullSecretName() {
        //Arrange
        String secretName = null;
        String secretValue = "value";

        //Act
        keyVault.saveSecret(secretName,secretValue);
        String retrievedValue = keyVault.retrieveSecret(secretName);

        //Assert
        assertEquals(retrievedValue,secretValue,"Saving a secret with a null name should work fine and retrieve saved secret value with HashMap");
    }

    @Test
    void testSaveNullSecretValue() {
        // Arrange
        String secretName = "nullValueSecret";

        // Act
        keyVault.saveSecret(secretName, null);
        String retrievedValue = keyVault.retrieveSecret(secretName);

        // Assert
        assertNull(retrievedValue, "The retrieved secret value should be null when the saved secret value is null.");
    }
}
