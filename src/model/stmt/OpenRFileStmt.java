package model.stmt;

import exception.modelexception.FileAlreadyOpenedException;
import exception.modelexception.OpenReadFileException;
import model.adt.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

public class OpenRFileStmt implements IStmt
{
    private String varFileId;
    private String fileName;

    public OpenRFileStmt(String var, String fileName)
    {
        this.varFileId = var;
        this.fileName = fileName;
    }

    public HashSet<String> getFileNames(PrgState state)
    {
        HashSet<String> fileNames = new HashSet<>();
        MyIMap<MyITuple<String,BufferedReader>> fileTable = state.getFileTable();
        Collection<MyITuple<String,BufferedReader>> values = fileTable.getValues();
        for (MyITuple<String,BufferedReader> value : values)
        {
            fileNames.add(value.getX());
        }
        return fileNames;
    }

    public PrgState execute(PrgState state) throws FileAlreadyOpenedException, OpenReadFileException
    {
        HashSet<String> fileNames = getFileNames(state);
        if (fileNames.contains(fileName))
        {
            throw new FileAlreadyOpenedException();
        }
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            MyIMap<MyITuple<String,BufferedReader>> fileTable = state.getFileTable();
            MyTuple<String,BufferedReader> tuple = new MyTuple<>(fileName, br);
            fileTable.add(tuple);
            MyIDictionary<String, Integer> symTable = state.getSymTable();
            symTable.add(varFileId, fileTable.getKey());
            return null;
        }
        catch (FileNotFoundException e)
        {
            throw new OpenReadFileException();
        }
    }

    @Override
    public String toString()
    {
        return "openRFile(" + varFileId + ", \"" + fileName + "\")";
    }
}
