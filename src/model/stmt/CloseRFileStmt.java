package model.stmt;

import exception.modelexception.*;
import model.adt.MyIDictionary;
import model.adt.MyIMap;
import model.adt.MyITuple;
import model.adt.PrgState;
import model.exp.Exp;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStmt implements IStmt
{
    private Exp exp;

    public CloseRFileStmt(Exp exp)
    {
        this.exp = exp;
    }

    public PrgState execute(PrgState state) throws VariableNotDefinedException, InvalidOperatorException,
            FileDescriptorException, IOException, DivisionByZeroException, HeapAllocationException
    {
        MyIDictionary<String,Integer> symTable = state.getSymTable();
        MyIMap<Integer> heap = state.getHeap();
        MyIMap<MyITuple<String,BufferedReader>> fileTable = state.getFileTable();
        try
        {
            int fd = exp.eval(symTable,heap);
            BufferedReader br = fileTable.lookUp(fd).getY();
            br.close();
            fileTable.deleteEntry(fd);
            return null;
        }
        catch (InvalidKeyException e)
        {
            throw new FileDescriptorException();
        }
    }

    public String toString()
    {
        return "closeRFile(" + exp.toString() + ")";
    }
}
