package org.example.evaluations.controllers;

import org.example.evaluations.evaluation.controllers.AzureController;
import org.example.evaluations.evaluation.dtos.SecretDto;
import org.example.evaluations.evaluation.services.AzureKeyVault;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AzureControllerTests {

    @Autowired
    private AzureController azureController;

    @MockBean
    private AzureKeyVault azureKeyVault;

    @Test
    @DisplayName("Testcase to check if you are doing DI using IKeyVault only and not by adding AzureKeyVault in AzureController")
    public void testStoreSecretThroughAzureController() {
        // Arrange
        SecretDto secretDto = new SecretDto();
        secretDto.setName("testName");
        secretDto.setValue("testValue");

        // Act
        azureController.storeSecret(secretDto);

        // Assert
        verify(azureKeyVault,times(0)).saveSecret("secretName","secretValue");
    }
}
