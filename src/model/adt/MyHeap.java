package model.adt;

import exception.modelexception.InvalidKeyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyHeap<V> implements MyIMap<V>
{
    private static int key = 0;
    private Map<Integer,V> map;

    public MyHeap()
    {
        this.map = new HashMap<>();
    }

    public Map<Integer,V> getContent()
    {
        return map;
    }

    public void setContent(Map<Integer,V> map)
    {
        this.map = map;
    }

    public V lookUp(int key) throws InvalidKeyException
    {
        V v = map.get(key);
        if (v == null)
        {
            throw new InvalidKeyException();
        }
        return v;
    }

    public void add(V value)
    {
        key++;
        map.put(key,value);
    }

    public void update(int key, V value)
    {
        map.put(key, value);
    }

    public Collection<V> getValues()
    {
        return map.values();
    }

    public int getKey()
    {
        return key;
    }

    public void deleteEntry(int key)
    {
        map.remove(key);
    }

    @Override
    public String toString()
    {
        String s = "[";
        for (HashMap.Entry<Integer,V> entry : map.entrySet())
        {
            s += "\t" + entry.getKey().toString() + "->" + entry.getValue().toString() + " ";
        }
        s += "]";
        return s;
    }
}
