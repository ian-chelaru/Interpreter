package model.adt;

import java.util.Stack;

public interface MyIStack<T>
{
    T pop();
    void push(T v);
    boolean isEmpty();
    Stack<T> getContent();
}
