package org.example.evaluations.evaluation.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Cart {
    private Long id;
    private List<String> uniqueItems;
    private Double totalValue;
    private String friendlyName;
    private User user;

    public Cart(Long id) {
        this.id = id;
    }

    public Cart(Long id,Long userId) {
        this.id = id;
        User user1 = new User();
        user1.setId(userId);
        this.user = user1;
    }
}
