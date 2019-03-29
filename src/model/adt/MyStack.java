package model.adt;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T>
{
    private Stack<T> stack;

    public MyStack()
    {
        this.stack = new Stack<>();
    }

    public Stack<T> getContent()
    {
        return stack;
    }

    public T pop()
    {
        return stack.pop();
    }

    public void push(T v)
    {
        stack.push(v);
    }

    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    @Override
    public String toString()
    {
        String s = "";
        for (T stmt : stack)
        {
            s = stmt.toString() + "\n" + s;
        }
        s = "{\n" + s;
        s += "}";
        return s;
    }
}
