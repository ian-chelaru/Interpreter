package model.stmt;

import exception.modelexception.*;
import model.adt.PrgState;

import java.io.IOException;

public interface IStmt
{
    PrgState execute(PrgState state) throws VariableNotDefinedException,
            InvalidOperatorException, FileAlreadyOpenedException, FileDescriptorException, OpenReadFileException,
            IOException, DivisionByZeroException, HeapAllocationException, ProcedureNotDefinedException;
}
