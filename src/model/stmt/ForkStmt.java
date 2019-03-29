package model.stmt;

import model.adt.*;

import java.util.Stack;

public class ForkStmt implements IStmt
{
    private IStmt stmt;
    private static int id = 2;

    public ForkStmt(IStmt stmt)
    {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state)
    {
        /*MyIDictionary<String,Integer> threadSymTable = new MyDictionary<>();
        state.getSymTable()
                .getContent()
                .forEach((key,value) ->
                {
                    threadSymTable.add(key,value);
                });*/
        Stack<MyIDictionary<String,Integer>> threadSymTableStack = new Stack<>();
        Stack<MyIDictionary<String,Integer>> symTableStack = state.getSymTableStack();
        for (MyIDictionary<String,Integer> symTable : symTableStack)
        {
            MyIDictionary<String,Integer> threadSymTable = new MyDictionary<>();
            symTable.getContent().forEach((key,value) -> threadSymTable.add(key,value));
            threadSymTableStack.add(threadSymTable);
        }
        PrgState threadPrgState = new PrgState(id++, new MyStack<>(),threadSymTableStack,state.getOut(),
                state.getFileTable(),state.getHeap(),state.getProcTable(),stmt);
        return threadPrgState;
    }

    @Override
    public String toString()
    {
        return "fork(\n" + stmt.toString() + "\nend fork)";
    }
}
