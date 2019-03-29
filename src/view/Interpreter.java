package view;

import controller.Controller;
import model.adt.*;
import model.exp.*;
import model.stmt.*;
import repository.IRepository;
import repository.Repository;


public class Interpreter
{
    public static void main(String args[])
    {
        /*IStmt ex1 = new CompStmt(new OpenRFileStmt("var_f","D:\\IdeaProjects\\Interpreter\\file1.txt"),
                new CompStmt(new ReadFileStmt(new VarExp("var_f"),"var_c"),
                        new CompStmt(new PrintStmt(new VarExp("var_c")),new CompStmt(new IfStmt(new VarExp("var_c"),
                                new CompStmt(new ReadFileStmt(new VarExp("var_f"),"var_c"),
                                        new PrintStmt(new VarExp("var_c"))),new PrintStmt(new ConstExp(0))),
                                new CloseRFileStmt(new VarExp("var_f"))))));
        PrgState prg1 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex1);
        IRepository repo1 = new Repository("D:\\IdeaProjects\\Interpreter\\log1.txt");
        repo1.addProgram(prg1);
        Controller ctr1 = new Controller(repo1);

        IStmt ex2 = new CompStmt(new OpenRFileStmt("var_f","D:\\IdeaProjects\\Interpreter\\file2.txt"),
                new CompStmt(new ReadFileStmt(new VarExp("var_f"),"var_c"),
                        new CompStmt(new PrintStmt(new VarExp("var_c")),new CompStmt(new IfStmt(new VarExp("var_c"),
                                new CompStmt(new ReadFileStmt(new VarExp("var_f"),"var_c"),
                                        new PrintStmt(new VarExp("var_c"))),new PrintStmt(new ConstExp(0))),
                                new CloseRFileStmt(new VarExp("var_f"))))));
        PrgState prg2 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex2);
        IRepository repo2 = new Repository("D:\\IdeaProjects\\Interpreter\\log2.txt");
        repo2.addProgram(prg2);
        Controller ctr2 = new Controller(repo2);

        IStmt ex3 = new CompStmt(new OpenRFileStmt("var_f","D:\\IdeaProjects\\Interpreter\\file1.txt"),
                new OpenRFileStmt("var_f","D:\\IdeaProjects\\Interpreter\\file1.txt"));
        PrgState prg3 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex3);
        IRepository repo3 = new Repository("D:\\IdeaProjects\\Interpreter\\log3.txt");
        repo3.addProgram(prg3);
        Controller ctr3 = new Controller(repo3);

        IStmt ex4 = new OpenRFileStmt("var_f","D:\\IdeaProjects\\Interpreter\\file0.txt");
        PrgState prg4 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex4);
        IRepository repo4 = new Repository("D:\\IdeaProjects\\Interpreter\\log4.txt");
        repo4.addProgram(prg4);
        Controller ctr4 = new Controller(repo4);

        IStmt ex5 = new ReadFileStmt(new ConstExp(1),"var_c");
        PrgState prg5 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex5);
        IRepository repo5 = new Repository("D:\\IdeaProjects\\Interpreter\\log5.txt");
        repo5.addProgram(prg5);
        Controller ctr5 = new Controller(repo5);

        IStmt ex6 = new CloseRFileStmt(new ConstExp(1));
        PrgState prg6 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex6);
        IRepository repo6 = new Repository("D:\\IdeaProjects\\Interpreter\\log6.txt");
        repo6.addProgram(prg6);
        Controller ctr6 = new Controller(repo6);

        IStmt ex7 = new CompStmt(new AssignStmt("v",new ConstExp(10)),
                new CompStmt(new HeapAllocationStmt("v",new ConstExp(20)),
                        new CompStmt(new HeapAllocationStmt("a",new ConstExp(22)),
                                new CompStmt(new PrintStmt(new VarExp("v")),new CompStmt(
                                        new PrintStmt(new ArithExp("+",new ConstExp(100),new HpReadExp("a"))),
                                        new CompStmt(new HpWriteStmt("a",new ConstExp(30)),
                                                new CompStmt(new PrintStmt(new VarExp("a")),
                                                        new CompStmt(new PrintStmt(new HpReadExp("a")),
                                                                new AssignStmt("v",new ConstExp(10))))))))));
        PrgState prg7 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex7);
        IRepository repo7 = new Repository("D:\\IdeaProjects\\Interpreter\\log7.txt");
        repo7.addProgram(prg7);
        Controller ctr7 = new Controller(repo7);

        IStmt ex8 = new HpWriteStmt("a",new ConstExp(10));
        PrgState prg8 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex8);
        IRepository repo8 = new Repository("D:\\IdeaProjects\\Interpreter\\log8.txt");
        repo8.addProgram(prg8);
        Controller ctr8 = new Controller(repo8);

        IStmt ex9 = new CompStmt(new AssignStmt("a",new ConstExp(1)),new PrintStmt(new HpReadExp("a")));
        PrgState prg9 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex9);
        IRepository repo9 = new Repository("D:\\IdeaProjects\\Interpreter\\log9.txt");
        repo9.addProgram(prg9);
        Controller ctr9 = new Controller(repo9);

        IStmt ex10 = new HeapAllocationStmt("v",new ConstExp(3));
        PrgState prg10 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),
                new MyHeap<>(),ex10);
        IRepository repo10 = new Repository("D:\\IdeaProjects\\Interpreter\\log10.txt");
        repo10.addProgram(prg10);
        Controller ctr10 = new Controller(repo10);

        IStmt ex11 = new CompStmt(
                new PrintStmt(new ArithExp("+",new ConstExp(10),new BoolExp(new ConstExp(2),new ConstExp(6),"<"))),
                new PrintStmt(new BoolExp(new ArithExp("+",new ConstExp(10),new ConstExp(2)),new ConstExp(6),"<=")));
        PrgState prg11 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),
                new MyHeap<>(),ex11);
        IRepository repo11 = new Repository("D:\\IdeaProjects\\Interpreter\\log11.txt");
        repo11.addProgram(prg11);
        Controller ctr11 = new Controller(repo11);

        IStmt ex12 = new CompStmt(new AssignStmt("v",new ConstExp(6)),new CompStmt(
                new WhileStmt(new ArithExp("-",new VarExp("v"),new ConstExp(4)),
                        new CompStmt(new PrintStmt(new VarExp("v")),
                                new AssignStmt("v",new ArithExp("-",new VarExp("v"),new ConstExp(1))))),
                new PrintStmt(new VarExp("v"))));
        PrgState prg12 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),
                new MyHeap<>(),ex12);
        IRepository repo12 = new Repository("D:\\IdeaProjects\\Interpreter\\log12.txt");
        repo12.addProgram(prg12);
        Controller ctr12 = new Controller(repo12);

        IStmt ex13 = new CompStmt(new OpenRFileStmt("f1","D:\\IdeaProjects\\Interpreter\\file1.txt"),
                new OpenRFileStmt("f2","D:\\IdeaProjects\\Interpreter\\file2.txt"));
        PrgState prg13 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),
                new MyHeap<>(),ex13);
        IRepository repo13 = new Repository("D:\\IdeaProjects\\Interpreter\\log13.txt");
        repo13.addProgram(prg13);
        Controller ctr13 = new Controller(repo13);

        IStmt ex14 = new CompStmt(new AssignStmt("v",new ConstExp(10)),
                new CompStmt(new HeapAllocationStmt("a",new ConstExp(22)),new CompStmt(new ForkStmt(
                        new CompStmt(new HpWriteStmt("a",new ConstExp(30)),
                                new CompStmt(new AssignStmt("v",new ConstExp(32)),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new CompStmt(new PrintStmt(new HpReadExp("a")),
                                                        new ReadFileStmt(new VarExp("a"),"a")))))),
                        new CompStmt(new PrintStmt(new VarExp("v")),new CompStmt(new PrintStmt(new HpReadExp("a")),
                                new OpenRFileStmt("fd","D:\\IdeaProjects\\Interpreter\\file1.txt"))))));
        PrgState prg14 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),
                new MyHeap<>(),ex14);
        IRepository repo14 = new Repository("D:\\IdeaProjects\\Interpreter\\log14.txt");
        repo14.addProgram(prg14);
        Controller ctr14 = new Controller(repo14);

        IStmt ex15 = new CompStmt(new ForkStmt(
                new CompStmt(new ForkStmt(new AssignStmt("a",new ConstExp(10))),new PrintStmt(new ConstExp(30)))),
                new PrintStmt(new ConstExp(1)));
        PrgState prg15 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),
                new MyHeap<>(),ex15);
        IRepository repo15 = new Repository("D:\\IdeaProjects\\Interpreter\\log15.txt");
        repo15.addProgram(prg15);
        Controller ctr15 = new Controller(repo15);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand(0,"exit"));
        menu.addCommand(new RunExample(1,ex1.toString(),ctr1));
        menu.addCommand(new RunExample(2,ex2.toString(),ctr2));
        menu.addCommand(new RunExample(3,ex3.toString(),ctr3));
        menu.addCommand(new RunExample(4,ex4.toString(),ctr4));
        menu.addCommand(new RunExample(5,ex5.toString(),ctr5));
        menu.addCommand(new RunExample(6,ex6.toString(),ctr6));
        menu.addCommand(new RunExample(7,ex7.toString(),ctr7));
        menu.addCommand(new RunExample(8,ex8.toString(),ctr8));
        menu.addCommand(new RunExample(9,ex9.toString(),ctr9));
        menu.addCommand(new RunExample(10,ex10.toString(),ctr10));
        menu.addCommand(new RunExample(11,ex11.toString(),ctr11));
        menu.addCommand(new RunExample(12,ex12.toString(),ctr12));
        menu.addCommand(new RunExample(13,ex13.toString(),ctr13));
        menu.addCommand(new RunExample(14,ex14.toString(),ctr14));
        menu.addCommand(new RunExample(15,ex15.toString(),ctr15));
        menu.show();*/
    }
}
