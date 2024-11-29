package org.example.evaluations.models;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
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

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testIfTableWithNameAuthorIsCreated() {
        String tableName = "AUTHOR";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name AUTHOR does not exist !");
    }

    @Test
    public void testIfTableWithNamePublicationIsCreated() {
        String tableName = "PUBLICATION";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name PUBLICATION does not exist !");
    }

    @Test
    public void testIfTableWithNamePublications_AuthorsIsCreated() {
        String tableName = "PUBLICATIONS_AUTHORS";
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{tableName}, Integer.class);

        assertTrue(count != null && count > 0, "Table with name PUBLICATIONS_AUTHORS does not exist !");
    }

    @Test
    public void testColumnNamesOfAuthorTable() throws SQLException {
        String tableName = "AUTHOR";
        Set<String> expectedColumns = Set.of("ID","NAME");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table AUTHOR does not contain all expected columns like ID, NAME");
    }

    @Test
    public void testColumnNamesOfPublicationTable() throws SQLException {
        String tableName = "PUBLICATION";
        Set<String> expectedColumns = Set.of("COST","NUMBER_OF_PAGES","PUBLISHING_DATE","VERSION","ID","PUBLICATION_TYPE","TITLE","URL");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table PUBLICATION does not contain all expected columns like COST,NUMBER_OF_PAGES,PUBLISHING_DATE,VERSION,ID,PUBLICATION_TYPE,TITLE,URL");
    }

    @Test
    public void testColumnNamesOfPublications_AuthorsTable() throws SQLException {
        String tableName = "PUBLICATIONS_AUTHORS";
        Set<String> expectedColumns = Set.of("AUTHOR_ID", "PUBLICATION_ID");

        boolean columnsAreValid = validateColumns(tableName, expectedColumns);

        assertTrue(columnsAreValid, "The table PUBLICATIONS_AUTHORS does not contain all expected columns like AUTHOR_ID, PUBLICATION_ID");
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

    @Transactional
    @Test
    public void insertPublication() {
        String sql = "INSERT INTO Publication (COST,VERSION,NUMBER_OF_PAGES,ID,PUBLICATION_TYPE,TITLE,URL) VALUES (100,1,200,2,'BOOK','HARRY POTTER',null)";
        entityManager.createNativeQuery(sql)
                .executeUpdate();
    }
}
