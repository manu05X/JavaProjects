package org.example.evaluations.evaluation.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("googlekvimpl")
public class GoogleKeyVault implements IKeyVault{
    private Map<String,String> container;

    public GoogleKeyVault() {
        this.container = new HashMap<>();
    }

    @Override
    public void saveSecret(String secretName, String secretValue) {
        container.put(secretName,secretValue);
    }

    @Override
    public String retrieveSecret(String secretName) {
        return container.get(secretName);
    }
}
