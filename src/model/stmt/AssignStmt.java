package model.stmt;

import exception.modelexception.DivisionByZeroException;
import exception.modelexception.HeapAllocationException;
import exception.modelexception.InvalidOperatorException;
import exception.modelexception.VariableNotDefinedException;
import model.adt.MyIDictionary;
import model.adt.MyIMap;
import model.adt.PrgState;
import model.exp.Exp;

public class AssignStmt implements IStmt
{
    private String id;
    private Exp exp;

    public AssignStmt(String id,Exp exp)
    {
        this.id = id;
        this.exp = exp;
    }

    public PrgState execute(PrgState state) throws VariableNotDefinedException, InvalidOperatorException,
            DivisionByZeroException, HeapAllocationException
    {
        MyIDictionary<String,Integer> symTbl = state.getSymTable();
        MyIMap<Integer> heap = state.getHeap();
        symTbl.add(id,exp.eval(symTbl,heap));
        return null;
    }

    @Override
    public String toString()
    {
        return id + "=" + exp.toString();
    }
}
