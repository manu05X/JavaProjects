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
    public void testIfTableWithNameAuth_CredentialsIsCreated() {
        String tableName = "AUTH_CREDENTIALS";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name AUTH_CREDENTIALS does not exist !");
    }

    @Test
    public void testIfTableWithNameUsersIsCreated() {
        String tableName = "USERS";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name USERS does not exist !");
    }

    @Test
    public void testIfTableWithNameUser_Login_SessionsIsCreated() {
        String tableName = "USER_LOGIN_SESSIONS";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name USER_LOGIN_SESSIONS does not exist !");
    }

    @Test
    public void testColumnNamesOfAuth_CredentialsTable() throws SQLException {
        String tableName = "AUTH_CREDENTIALS";
        Set<String> expectedColumns = Set.of("EMAIL", "PASSWORD");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table AUTH_CREDENTIALS does not contain all expected columns like EMAIL, PASSWORD");
    }

    @Test
    public void testColumnNamesOfUsersTable() throws SQLException {
        String tableName = "USERS";
        Set<String> expectedColumns = Set.of("ID", "ADDRESS", "FIRST_NAME", "AUTH_CREDENTIAL_EMAIL", "LAST_NAME", "PHONE_NUMBER");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table USERS does not contain all expected columns like ID, ADDRESS, AUTH_CREDENTIAL_EMAIL, FIRSTNAME, LASTNAME, PHONE_NUMBER");
    }

    @Test
    public void testColumnNamesOfUser_Login_SessionsTable() throws SQLException {
        String tableName = "USER_LOGIN_SESSIONS";
        Set<String> expectedColumns = Set.of("SESSION_STATE", "ID", "TTL", "USER_ID", "TOKEN");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table USER_LOGIN_SESSIONS does not contain all expected columns like SESSION_STATE, ID, TTL, USER_ID, TOKEN");
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
