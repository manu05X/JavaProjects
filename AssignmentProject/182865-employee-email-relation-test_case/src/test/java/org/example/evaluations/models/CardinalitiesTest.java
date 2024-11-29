package org.example.evaluations.models;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CardinalitiesTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testIfTableWithNameCorporateEmailAddressIsCreated() {
        String tableName = "CORPORATE_EMAIL_ADDRESS";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name CORPORATE_EMAIL_ADDRESS does not exist !");
    }

    @Test
    public void testIfTableWithNameCorporateEmployeeIsCreated() {
        String tableName = "CORPORATE_EMPLOYEE";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name CORPORATE_EMPLOYEE does not exist !");
    }

    @Test
    public void testColumnNamesOfCorporateEmployeeTable() throws SQLException {
        String tableName = "CORPORATE_EMPLOYEE";
        Set<String> expectedColumns = Set.of("ID", "DESIGNATION", "NAME");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table CORPORATE_EMPLOYEE does not contain all expected columns like ID, DESIGNATION, NAME");
    }

    @Test
    public void testColumnNamesOfCorporateEmailAddressTable() throws SQLException {
        String tableName = "CORPORATE_EMAIL_ADDRESS";
        Set<String> expectedColumns = Set.of("CREATED_AT", "EMPLOYEE_ID", "TENANT");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table CORPORATE_EMAIL_ADDRESS does not contain all expected columns like CREATED_AT, EMPLOYEE_ID, TENANT");
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
}
