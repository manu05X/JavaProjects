package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;

@Entity
@AttributeOverride(
        name = "owner",
        column = @Column(name = "CREDIT_CARD_OWNER", nullable = false)
)
public class CreditCard extends PurchaseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String cardNumber;

    protected Long creditLimit;
}
