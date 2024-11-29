package org.example.evaluations.models;

import jakarta.persistence.Id;
import org.example.evaluations.evaluation.models.ContractualEmployee;
import org.example.evaluations.evaluation.models.PermanentEmployee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class TablesTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testIfTableWithNameContractualEmployeeIsCreated() {
        String tableName = "CONTRACTUAL_EMPLOYEE";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name CONTRACTUAL_EMPLOYEE does not exist !");
    }

    @Test
    public void testIfTableWithNamePermanentEmployeeIsCreated() {
        String tableName = "PERMANENT_EMPLOYEE";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name PERMANENT_EMPLOYEE does not exist !");
    }

    @Test
    public void testIfTableWithNameInstructorIsCreated() {
        String tableName = "INSTRUCTOR";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name INSTRUCTOR does not exist !");
    }

    @Test
    public void testIfTableWithNameTaIsCreated() {
        String tableName = "TA";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name TA does not exist !");
    }

    @Test
    public void testIfTableWithNameSoftwareDeveloperIsCreated() {
        String tableName = "SOFTWARE_DEVELOPER";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name SOFTWARE_DEVELOPER does not exist !");
    }

    @Test
    public void testIfTableWithNameProgramManagerIsCreated() {
        String tableName = "PROGRAM_MANAGER";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name PROGRAM_MANAGER does not exist !");
    }

    @Test
    public void testIfTableWithNameEmployeeIsNotCreated() {
        String tableName = "EMPLOYEE";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count == 0, "Table with name EMPLOYEE exist !");
    }

    @Test
    public void testColumnNamesOfContractualEmployeeTable() throws SQLException {
        String tableName = "CONTRACTUAL_EMPLOYEE";
        Set<String> expectedColumns = Set.of("ID","HOURLY_RENUMERATION", "ADDRESS", "ALIAS", "FIRST_NAME", "LAST_NAME", "PHONE_NUMBER");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table CONTRACTUAL_EMPLOYEE does not contain all expected columns like HOURLY_RENUMERATION, ADDRESS, ALIAS, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ID");
    }

    @Test
    public void testColumnNamesOfPermanentEmployeeTable() throws SQLException {
        String tableName = "PERMANENT_EMPLOYEE";
        Set<String> expectedColumns = Set.of("ID","COST_TO_COMPANY", "ADDRESS", "EMAIL", "FIRST_NAME", "LAST_NAME", "PHONE_NUMBER");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table PERMANENT_EMPLOYEE does not contain all expected columns like COST_TO_COMPANY, ADDRESS, EMAIL, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ID");
    }

    @Test
    public void testColumnNamesOfInstructorTable() throws SQLException {
        String tableName = "INSTRUCTOR";
        Set<String> expectedColumns = Set.of("ID","HOURLY_RENUMERATION", "ADDRESS", "ALIAS", "FIRST_NAME", "LAST_NAME", "PHONE_NUMBER", "COMPANY");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table INSTRUCTOR does not contain all expected columns like HOURLY_RENUMERATION, ADDRESS, ALIAS, FIRST_NAME, LAST_NAME, PHONE_NUMBER, COMPANY, ID");
    }

    @Test
    public void testColumnNamesOfTaTable() throws SQLException {
        String tableName = "TA";
        Set<String> expectedColumns = Set.of("ID","HOURLY_RENUMERATION", "ADDRESS", "ALIAS", "FIRST_NAME", "LAST_NAME", "PHONE_NUMBER","NUMBER_OF_HELP_REQUESTS");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table TA does not contain all expected columns like HOURLY_RENUMERATION, ADDRESS, ALIAS, FIRST_NAME, LAST_NAME, PHONE_NUMBER, NUMBER_OF_HELP_REQUESTS, ID");
    }

    @Test
    public void testColumnNamesOfSoftwareDeveloperTable() throws SQLException {
        String tableName = "SOFTWARE_DEVELOPER";
        Set<String> expectedColumns = Set.of("ID","COST_TO_COMPANY", "ADDRESS", "EMAIL", "FIRST_NAME", "LAST_NAME", "PHONE_NUMBER","LEAVES_TAKEN");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table SOFTWARE_DEVELOPER does not contain all expected columns like COST_TO_COMPANY, ADDRESS, EMAIL, FIRST_NAME, LAST_NAME, PHONE_NUMBER, LEAVES_TAKEN, ID");
    }

    @Test
    public void testColumnNamesOfProgramManagerTable() throws SQLException {
        String tableName = "PROGRAM_MANAGER";
        Set<String> expectedColumns = Set.of("ID","COST_TO_COMPANY", "ADDRESS", "EMAIL", "FIRST_NAME", "LAST_NAME", "PHONE_NUMBER","FEATURES_COMPLETED");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table PROGRAM_MANAGER does not contain all expected columns like COST_TO_COMPANY, ADDRESS, EMAIL, FIRST_NAME, LAST_NAME, PHONE_NUMBER, FEATURES_COMPLETED, ID");
    }

    private Set<String> getColumnNames(String tableName) throws SQLException {
        Set<String> columns = new HashSet<>();
        DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();

        try (ResultSet rs = metaData.getColumns(null, null, tableName, null)) {
            while (rs.next()) {
                columns.add(rs.getString("COLUMN_NAME"));
            }
        }
        return columns;
    }

    private boolean validateColumns(String tableName, Set<String> expectedColumns) throws SQLException {
        Set<String> actualColumns = getColumnNames(tableName);
        return actualColumns.containsAll(expectedColumns);
    }

    @Test
    public void testIfAliasFieldAnnotatedWithIdInContractualEmployeeTable() {
        Class<?> clazz = ContractualEmployee.class;

        boolean hasIdAnnotation = hasIdAnnotation(clazz, "alias");

        assertThat(hasIdAnnotation).isTrue();
    }

    @Test
    public void testIfEmailFieldIsAnnotatedWithIdInPermanentEmployeeTable() {
        Class<?> clazz = PermanentEmployee.class;

        boolean hasIdAnnotation = hasIdAnnotation(clazz, "email");

        assertThat(hasIdAnnotation).isTrue();
    }

    public static boolean hasIdAnnotation(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            return field.isAnnotationPresent(Id.class);
        } catch (NoSuchFieldException e) {
            return false;
        }
    }
}

