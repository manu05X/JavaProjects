package org.example.evaluations.evaluation.services;

public interface IKeyVault {
    void saveSecret(String secretName,String secretValue);

    String retrieveSecret(String secretName);
}
