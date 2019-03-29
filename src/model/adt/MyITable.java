package model.adt;

import exception.modelexception.ProcedureNotDefinedException;

import java.util.Map;

public interface MyITable<K,V>
{
    V lookup(K key) throws ProcedureNotDefinedException;
    void add(K key, V value);
    Map<K,V> getContent();
}
