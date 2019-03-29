package repository;

import exception.repositoryexception.LogFileException;
import model.adt.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository
{
    private List<PrgState> programList;
    private String logFilePath;

    public Repository(String logFilePath)
    {
        programList = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

    public List<PrgState> getProgramList()
    {
        return programList;
    }

    public void setProgramList(List<PrgState> programList)
    {
        this.programList = programList;
    }

    public void addProgram(PrgState newProgram)
    {
        programList.add(newProgram);
    }

    public void logPrgStateExec(PrgState currentProgram) throws LogFileException
    {
        try
        {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,true)));
            logFile.printf(currentProgram.toString());
            logFile.close();
        }
        catch (IOException e)
        {
            throw new LogFileException();
        }
    }

    public PrgState getPrgStateById(int id)
    {
        for (PrgState prg : programList)
        {
            if (prg.getId() == id)
            {
                return prg;
            }
        }
        return null;
    }
}
