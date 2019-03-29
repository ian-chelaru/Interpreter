package model.stmt;

import exception.modelexception.*;
import model.adt.MyIDictionary;
import model.adt.MyIMap;
import model.adt.PrgState;
import model.exp.Exp;


public class HpWriteStmt implements IStmt
{
    private String var;
    private Exp exp;

    public HpWriteStmt(String var,Exp exp)
    {
        this.var = var;
        this.exp = exp;
    }

    public PrgState execute(PrgState state) throws VariableNotDefinedException, InvalidOperatorException,
            DivisionByZeroException, HeapAllocationException
    {
        MyIDictionary<String,Integer> symTbl = state.getSymTable();
        MyIMap<Integer> heap = state.getHeap();
        heap.update(symTbl.lookUp(var),exp.eval(symTbl,heap));
        return null;
    }

    @Override
    public String toString()
    {
        return "wH(" + var + "," + exp.toString() + ")";
    }
}
