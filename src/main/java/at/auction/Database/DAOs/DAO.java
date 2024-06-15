package at.auction.Database.DAOs;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T>{

    public T get(int id) throws SQLException;

    public List<T> getAll() throws SQLException;

    public void save(T t) throws SQLException;

    public void insert(T t) throws SQLException;

    public void remove(T t) throws SQLException;
}
