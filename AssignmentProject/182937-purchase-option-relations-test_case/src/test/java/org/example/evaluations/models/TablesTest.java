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
    public void testIfTableWithNameBankAccountIsCreated() {
        String tableName = "BANK_ACCOUNT";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name BANK_ACCOUNT does not exist !");
    }

    @Test
    public void testIfTableWithNameCreditCardIsCreated() {
        String tableName = "CREDIT_CARD";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name CREDIT_CARD does not exist !");
    }

    @Test
    public void testIfTableWithNamePayLaterIsCreated() {
        String tableName = "PAY_LATER";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name PAY_LATER does not exist !");
    }

    @Test
    public void testColumnNamesOfCreditCardTable() throws SQLException {
        String tableName = "CREDIT_CARD";
        Set<String> expectedColumns = Set.of("CREDIT_LIMIT", "ID","CARD_NUMBER", "CREDIT_CARD_OWNER");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table CREDIT_CARD does not contain all expected columns like CREDIT_LIMIT, ID,CARD_NUMBER, CREDIT_CARD_OWNER");
    }

    @Test
    public void testColumnNamesOfBankAccountTable() throws SQLException {
        String tableName = "BANK_ACCOUNT";
        Set<String> expectedColumns = Set.of("ID", "ACCOUNT_HOLDER", "BANK_NAME", "NUMBER");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table BANK_ACCOUNT does not contain all expected columns like ID, ACCOUNT_HOLDER, BANK_NAME, NUMBER");
    }

    @Test
    public void testColumnNamesOfPayLaterTable() throws SQLException {
        String tableName = "PAY_LATER";
        Set<String> expectedColumns = Set.of("ID","OWNER");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table PAY_LATER does not contain all expected columns like OWNER, ID");
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