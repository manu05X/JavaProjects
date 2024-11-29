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
    public void testIfTableWithNameBookIsCreated() {
        String tableName = "BOOK";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name BOOK does not exist !");
    }

    @Test
    public void testIfTableWithNamePenIsCreated() {
        String tableName = "PEN";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name PEN does not exist !");
    }

    @Test
    public void testIfTableWithNameProductIsCreated() {
        String tableName = "PRODUCT";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name PRODUCT does not exist !");
    }

    @Test
    public void testIfTableWithNameSubscriptionIsCreated() {
        String tableName = "SUBSCRIPTION";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name SUBSCRIPTION does not exist !");
    }


    @Test
    public void testColumnNamesOfBookTable() throws SQLException {
        String tableName = "BOOK";
        Set<String> expectedColumns = Set.of("BOOK_TYPE", "ID", "SUBSCRIPTION_ID", "NAME", "STATUS");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table BOOK does not contain all expected columns like BOOK_TYPE, ID, SUBSCRIPTION_ID, NAME, STATUS");
    }

    @Test
    public void testColumnNamesOfPenTable() throws SQLException {
        String tableName = "PEN";
        Set<String> expectedColumns = Set.of("PEN_TYPE", "ID", "SUBSCRIPTION_ID", "NAME", "STATUS");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table PEN does not contain all expected columns like PEN_TYPE, ID, SUBSCRIPTION_ID, NAME, STATUS");
    }

    @Test
    public void testColumnNamesOfProductTable() throws SQLException {
        String tableName = "PRODUCT";
        Set<String> expectedColumns = Set.of("ID", "SUBSCRIPTION_ID", "NAME", "STATUS");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table PRODUCT does not contain all expected columns like ID, SUBSCRIPTION_ID, NAME, STATUS");
    }

    @Test
    public void testColumnNamesOfSubscriptionTable() throws SQLException {
        String tableName = "SUBSCRIPTION";
        Set<String> expectedColumns = Set.of("CHARGES", "STATUS", "ID");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table SUBSCRIPTION does not contain all expected columns like CHARGES, STATUS, ID");
    }

    private Set<String> getColumnNames(String tableName) throws SQLException {
        Set<String> columns = new HashSet<>();
        DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();

        try (ResultSet rs = metaData.getColumns(null, null, tableName, null)) {
            while (rs.next()) {
                columns.add(rs.getString("COLUMN_NAME"));
//                String cname = rs.getString("COLUMN_NAME");
//                if(cname.equals("STATUS")) {
//                    String ctype = rs.getString("TYPE_NAME");
//                    System.out.println(ctype);
//                }
            }
        }
        return columns;
    }

    private boolean validateColumns(String tableName, Set<String> expectedColumns) throws SQLException {
        Set<String> actualColumns = getColumnNames(tableName);
        return actualColumns.containsAll(expectedColumns);
    }

    @Test
    public void testDataTypeOfEnumFieldPresentInSubscriptionTable() throws SQLException {
        Set<String> columnTypes = getColumnTypes("SUBSCRIPTION");

        assertTrue(columnTypes.contains("ENUM('ACTIVE', 'INACTIVE')"),"SUBSCRIPTION STATUS SHOULD BE OF STRING TYPE");
    }

    @Test
    public void testDataTypeOfEnumFieldPresentInProductTable() throws SQLException {
        Set<String> columnTypes = getColumnTypes("PRODUCT");

        assertTrue(columnTypes.contains("ENUM('ACTIVE', 'INACTIVE')"),"PRODUCT STATUS SHOULD BE OF STRING TYPE");
    }

    private Set<String> getColumnTypes(String tableName) throws SQLException {
        Set<String> types = new HashSet<>();
        DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();

        try (ResultSet rs = metaData.getColumns(null, null, tableName, null)) {
            while (rs.next()) {
                types.add(rs.getString("TYPE_NAME"));
            }
        }
        return types;
    }
}
