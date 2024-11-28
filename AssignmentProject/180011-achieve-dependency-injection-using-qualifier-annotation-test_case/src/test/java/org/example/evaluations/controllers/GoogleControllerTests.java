package org.example.evaluations.controllers;

import org.example.evaluations.evaluation.controllers.GoogleController;
import org.example.evaluations.evaluation.dtos.SecretDto;
import org.example.evaluations.evaluation.services.GoogleKeyVault;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class GoogleControllerTests {

    @Autowired
    private GoogleController googleController;

    @MockBean
    private GoogleKeyVault googleKeyVault;


    @Test
    @DisplayName("Testcase to check if you are doing DI using IKeyVault only and not by adding GoogleKeyVault in GoogleController")
    public void testStoreSecretThroughGoogleController() {
        // Arrange
        SecretDto secretDto = new SecretDto();
        secretDto.setName("testName");
        secretDto.setValue("testValue");

        // Act
        googleController.storeSecret(secretDto);

        // Assert
        verify(googleKeyVault,times(0)).saveSecret("secretName","secretValue");
    }
}
