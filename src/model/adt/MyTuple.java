package model.adt;

public class MyTuple<X,Y> implements MyITuple
{
    private X x;
    private Y y;

    public MyTuple(X x, Y y)
    {
        this.x = x;
        this.y = y;
    }

    public X getX()
    {
        return x;
    }

    public Y getY()
    {
        return y;
    }

    @Override
    public String toString()
    {
        return "(" + x.toString() + ", " + y.toString() + ")";
    }
}
