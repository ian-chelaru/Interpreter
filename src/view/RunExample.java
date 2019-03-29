package view;

import controller.Controller;
import exception.modelexception.*;
import exception.repositoryexception.LogFileException;

import java.io.IOException;

public class RunExample extends Command
{
    private Controller ctr;

    public RunExample(int key,String description,Controller ctr)
    {
        super(key,description);
        this.ctr = ctr;
    }

    public Controller getCtr()
    {
        return ctr;
    }

    @Override
    public void execute()
    {
        try
        {
            ctr.allSteps();
        }
        catch (InterruptedException e)
        {
            System.err.println("Interrupted Exception occurred");
        }
        catch (LogFileException e)
        {
            System.err.println("Log file could not be opened");
        }
        catch (RuntimeException e)
        {
            System.err.println("Runtime Exception:  " + e.getMessage());
            //e.printStackTrace();
        }
        /*catch (VariableNotDefinedException e)
        {
            System.err.println("VariableNotDefined");
        }
        catch (DivisionByZeroException e)
        {
            System.err.println("Division by 0");
        }
        catch (InvalidOperatorException e)
        {
            System.err.println("InvalidOperator");
        }
        catch (FileAlreadyOpenedException e)
        {
            System.err.println("FileAlreadyOpened");
        }
        catch (FileDescriptorException e)
        {
            System.err.println("File descriptor not found");
        }
        catch (OpenReadFileException e)
        {
            System.err.println("File can not be opened for reading");
        }
        catch (IOException e)
        {
            System.err.println("I/O error");
        }
        catch (HeapAllocationException e)
        {
            System.err.println("Heap location is not allocated");
        }*/
    }
}
