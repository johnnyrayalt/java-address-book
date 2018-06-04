package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class EntryTest {

    @Test
    public void getName() {
        Entry testEntry = setNewEntry();
        assertEquals("John Doe", testEntry.getName());
    }

    @Test
    public void getPhoneNumber() {
        Entry testEntry = setNewEntry();
        assertEquals("8888675309", testEntry.getPhoneNumber());
    }

    @Test
    public void getMailingAddress() {
        Entry testEntry = setNewEntry();
        assertEquals("100 Main St., Los Angeles, California, 90001", testEntry.getMailingAddress());
    }

    @Test
    public void getEmailAddress() {
        Entry testEntry = setNewEntry();
        assertEquals("johndoe@gmail.com", testEntry.getEmailAddress());
    }


    public static Entry setNewEntry() {
        return new Entry("John Doe", "8888675309", "100 Main St., Los Angeles, California, 90001", "johndoe@gmail.com");
    }
}