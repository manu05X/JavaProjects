package org.example.evaluations.models;

import jakarta.transaction.Transactional;
import org.example.evaluations.CustomStatementInspector;
import org.example.evaluations.evaluation.repos.PersonRepo;
import org.example.evaluations.evaluation.services.PersonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@SpringJUnitConfig
public class PersonServiceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CustomStatementInspector statementInspector;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepo personRepo;

    @BeforeEach
    public void setUpForEachMethod() {
        statementInspector.clear();
    }

    @BeforeAll
    public void setUpForClass() {
        jdbcTemplate.execute("INSERT INTO address (id, number, street, city, landmark, state, pincode) VALUES (10, 123, 'Baker Street', 'London', 'Near the park', 'Greater London', 'NW1 6XE')");
        jdbcTemplate.execute("INSERT INTO address (id, number, street, city, landmark, state, pincode) VALUES (20, 456, 'Elm Street', 'Springfield', 'Next to the school', 'Illinois', '62704')");
        jdbcTemplate.execute("INSERT INTO person (id, name, phone_number) VALUES (30, 'John Doe', '123-456-7890')");
        jdbcTemplate.execute("INSERT INTO person (id, name, phone_number) VALUES (40, 'Jane Smith', '987-654-3210') ");
        jdbcTemplate.execute("INSERT INTO person_addresses (persons_id, addresses_id) VALUES (40,10) ");
        jdbcTemplate.execute("INSERT INTO person_addresses (persons_id, addresses_id) VALUES (30,20)");
    }

    @Test
    @Transactional
    public void testGetAllUniqueCities() {
        //Act
        Set<String> values  = personService.getAllUniqueCities();

        //Assert
        List<String> executedQueries = statementInspector.getExecutedQueries();
        assertTrue(executedQueries.size()==2,"Only 2 queries are expected out of which 1 will be subquery and one will be select query.");
        assertTrue(executedQueries.get(1).equals("select a1_0.persons_id,a1_1.id,a1_1.city,a1_1.landmark,a1_1.number,a1_1.pincode,a1_1.state,a1_1.street from person_addresses a1_0 join address a1_1 on a1_1.id=a1_0.addresses_id where a1_0.persons_id in (select p1_0.id from person p1_0)"),"expected subquery");
        assertEquals(2,values.size(),"There will be 2 unique cities.");
    }


    @Test
    @Transactional
    public void testGetCitiesWherePersonLivedAt() {
        //Act
        Set<String> values = personService.getCitiesWherePersonLivedAt(3L);

        //Assert
        List<String> executedQueries = statementInspector.getExecutedQueries();
        assertTrue(executedQueries.size()==2,"Only 2 queries are expected out of which 1 will be join and one will be select query.");
        assertTrue(executedQueries.get(1).equals("select a1_0.persons_id,a1_1.id,a1_1.city,a1_1.landmark,a1_1.number,a1_1.pincode,a1_1.state,a1_1.street from person_addresses a1_0 join address a1_1 on a1_1.id=a1_0.addresses_id where a1_0.persons_id=?"),"expected join query because of ManytoMany relationship");
        assertEquals(1,values.size(),"There is only 1 city at which person with id 3 has lived");
    }
}
