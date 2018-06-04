package dao;

import models.Entry;
import java.util.List;

public interface EntryDao {

    List<Entry> getAll();

    void add(Entry entry);

    Entry findById(int id);

    void update(int id, String name);

    void deleteById(int id);

    void clearAllEntries();
}
