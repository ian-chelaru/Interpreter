package model.stmt;

import exception.modelexception.DivisionByZeroException;
import exception.modelexception.HeapAllocationException;
import exception.modelexception.InvalidOperatorException;
import exception.modelexception.VariableNotDefinedException;
import model.adt.MyIDictionary;
import model.adt.MyIMap;
import model.adt.MyIStack;
import model.adt.PrgState;
import model.exp.Exp;

public class IfStmt implements IStmt
{
    private Exp exp;
    private IStmt thenStmt;
    private IStmt elseStmt;

    public IfStmt(Exp exp,IStmt thenStmt,IStmt elseStmt)
    {
        this.exp = exp;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    public PrgState execute(PrgState state) throws VariableNotDefinedException, InvalidOperatorException,
            DivisionByZeroException, HeapAllocationException
    {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String,Integer> symTbl = state.getSymTable();
        MyIMap<Integer> heap = state.getHeap();
        if (exp.eval(symTbl,heap) != 0)
        {
            stk.push(thenStmt);
        }
        else
        {
            stk.push(elseStmt);
        }
        return null;
    }

    @Override
    public String toString()
    {
        return "(If " + exp.toString() + " then " + thenStmt.toString() + " else " + elseStmt.toString() + ")";
    }
}
