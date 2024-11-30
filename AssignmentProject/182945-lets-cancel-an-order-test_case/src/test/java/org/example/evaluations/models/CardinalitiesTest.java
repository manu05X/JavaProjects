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
    public void testIfTableWithNameAddressIsCreatedWithExpectedFields() throws SQLException {
        String tableName = "ADDRESS";

        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        Set<String> expectedColumns = Set.of("IS_DEFAULT","ID","CITY","LANDMARK","NUMBER","PINCODE","STATE","STREET");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(count != null && count > 0, "Table with name ADDRESS does not exist !");

        assertTrue(columnsAreValid, "The table ADDRESS does not contain all expected columns like IS_DEFAULT,ID,CITY,LANDMARK,NUMBER,PINCODE,STATE,STREET");
    }

    @Test
    public void testIfTableWithNameCustomerIsCreatedWithExpectedFields() throws SQLException {
        String tableName = "CUSTOMER";

        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        Set<String> expectedColumns = Set.of("ID","EMAIL","NAME","PASSWORD","ORDER_CANCELLATION_COUNT");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(count != null && count > 0, "Table with name CUSTOMER does not exist !");

        assertTrue(columnsAreValid, "The table CUSTOMER does not contain all expected columns like ID,EMAIL,NAME,PASSWORD,ORDER_CANCELLATION_COUNT");
    }

    @Test
    public void testIfTableWithNameCustomers_AddressesIsCreatedWithExpectedFields() throws SQLException {
        String tableName = "CUSTOMERS_ADDRESSES";

        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        Set<String> expectedColumns = Set.of("ADDRESS_ID","CUSTOMER_ID");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(count != null && count > 0, "Table with name CUSTOMERS_ADDRESSES does not exist !");

        assertTrue(columnsAreValid, "The table CUSTOMERS_ADDRESSES does not contain all expected columns like ADDRESS_ID,CUSTOMER_ID");
    }

    @Test
    public void testIfTableWithNameInventoryIsCreatedWithExpectedFields() throws SQLException {
        String tableName = "INVENTORY";

        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        Set<String> expectedColumns = Set.of("COUNT","ORDERING_COST","STOCK_OUT_COST","ID","ITEM_ID");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(count != null && count > 0, "Table with name INVENTORY does not exist !");

        assertTrue(columnsAreValid, "The table INVENTORY  does not contain all expected columns like COUNT,ORDERING_COST,STOCK_OUT_COST,ID,ITEM_ID");
    }

    @Test
    public void testIfTableWithNameItemIsCreatedWithExpectedFields() throws SQLException {
        String tableName = "ITEM";

        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        Set<String> expectedColumns = Set.of("IS_PREMIUM","PRICE","ID","DESCRIPTION","IMAGE_URL","TITLE");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(count != null && count > 0, "Table with name ITEM does not exist !");

        assertTrue(columnsAreValid, "The table ITEM does not contain all expected columns like IS_PREMIUM,PRICE,ID,DESCRIPTION,IMAGE_URL,TITLE");
    }

    @Test
    public void testIfTableWithNameItemDetailIsCreatedWithExpectedFields() throws SQLException {
        String tableName = "ITEM_DETAIL";

        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        Set<String> expectedColumns = Set.of("ID","ITEM_ID","ORDER_ID","QUANTITY");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(count != null && count > 0, "Table with name ITEM_DETAIL does not exist !");

        assertTrue(columnsAreValid, "The table ITEM_DETAIL does not contain all expected columns like ID,ITEM_ID,ORDER_ID,QUANTITY");
    }

    @Test
    public void testIfTableWithNameOrdersIsCreatedWithExpectedFields() throws SQLException {
        String tableName = "ORDERS";

        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        Set<String> expectedColumns = Set.of("TOTAL_COST","CUSTOMER_ID","ID");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(count != null && count > 0, "Table with name ORDERS does not exist !");

        assertTrue(columnsAreValid, "The table ORDERS does not contain all expected columns like TOTAL_COST,CUSTOMER_ID,ID");
    }

    @Test
    public void testIfTableWithNameOrderStateTimeMappingIsCreatedWithExpectedFields() throws SQLException {
        String tableName = "ORDER_STATE_TIME_MAPPING";

        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        Set<String> expectedColumns = Set.of("ORDER_STATE","DATE","ID","ORDER_ID");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(count != null && count > 0, "Table with name ORDER_STATE_TIME_MAPPING does not exist !");

        assertTrue(columnsAreValid, "The table ORDER_STATE_TIME_MAPPING does not contain all expected columns like ORDER_STATE,DATE,ID,ORDER_ID");
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
