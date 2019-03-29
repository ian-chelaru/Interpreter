package model.adt;

import exception.modelexception.VariableNotDefinedException;

import java.util.HashMap;

public interface MyIDictionary<K,V>
{
    V lookUp(K key) throws VariableNotDefinedException;
    void add(K key, V value);
    HashMap<K,V> getContent();
}
