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
    public void testIfMappingTableIsCreated() {
        String tableName = "MEMBERS_WHATSAPP_GROUPS";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name MEMBERS_WHATSAPP_GROUPS does not exist !");
    }

    @Test
    public void testIfTableWithNameMessageIsCreated() {
        String tableName = "MESSAGE";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name MESSAGE does not exist!");
    }

    @Test
    public void testIfTableWithNameWhatsapp_GroupIsCreated() {
        String tableName = "WHATSAPP_GROUP";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name WHATSAPP_GROUP  does not exist!");
    }

    @Test
    public void testIfTableWithNameUserIsCreated() {
        String tableName = "MEMBER";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name MEMBER does not exist!");
    }

    @Test
    public void testColumnNamesOfMemberTable() throws SQLException {
        String tableName = "MEMBER";
        Set<String> expectedColumns = Set.of("ID", "NAME");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table MEMBER does not contain all expected columns like ID, NAME");
    }

    @Test
    public void testColumnNamesOfMessageTable() throws SQLException {
        String tableName = "MESSAGE";
        Set<String> expectedColumns = Set.of("ID", "CONTENT","AUTHOR_ID","GROUP_ID");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table MESSAGE does not contain all expected columns like ID, CONTENT, AUTHOR_ID, GROUP_ID");
    }

    @Test
    public void testColumnNamesOfWhatsapp_GroupTable() throws SQLException {
        String tableName = "WHATSAPP_GROUP";
        Set<String> expectedColumns = Set.of("ID", "NAME","DESCRIPTION");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table WHATSAPP_GROUP does not contain all expected columns like ID, NAME, DESCRIPTION");
    }

    @Test
    public void testColumnNamesOfMembers_Whatsapp_GroupsTable() throws SQLException {
        String tableName = "MEMBERS_WHATSAPP_GROUPS";
        Set<String> expectedColumns = Set.of("MEMBER_ID", "WHATSAPP_GROUP_ID");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table MEMBERS_WHATSAPP_GROUPS does not contain all expected columns like MEMBER_ID, WHATSAPP_GROUP_ID");
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
