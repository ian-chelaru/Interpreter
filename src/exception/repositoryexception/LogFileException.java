package exception.repositoryexception;

public class LogFileException extends Exception
{
    public LogFileException()
    {
        super("Log file could not be opened");
    }
}
