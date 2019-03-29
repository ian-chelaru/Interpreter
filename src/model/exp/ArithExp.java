package model.exp;

import exception.modelexception.DivisionByZeroException;
import exception.modelexception.HeapAllocationException;
import exception.modelexception.InvalidOperatorException;
import exception.modelexception.VariableNotDefinedException;
import model.adt.MyIDictionary;
import model.adt.MyIMap;

public class ArithExp extends Exp
{
    private Exp exp1;
    private Exp exp2;
    private String op;

    public ArithExp(String op,Exp exp1,Exp exp2)
    {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }

    @Override
    public int eval(MyIDictionary<String,Integer> tbl,MyIMap<Integer> hp) throws DivisionByZeroException,
            VariableNotDefinedException, InvalidOperatorException, HeapAllocationException
    {
        switch (op)
        {
            case "+":
                return exp1.eval(tbl,hp) + exp2.eval(tbl,hp);
            case "-":
                return exp1.eval(tbl,hp) - exp2.eval(tbl,hp);
            case "*":
                return exp1.eval(tbl,hp) * exp2.eval(tbl,hp);
            case "/":
                try
                {
                    return exp1.eval(tbl,hp) / exp2.eval(tbl,hp);
                }
                catch (ArithmeticException e)
                {
                    throw new DivisionByZeroException();
                }
            default:
                throw new InvalidOperatorException();
        }
    }

    public String toString()
    {
        return "(" + exp1.toString() + op + exp2.toString() + ")";
    }
}
