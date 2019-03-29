package model.adt;

import exception.modelexception.InvalidKeyException;

import java.util.Collection;
import java.util.Map;

public interface MyIMap<V>
{
    V lookUp(int key) throws InvalidKeyException;
    void add(V value);
    Collection<V> getValues();
    int getKey();
    void deleteEntry(int key);
    void update(int key, V value);
    Map<Integer,V> getContent();
    void setContent(Map<Integer,V> map);
}
