package model.stmt;

import exception.modelexception.*;
import model.adt.*;
import model.exp.Exp;

import java.util.List;

public class CallProcStmt implements IStmt
{

    private String procName;
    private List<Exp> parameters;

    public CallProcStmt(String procName,List<Exp> parameters)
    {
        this.procName = procName;
        this.parameters = parameters;
    }

    @Override
    public PrgState execute(PrgState state)
            throws VariableNotDefinedException, InvalidOperatorException, DivisionByZeroException,
            HeapAllocationException, ProcedureNotDefinedException
    {
        MyITuple<List<String>,IStmt> procedure = state.getProcTable()
                .lookup(procName);
        MyIDictionary<String,Integer> newSymTable = new MyDictionary<>();
        MyIDictionary<String,Integer> mainSymTable = state.getSymTable();
        MyIMap<Integer> heap = state.getHeap();
        List<String> formalParameters = procedure.getX();
        int n = parameters.size();
        for (int i = 0; i < n; i++)
        {
            newSymTable.add(formalParameters.get(i),parameters.get(i)
                    .eval(mainSymTable,heap));
        }
        state.getSymTableStack()
                .push(newSymTable);
        state.getExeStack()
                .push(new ReturnStmt());
        state.getExeStack()
                .push(procedure.getY());
        return null;
    }

    @Override
    public String toString()
    {
        return "call " + procName + "(" + parameters.toString() + ")";
    }
}
