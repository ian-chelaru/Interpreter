package model.adt;

import exception.modelexception.VariableNotDefinedException;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K,V> implements MyIDictionary<K,V>
{
    private HashMap<K,V> dictionary;

    public MyDictionary()
    {
        this.dictionary = new HashMap<>();
    }

    public HashMap<K,V> getContent()
    {
        return dictionary;
    }

    public V lookUp(K key) throws VariableNotDefinedException
    {
        V v = dictionary.get(key);
        if (v == null)
        {
            throw new VariableNotDefinedException();
        }
        return v;
    }

    public void add(K key,V value)
    {
        dictionary.put(key,value);
    }

    @Override
    public String toString()
    {
        String s = "[";
        for (HashMap.Entry<K,V> entry : dictionary.entrySet())
        {
            s += "\t" + entry.getKey().toString() + "->" + entry.getValue().toString() + " ";
        }
        s += "]";
        return s;
    }
}
