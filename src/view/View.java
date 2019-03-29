package view;

import controller.Controller;
import exception.modelexception.*;
import exception.repositoryexception.LogFileException;
import model.adt.*;
import model.exp.ArithExp;
import model.exp.ConstExp;
import model.exp.VarExp;
import model.stmt.*;
import repository.Repository;

import java.io.BufferedReader;
import java.io.IOException;

public class View
{
    static Repository myRepo = new Repository("D:\\IdeaProjects\\Interpreter\\log.txt");
    static Controller controller = new Controller(myRepo);

    public static void main(String[] args)
    {
//        IStmt ex1 = new CompStmt(new AssignStmt("v",new ConstExp(2)),new PrintStmt(new VarExp("v")));
//        IStmt ex2 = new CompStmt(new AssignStmt("a",new ArithExp("+",new ConstExp(2),new ArithExp("*",new ConstExp(3)
//                ,new ConstExp(5)))),new CompStmt(new AssignStmt("b",
//                new ArithExp("+",new VarExp("a"),new ConstExp(1))),new PrintStmt(new VarExp("b"))));
//        IStmt ex3 = new CompStmt(new AssignStmt("a",new ArithExp("-",new ConstExp(2),new ConstExp(2))),
//                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ConstExp(2)),new AssignStmt("v",
//                        new ConstExp(3))),new PrintStmt(new VarExp("v"))));
//        IStmt ex4 = new PrintStmt(new ArithExp("/",new ConstExp(1),new ConstExp(0)));
//        IStmt ex5 = new PrintStmt(new VarExp("a"));
//        IStmt ex6 = new PrintStmt(new ArithExp("noop",new ConstExp(1),new ConstExp(2)));
//        IStmt ex7 = new OpenRFileStmt("fd","D:\\IdeaProjects\\Interpreter\\log1.txt");
//        IStmt ex8 = new CompStmt(new OpenRFileStmt("var_f","D:\\IdeaProjects\\Interpreter\\log2.txt"),
//                new CompStmt(new ReadFileStmt(new VarExp("var_f"),"var_c"),new CompStmt(new PrintStmt(new VarExp(
//                        "var_c")),new CompStmt(new IfStmt(new VarExp("var_c"),
//                        new CompStmt(new ReadFileStmt(new VarExp("var_f"),"var_c"),
//                                new PrintStmt(new VarExp("var_c"))),new PrintStmt(new ConstExp(0))),
//                        new CloseRFileStmt(new VarExp("var_f"))))));
//        IStmt ex9 = new CompStmt(new OpenRFileStmt("var_f","D:\\IdeaProjects\\Interpreter\\log3.txt"),
//                new CompStmt(new ReadFileStmt(new VarExp("var_f"),"var_c"),new CompStmt(new PrintStmt(new VarExp(
//                        "var_c")),new CompStmt(new IfStmt(new VarExp("var_c"),
//                        new CompStmt(new ReadFileStmt(new VarExp("var_f"),"var_c"),
//                                new PrintStmt(new VarExp("var_c"))),new PrintStmt(new ConstExp(0))),
//                        new CloseRFileStmt(new VarExp("var_f"))))));
//        IStmt ex10 = new CompStmt(new OpenRFileStmt("var_f", "D:\\IdeaProjects\\Interpreter\\log2.txt"),
//                        new OpenRFileStmt("var_f", "D:\\IdeaProjects\\Interpreter\\log2.txt"));
//        IStmt ex11 = new OpenRFileStmt("var_f", "D:\\IdeaProjects\\Interpreter\\log0.txt");
//        IStmt ex12 = new ReadFileStmt(new ConstExp(1), "var_c");
//        IStmt ex13 = new CloseRFileStmt(new ConstExp(1));


//        MyIStack<IStmt> stk = new MyStack<>();
//        MyIDictionary<String,Integer> symTbl = new MyDictionary<>();
//        MyIList<Integer> out = new MyList<>();
//        MyIMap<MyITuple<String,BufferedReader>> fileTable = new MyMap<>();
//        PrgState program1 = new PrgState(stk,symTbl,out,fileTable,ex13);
//        myRepo.addProgram(program1);

//        try
//        {
//            controller.allSteps();
//        }
//        catch (VariableNotDefinedException e)
//        {
//            System.out.println("VariableNotDefined");
//        }
//        catch (DivisionByZeroException e)
//        {
//            System.out.println("Division by 0");
//        }
//        catch (InvalidOperatorException e)
//        {
//            System.out.println("InvalidOperator");
//        }
//        catch (FileAlreadyOpenedException e)
//        {
//            System.out.println("FileAlreadyOpened");
//        }
//        catch (FileDescriptorException e)
//        {
//            System.out.println("File descriptor not found");
//        }
//        catch (OpenReadFileException e)
//        {
//            System.out.println("File can not be opened for reading");
//        }
//        catch (IOException e)
//        {
//            System.out.println("I/O error");
//        }
//        catch (LogFileException e)
//        {
//            System.out.println("Log file could not be opened");
//        }

    }
}
