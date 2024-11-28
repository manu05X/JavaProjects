package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.SecretDto;
import org.example.evaluations.evaluation.services.IKeyVault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/impl/api/azure/secrets")
public class AzureController {

    @Autowired
    @Qualifier("azurekvimpl")
    private IKeyVault keyVault;

    @PostMapping
    public void storeSecret(@RequestBody SecretDto secret) {
       keyVault.saveSecret(secret.getName(),secret.getValue());
    }

    @GetMapping("/{name}")
    public String getSecret(@PathVariable("name") String secretName) {
        return keyVault.retrieveSecret(secretName);
    }
}
