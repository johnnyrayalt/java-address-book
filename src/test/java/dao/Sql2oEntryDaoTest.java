package dao;

import models.Entry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import static org.junit.Assert.*;

public class Sql2oEntryDaoTest {
    private Sql2oEntryDao entryDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        entryDao = new Sql2oEntryDao(sql2o);
        conn = sql2o.open();
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Entry entry = new Entry("John Doe", "8888675309", "100 Main St., Los Angeles, California, 90001", "johndoe@gmail.com");
        int originalEntryId = entry.getId();
        entryDao.add(entry);
        assertNotEquals(originalEntryId, entry.getId());
    }

    @Test
    public void existingEntriesCanbeFoundById() throws Exception {
        Entry entry = new Entry("John Doe", "8888675309", "100 Main St., Los Angeles, California, 90001", "johndoe@gmail.com");
        entryDao.add(entry);
        Entry foundEntry = entryDao.findById(entry.getId());
        assertEquals(entry, foundEntry);
    }

    @Test
    public void addedEntriesAreReturnedFromGetAll() throws Exception {
        Entry entry = new Entry("John Doe", "8888675309", "100 Main St., Los Angeles, California, 90001", "johndoe@gmail.com");
        entryDao.add(entry);
        assertEquals(1, entryDao.getAll().size());
    }

    @Test
    public void noEntryReturnsEmptyList() {
        assertEquals(0, entryDao.getAll().size());
    }

    @Test
    public void updateChangesEntryContent() throws Exception {
        Entry entry = new Entry("John Doe", "8888675309", "100 Main St., Los Angeles, California, 90001", "johndoe@gmail.com");
        entryDao.add(entry);
        entryDao.update(entry.getId(), "Jane Doe");
        Entry updatedEntry = entryDao.findById(entry.getId());
        assertNotEquals("John Doe", updatedEntry.getName());
    }

    @Test
    public void deleteByIdDeletesCorrectEntry() throws Exception {
        Entry entry = new Entry("John Doe", "8888675309", "100 Main St., Los Angeles, California, 90001", "johndoe@gmail.com");
        entryDao.add(entry);
        entryDao.deleteById(entry.getId());
        assertEquals(0, entryDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() {
        Entry entry = new Entry("John Doe", "8888675309", "100 Main St., Los Angeles, California, 90001", "johndoe@gmail.com");
        Entry entry2 = new Entry("John Doe", "8888675309", "100 Main St., Los Angeles, California, 90001", "johndoe@gmail.com");
        entryDao.add(entry);
        entryDao.add(entry2);
        int daoSize = entryDao.getAll().size();
        entryDao.clearAllEntries();
        assertTrue(daoSize > 0 && daoSize > entryDao.getAll().size());
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
}