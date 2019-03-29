package model.exp;

import exception.modelexception.DivisionByZeroException;
import exception.modelexception.HeapAllocationException;
import exception.modelexception.InvalidOperatorException;
import exception.modelexception.VariableNotDefinedException;
import model.adt.MyIDictionary;
import model.adt.MyIMap;

public class BoolExp extends Exp
{
    private Exp exp1;
    private Exp exp2;
    private String operator;

    public BoolExp(Exp exp1,Exp exp2,String op)
    {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operator = op;
    }

    @Override
    public int eval(MyIDictionary<String,Integer> tbl,MyIMap<Integer> hp) throws DivisionByZeroException,
            VariableNotDefinedException, InvalidOperatorException, HeapAllocationException
    {
        switch (operator)
        {
            case "<":
                if (exp1.eval(tbl,hp) < exp2.eval(tbl,hp))
                {
                    return 1;
                }
                return 0;
            case "<=":
                if (exp1.eval(tbl,hp) <= exp2.eval(tbl,hp))
                {
                    return 1;
                }
                return 0;
            case "==":
                if (exp1.eval(tbl,hp) == exp2.eval(tbl,hp))
                {
                    return 1;
                }
                return 0;
            case "!=":
                if (exp1.eval(tbl,hp) != exp2.eval(tbl,hp))
                {
                    return 1;
                }
                return 0;
            case ">":
                if (exp1.eval(tbl,hp) > exp2.eval(tbl,hp))
                {
                    return 1;
                }
                return 0;
            case ">=":
                if (exp1.eval(tbl,hp) >= exp2.eval(tbl,hp))
                {
                    return 1;
                }
                return 0;
            default:
                throw new InvalidOperatorException();
        }
    }

    @Override
    public String toString()
    {
        return "(" + exp1.toString() + operator + exp2.toString() + ")";
    }
}
