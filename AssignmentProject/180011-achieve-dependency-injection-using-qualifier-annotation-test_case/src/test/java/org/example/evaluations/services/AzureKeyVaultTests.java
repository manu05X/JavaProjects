package org.example.evaluations.services;

import org.example.evaluations.evaluation.services.AzureKeyVault;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AzureKeyVaultTests {

    @Autowired
    private AzureKeyVault keyVault;

    @Test
    void testSaveAndRetrieveSecret() {
        // Arrange
        String secretName = "mySecret";
        String secretValue = "superSecretValue";

        // Act
        keyVault.saveSecret(secretName, secretValue);
        String retrievedValue = keyVault.retrieveSecret(secretName);

        // Assert
        assertEquals(secretValue, retrievedValue, "The retrieved secret value should match the saved value.");
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
        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            keyVault.saveSecret(null, "value");
        }, "Saving a secret with a null name should throw a NullPointerException with TreeMap.");
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

    @Test
    void testKeysOrder() {
        // Arrange
        keyVault.saveSecret("bSecret", "valueB");
        keyVault.saveSecret("aSecret", "valueA");
        keyVault.saveSecret("cSecret", "valueC");

        // Act
        String firstKey = keyVault.retrieveSecret("aSecret");
        String secondKey = keyVault.retrieveSecret("bSecret");
        String thirdKey = keyVault.retrieveSecret("cSecret");

        // Assert
        assertEquals("valueA", firstKey, "The key 'aSecret' should be first due to natural order.");
        assertEquals("valueB", secondKey, "The key 'bSecret' should be second due to natural order.");
        assertEquals("valueC", thirdKey, "The key 'cSecret' should be last due to natural order.");
    }
}
