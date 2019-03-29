package model.adt;

import exception.modelexception.InvalidKeyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyMap<V> implements MyIMap<V>
{
    private static final AtomicInteger key = new AtomicInteger();
    private Map<Integer,V> map;

    public MyMap()
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
        int k;
        k = key.incrementAndGet();
        map.put(k,value);
    }

    public Collection<V> getValues()
    {
        return map.values();
    }

    public int getKey()
    {
        return key.get();
    }

    public void deleteEntry(int key)
    {
        map.remove(key);
    }

    public void update(int key, V value)
    {
        map.put(key, value);
    }

    @Override
    public String toString()
    {
        String s = "\n[\n";
        for (HashMap.Entry<Integer,V> entry : map.entrySet())
        {
            s += "\t" + entry.getKey().toString() + "->" + entry.getValue().toString() + "\n";
        }
        s += "]";
        return s;
    }
}
