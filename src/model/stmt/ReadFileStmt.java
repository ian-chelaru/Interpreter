package model.stmt;

import exception.modelexception.*;
import model.adt.MyIDictionary;
import model.adt.MyIMap;
import model.adt.MyITuple;
import model.adt.PrgState;
import model.exp.Exp;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt
{
    private Exp exp;
    private String varName;

    public ReadFileStmt(Exp exp,String varName)
    {
        this.exp = exp;
        this.varName = varName;
    }

    public PrgState execute(PrgState state) throws VariableNotDefinedException, InvalidOperatorException,
            FileDescriptorException, IOException, DivisionByZeroException, HeapAllocationException
    {
        MyIDictionary<String,Integer> symTable = state.getSymTable();
        MyIMap<MyITuple<String,BufferedReader>> fileTable = state.getFileTable();
        MyIMap<Integer> heap = state.getHeap();
        int fd = exp.eval(symTable,heap);
        try
        {
            BufferedReader br = fileTable.lookUp(fd).getY();
            String line;
            line = br.readLine();
            int number;
            if (line == null)
            {
                number = 0;
            }
            else
            {
                number = Integer.parseInt(line);
            }
            symTable.add(varName,number);
            return null;
        }
        catch (InvalidKeyException e)
        {
            throw new FileDescriptorException();
        }
    }

    @Override
    public String toString()
    {
        return "readFile(" + exp.toString() + ", " + varName + ")";
    }
}
