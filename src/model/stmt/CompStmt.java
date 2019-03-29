package model.stmt;

import model.adt.MyIStack;
import model.adt.PrgState;

public class CompStmt implements IStmt
{
    private IStmt firstStmt;
    private IStmt secondStmt;

    public CompStmt(IStmt firstStmt, IStmt secondStmt)
    {
        this.firstStmt = firstStmt;
        this.secondStmt = secondStmt;
    }

    public PrgState execute(PrgState state)
    {
        MyIStack<IStmt> stk = state.getExeStack();
        stk.push(secondStmt);
        stk.push(firstStmt);
        return null;
    }

    @Override
    public String toString()
    {
        return firstStmt.toString() + ";\n" + secondStmt.toString();
    }
}
