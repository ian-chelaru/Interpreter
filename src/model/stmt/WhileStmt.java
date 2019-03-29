package model.stmt;

import exception.modelexception.*;
import model.adt.MyIDictionary;
import model.adt.MyIMap;
import model.adt.MyIStack;
import model.adt.PrgState;
import model.exp.Exp;

public class WhileStmt implements IStmt
{
    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp exp,IStmt stmt)
    {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws VariableNotDefinedException, InvalidOperatorException, DivisionByZeroException,
            HeapAllocationException
    {
        MyIStack<IStmt> stack = state.getExeStack();
        MyIDictionary<String,Integer> symTbl = state.getSymTable();
        MyIMap<Integer> heap = state.getHeap();
        if (exp.eval(symTbl,heap) != 0)
        {
            stack.push(this);
            stack.push(stmt);
        }
        return null;
    }

    @Override
    public String toString()
    {
        return "while(" + exp.toString() + ")" + "\n{\n" + stmt.toString() + "\n}";
    }
}
