package model.stmt;

import exception.modelexception.*;
import model.adt.MyIDictionary;
import model.adt.MyIMap;
import model.adt.PrgState;
import model.exp.Exp;


public class HeapAllocationStmt implements IStmt
{
    private String var;
    private Exp exp;

    public HeapAllocationStmt(String var, Exp exp)
    {
        this.var = var;
        this.exp = exp;
    }

    public PrgState execute(PrgState state) throws VariableNotDefinedException, InvalidOperatorException,
            DivisionByZeroException, HeapAllocationException
    {
        MyIMap<Integer> heap = state.getHeap();
        MyIDictionary<String,Integer> symTable = state.getSymTable();
        int value = exp.eval(symTable,heap);
        heap.add(value);
        symTable.add(var,heap.getKey());
        return null;
    }

    @Override
    public String toString()
    {
        return "new(" + var + ", " + exp.toString() + ")";
    }
}
