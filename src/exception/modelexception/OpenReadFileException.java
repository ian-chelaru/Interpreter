package exception.modelexception;

public class OpenReadFileException extends Exception
{
    public OpenReadFileException()
    {
        super("FileCanNotBeOpenedForReading");
    }
}
