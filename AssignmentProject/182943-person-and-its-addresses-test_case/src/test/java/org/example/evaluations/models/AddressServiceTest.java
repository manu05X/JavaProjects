package org.example.evaluations.models;


import jakarta.transaction.Transactional;
import org.antlr.v4.runtime.misc.Pair;
import org.example.evaluations.CustomStatementInspector;
import org.example.evaluations.evaluation.repos.AddressRepo;
import org.example.evaluations.evaluation.services.AddressService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@SpringJUnitConfig
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CustomStatementInspector statementInspector;

    @Autowired
    private AddressRepo addressRepo;

    @BeforeEach
    public void setUpForEachMethod() {
        statementInspector.clear();
    }

    @BeforeAll
    public void setUpForClass() {
        jdbcTemplate.execute("INSERT INTO address (id, number, street, city, landmark, state, pincode) VALUES (1, 123, 'Baker Street', 'London', 'Near the park', 'Greater London', 'NW1 6XE')");
        jdbcTemplate.execute("INSERT INTO address (id, number, street, city, landmark, state, pincode) VALUES (2, 456, 'Elm Street', 'Springfield', 'Next to the school', 'Illinois', '62704')");
        jdbcTemplate.execute("INSERT INTO person (id, name, phone_number) VALUES (3, 'John Doe', '123-456-7890')");
        jdbcTemplate.execute("INSERT INTO person (id, name, phone_number) VALUES (4, 'Jane Smith', '987-654-3210') ");
        jdbcTemplate.execute("INSERT INTO person_addresses (persons_id, addresses_id) VALUES (3,1) ");
        jdbcTemplate.execute("INSERT INTO person_addresses (persons_id, addresses_id) VALUES (4,2)");
    }

    @Test
    @Transactional
    public void testGetCityAndPincodeFromAddressId() {
        //Arrange
        Long addressId = 2L;

        //Act
        Pair<String,String> values = addressService.getCityAndPincodeFromAddressId(addressId);

        //Assert
        List<String> executedQueries = statementInspector.getExecutedQueries();
        assertTrue(executedQueries.size()==1,"Only one select query from address table is expected");
        assertTrue(executedQueries.get(0).equals("select a1_0.id,a1_0.city,a1_0.landmark,a1_0.number,a1_0.pincode,a1_0.state,a1_0.street from address a1_0 where a1_0.id=?"),"expected select query");
        assertEquals("Springfield",values.a,"Actual City name doesn't match with expected");
        assertEquals("62704",values.b,"Actual Pincode doesn't match with expected");
    }
}
