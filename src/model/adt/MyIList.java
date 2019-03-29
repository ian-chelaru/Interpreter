package model.adt;

import java.util.ArrayList;

public interface MyIList<E>
{
    void add (E e);
    ArrayList<E> getContent();
}
