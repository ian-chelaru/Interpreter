package model.exp;

import model.adt.MyIDictionary;
import model.adt.MyIMap;

public class ConstExp extends Exp
{
    private Integer number;

    public ConstExp(int number)
    {
        this.number = number;
    }

    @Override
    public int eval(MyIDictionary<String,Integer> tbl, MyIMap<Integer> hp)
    {
        return number;
    }

    public String toString()
    {
        return number.toString();
    }
}
