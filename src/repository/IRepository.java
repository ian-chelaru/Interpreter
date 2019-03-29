package repository;

import exception.repositoryexception.LogFileException;
import model.adt.PrgState;

import java.util.List;

public interface IRepository
{
    List<PrgState> getProgramList();
    void setProgramList(List<PrgState> programList);
    void addProgram(PrgState newProgram);
    void logPrgStateExec(PrgState currentProgram) throws LogFileException;
    PrgState getPrgStateById(int id);
}
