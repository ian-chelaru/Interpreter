package exception.modelexception;

public class FileDescriptorException extends Exception
{
    public FileDescriptorException()
    {
        super("FileDescriptorNotFound");
    }
}
