package services;

import java.util.List;

public interface IService<T> {
    List<T> findAll();

    T findById(String id);

    void add(T t);

    void edit(T t, String id);

    void delete(String id);
}
