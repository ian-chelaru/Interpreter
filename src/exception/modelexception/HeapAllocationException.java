package exception.modelexception;

public class HeapAllocationException extends Exception
{
    public HeapAllocationException()
    {
        super("HeapLocationIsNotAllocated");
    }
}
