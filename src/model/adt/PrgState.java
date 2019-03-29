package model.adt;

import exception.modelexception.*;
import model.stmt.IStmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class PrgState
{
    private int id;
    private MyIStack<IStmt> exeStack;
    private Stack<MyIDictionary<String,Integer>> symTableStack;
    private MyIList<Integer> out;
    private MyIMap<MyITuple<String,BufferedReader>> fileTable;
    private MyIMap<Integer> heap;
    private MyITable<String,MyITuple<List<String>,IStmt>> procTable;

    public PrgState(int id,MyIStack<IStmt> exeStack,Stack<MyIDictionary<String,Integer>> symTableStack,MyIList<Integer> out,
            MyIMap<MyITuple<String,BufferedReader>> fileTable,MyIMap<Integer> heap,
            MyITable<String,MyITuple<List<String>,IStmt>> procTable,IStmt program)
    {
        this.id = id;
        this.exeStack = exeStack;
        this.symTableStack = symTableStack;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.procTable = procTable;
        exeStack.push(program);
    }

    public int getId()
    {
        return id;
    }

    public MyIStack<IStmt> getExeStack()
    {
        return exeStack;
    }

    public MyIDictionary<String,Integer> getSymTable()
    {
        return symTableStack.peek();
    }

    public Stack<MyIDictionary<String,Integer>> getSymTableStack()
    {
        return symTableStack;
    }

    public MyIList<Integer> getOut()
    {
        return out;
    }

    public MyIMap<MyITuple<String,BufferedReader>> getFileTable()
    {
        return fileTable;
    }

    public MyIMap<Integer> getHeap()
    {
        return heap;
    }

    public MyITable<String,MyITuple<List<String>,IStmt>> getProcTable()
    {
        return procTable;
    }

    public boolean isNotCompleted()
    {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws VariableNotDefinedException, EmptyStackException, InvalidOperatorException,
            FileAlreadyOpenedException, FileDescriptorException, OpenReadFileException, IOException,
            DivisionByZeroException, HeapAllocationException, ProcedureNotDefinedException
    {
        IStmt currentStmt = exeStack.pop();
        return currentStmt.execute(this);
    }

    public String toString()
    {
        return "\tProgram State " + id + "\nExecution Stack\n" + exeStack.toString() + "\n\nTable of Symbols: " +
                symTableStack.peek().toString() + "\n\nOutput: " + out.toString() + "\n\nFile Table: " + fileTable.toString() +
                "\n\nHeap: " + heap.toString() + "\n\nProcedure Table:" + procTable.toString() + "\n\n\n\n";
    }
}
