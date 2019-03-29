package model.exp;

import exception.modelexception.DivisionByZeroException;
import exception.modelexception.HeapAllocationException;
import exception.modelexception.InvalidOperatorException;
import exception.modelexception.VariableNotDefinedException;
import model.adt.MyIDictionary;
import model.adt.MyIMap;

public abstract class Exp
{
    public abstract int eval(MyIDictionary<String,Integer> tbl, MyIMap<Integer> hp) throws DivisionByZeroException,
            VariableNotDefinedException, InvalidOperatorException, HeapAllocationException;
}