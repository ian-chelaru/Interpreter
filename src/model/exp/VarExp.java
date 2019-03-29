package model.exp;

import exception.modelexception.VariableNotDefinedException;
import model.adt.MyIDictionary;
import model.adt.MyIMap;

public class VarExp extends Exp
{
    private String id;

    public VarExp(String id)
    {
        this.id = id;
    }

    @Override
    public int eval(MyIDictionary<String,Integer> tbl, MyIMap<Integer> hp) throws VariableNotDefinedException
    {
        return tbl.lookUp(id);

    }

    public String toString()
    {
        return id;
    }
}
