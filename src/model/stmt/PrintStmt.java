package model.stmt;

import exception.modelexception.DivisionByZeroException;
import exception.modelexception.HeapAllocationException;
import exception.modelexception.InvalidOperatorException;
import exception.modelexception.VariableNotDefinedException;
import model.adt.MyIDictionary;
import model.adt.MyIList;
import model.adt.MyIMap;
import model.adt.PrgState;
import model.exp.Exp;

public class PrintStmt implements IStmt
{
    private Exp exp;

    public PrintStmt(Exp exp)
    {
        this.exp = exp;
    }

    public PrgState execute(PrgState state) throws VariableNotDefinedException, InvalidOperatorException,
            DivisionByZeroException, HeapAllocationException
    {
        MyIDictionary<String,Integer> symTbl = state.getSymTable();
        MyIList<Integer> out = state.getOut();
        MyIMap<Integer> heap = state.getHeap();
        out.add(exp.eval(symTbl,heap));
        return null;
    }

    @Override
    public String toString()
    {
        return "print(" + exp.toString() + ")";
    }
}
