package model.exp;

import exception.modelexception.HeapAllocationException;
import exception.modelexception.InvalidKeyException;
import exception.modelexception.VariableNotDefinedException;
import model.adt.MyIDictionary;
import model.adt.MyIMap;

public class HpReadExp extends Exp
{
    private String id;

    public HpReadExp(String id)
    {
        this.id = id;
    }

    @Override
    public int eval(MyIDictionary<String,Integer> tbl,MyIMap<Integer> hp) throws VariableNotDefinedException,
            HeapAllocationException
    {
        try
        {
            return hp.lookUp(tbl.lookUp(id));
        }
        catch (InvalidKeyException e)
        {
            throw new HeapAllocationException();
        }
    }

    @Override
    public String toString()
    {
        return "rH(" + id + ")";
    }
}
