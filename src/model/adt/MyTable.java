package model.adt;

import exception.modelexception.ProcedureNotDefinedException;

import java.util.HashMap;
import java.util.Map;

public class MyTable<K,V> implements MyITable<K,V>
{

    private Map<K,V> table;

    public MyTable()
    {
        this.table = new HashMap<>();
    }

    @Override
    public V lookup(K key) throws ProcedureNotDefinedException
    {
        V v = table.get(key);
        if (v == null)
        {
            throw new ProcedureNotDefinedException();
        }
        return v;
    }

    @Override
    public void add(K key,V value)
    {
        table.put(key,value);
    }

    @Override
    public Map<K,V> getContent()
    {
        return table;
    }

    @Override
    public String toString()
    {
        String s = "[";
        for (HashMap.Entry<K,V> entry : table.entrySet())
        {
            s += "\t" + entry.getKey().toString() + "->" + entry.getValue().toString() + " ";
        }
        s += "]";
        return s;
    }
}
