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
public class TablesTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testIfTableWithNameAccountIsCreated() {
        String tableName = "ACCOUNT";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name ACCOUNT does not exist !");
    }

    @Test
    public void testIfTableWithNameDebitAccountIsCreated() {
        String tableName = "DEBIT_ACCOUNT";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name DEBIT_ACCOUNT does not exist !");
    }

    @Test
    public void testIfTableWithNameCreditAccountIsCreated() {
        String tableName = "CREDIT_ACCOUNT";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name CREDIT_ACCOUNT does not exist !");
    }

    @Test
    public void testIfTableWithNameCreditCardIsCreated() {
        String tableName = "CREDIT_CARD";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name CREDIT_CARD does not exist !");
    }

    @Test
    public void testIfTableWithNameDebitCardIsCreated() {
        String tableName = "DEBIT_CARD";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name DEBIT_CARD does not exist !");
    }

    @Test
    public void testColumnNamesOfAccountTable() throws SQLException {
        String tableName = "ACCOUNT";
        Set<String> expectedColumns = Set.of("ID", "OWNER");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table ACCOUNT does not contain all expected columns like ID, OWNER");
    }

    @Test
    public void testColumnNamesOfCreditAccountTable() throws SQLException {
        String tableName = "CREDIT_ACCOUNT";
        Set<String> expectedColumns = Set.of("INTEREST_RATE", "ACCOUNT_ID");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table CREDIT_ACCOUNT does not contain all expected columns like INTEREST_RATE, ACCOUNT_ID");
    }

    @Test
    public void testColumnNamesOfDebitAccountTable() throws SQLException {
        String tableName = "DEBIT_ACCOUNT";
        Set<String> expectedColumns = Set.of("BALANCE", "ACCOUNT_ID");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table DEBIT_ACCOUNT does not contain all expected columns like BALANCE, ACCOUNT_ID");
    }

    @Test
    public void testColumnNamesOfCreditCardTable() throws SQLException {
        String tableName = "CREDIT_CARD";
        Set<String> expectedColumns = Set.of("CREDIT_ACCOUNT_ID", "ID");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table CREDIT_CARD does not contain all expected columns like CREDIT_ACCOUNT_ID, ID");
    }

    @Test
    public void testColumnNamesOfDebitCardTable() throws SQLException {
        String tableName = "DEBIT_CARD";
        Set<String> expectedColumns = Set.of("DEBIT_ACCOUNT_ID", "ID");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table DEBIT_CARD does not contain all expected columns like DEBIT_ACCOUNT_ID, ID");
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

