package model.stmt;

import model.adt.PrgState;

public class ReturnStmt implements IStmt
{
    public ReturnStmt()
    {
    }

    @Override
    public PrgState execute(PrgState state)
    {
        state.getSymTableStack().pop();
        return null;
    }

    @Override
    public String toString()
    {
        return "return";
    }
}
