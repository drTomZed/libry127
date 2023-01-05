package pl.sdacademy.dao;

import java.util.List;

public interface Repository<T, ID> {

    T findById();

    T findByISBNorTitle();

    List<T> findAll();

    void addBook();

    void deleteBook();

}
