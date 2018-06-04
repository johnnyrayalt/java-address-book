package dao;

import models.Entry;
import org.sql2o.*;
import java.util.List;

public class Sql2oEntryDao implements EntryDao {

    private final Sql2o sql2o;

    public Sql2oEntryDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Entry entry) {
        String sql = "INSERT INTO entries (name, phoneNumber, mailingAddress, emailAddress) VALUES (:name, :phoneNumber,:mailingAddress, :emailAddress)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(entry)
                    .executeUpdate()
                    .getKey();
            entry.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Entry> getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM entries")
                    .executeAndFetch(Entry.class);
        }
    }

    @Override
    public Entry findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM entries WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Entry.class);
        }

    }

    @Override
    public void update(int id, String newName) {
        String sql = "UPDATE entries SET name = :name WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from entries WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllEntries() {
        String sql = "DELETE from entries";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}
