package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.adt.*;
import model.exp.*;
import model.stmt.*;
import repository.IRepository;
import repository.Repository;
import view.Command;
import view.ExitCommand;
import view.RunExample;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable
{
    private HashMap<Integer,Command> commands;

    @FXML
    private ListView<String> programList;

    @Override
    public void initialize(URL url,ResourceBundle resourceBundle)
    {
        commands = new HashMap<>();
        createCommands();
        loadData();
        programList.getSelectionModel()
                .setSelectionMode(SelectionMode.SINGLE);
    }

    public void executeProgram()
    {
        int key = programList.getSelectionModel()
                .getSelectedIndex();
        Command command = commands.get(key);
        if (command instanceof ExitCommand)
        {
            command.execute();
        }
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));

            Stage mainStage = new Stage();
            Parent root = loader.load();
            mainStage.setTitle("Interpreter");
            mainStage.setScene(new Scene(root,1300,750));
            mainStage.setResizable(false);
            mainStage.initModality(Modality.APPLICATION_MODAL);

            MainWindow controller = loader.getController();
            RunExample runCommand = (RunExample) command;
            controller.initData(runCommand);

            mainStage.show();
        }
        catch (IOException e)
        {
            System.out.println("FXML file error");
        }
    }

    public void addCommand(Command c)
    {
        commands.put(c.getKey(),c);
    }

    public void loadData()
    {
        commands.values()
                .stream()
                .map(c -> c.getKey())
                .sorted()
                .map(k -> "\t" + k + ":\n" + commands.get(k)
                        .getDescription())
                .forEach(d -> programList.getItems()
                        .add(d));
    }

    public void createCommands()
    {

        List<String> parameters = Arrays.asList("a","b");

        IStmt proc1 = new CompStmt(new AssignStmt("v",new ArithExp("+",new VarExp("a"),new VarExp("b"))),
                new PrintStmt(new VarExp("v")));
        IStmt proc2 = new CompStmt(new AssignStmt("v",new ArithExp("*",new VarExp("a"),new VarExp("b"))),
                new PrintStmt(new VarExp("v")));

        MyITable<String,MyITuple<List<String>,IStmt>> procTable = new MyTable<>();

        procTable.add("sum",new MyTuple<>(parameters,proc1));
        procTable.add("product",new MyTuple<>(parameters,proc2));

        List<Exp> actualParameters1 = Arrays.asList(new ArithExp("*",new VarExp("v"),new ConstExp(10)),new VarExp("w"));
        List<Exp> actualParameters2 = Arrays.asList(new VarExp("v"),new VarExp("w"));

        MyIDictionary<String,Integer> symTable = new MyDictionary<>();
        Stack<MyIDictionary<String,Integer>> symTableStack = new Stack<>();
        symTableStack.push(symTable);

        IStmt ex1 = new CompStmt(new AssignStmt("v",new ConstExp(2)),new CompStmt(new AssignStmt("w",new ConstExp(5)),
                new CompStmt(new CallProcStmt("sum",actualParameters1), new PrintStmt(new VarExp("v")))));
        PrgState prg1 = new PrgState(1,new MyStack<>(),symTableStack,new MyList<>(),new MyMap<>(),new MyHeap<>(),
                procTable,ex1);
        IRepository repo1 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log1.txt");
        repo1.addProgram(prg1);
        controller.Controller ctr1 = new controller.Controller(repo1);

        IStmt ex2 = new CompStmt(new AssignStmt("v",new ConstExp(2)),new CompStmt(new AssignStmt("w",new ConstExp(5)),
                new CompStmt(new CallProcStmt("sum",actualParameters1),
                        new CompStmt(new PrintStmt(new VarExp("v")), new ForkStmt(
                                new CompStmt(new CallProcStmt("product",actualParameters2),
                                        new ForkStmt(new CallProcStmt("sum",actualParameters2))))))));
        PrgState prg2 = new PrgState(1,new MyStack<>(),symTableStack,new MyList<>(),new MyMap<>(),new MyHeap<>(),
                procTable,ex2);
        IRepository repo2 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log2.txt");
        repo2.addProgram(prg2);
        controller.Controller ctr2 = new controller.Controller(repo2);

        IStmt ex3 = new CallProcStmt("f",actualParameters1);
        PrgState prg3 = new PrgState(1,new MyStack<>(),symTableStack,new MyList<>(),new MyMap<>(),new MyHeap<>(),
                procTable,ex3);
        IRepository repo3 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log3.txt");
        repo3.addProgram(prg3);
        controller.Controller ctr3 = new controller.Controller(repo3);


        addCommand(new ExitCommand(0,"exit"));
        addCommand(new RunExample(1,ex1.toString(),ctr1));
        addCommand(new RunExample(2,ex2.toString(),ctr2));
        addCommand(new RunExample(3,ex3.toString(),ctr3));

        /*IStmt ex1 = new CompStmt(new OpenRFileStmt("var_f","D:\\IdeaProjects\\Interpreter\\file1.txt"),
                new CompStmt(new ReadFileStmt(new VarExp("var_f"),"var_c"),
                        new CompStmt(new PrintStmt(new VarExp("var_c")),new CompStmt(new IfStmt(new VarExp("var_c"),
                                new CompStmt(new ReadFileStmt(new VarExp("var_f"),"var_c"),
                                        new PrintStmt(new VarExp("var_c"))),new PrintStmt(new ConstExp(0))),
                                new CloseRFileStmt(new VarExp("var_f"))))));
        PrgState prg1 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex1);
        IRepository repo1 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log1.txt");
        repo1.addProgram(prg1);
        controller.Controller ctr1 = new controller.Controller(repo1);

        IStmt ex2 = new CompStmt(new OpenRFileStmt("var_f","D:\\IdeaProjects\\Interpreter\\file2.txt"),
                new CompStmt(new ReadFileStmt(new VarExp("var_f"),"var_c"),
                        new CompStmt(new PrintStmt(new VarExp("var_c")),new CompStmt(new IfStmt(new VarExp("var_c"),
                                new CompStmt(new ReadFileStmt(new VarExp("var_f"),"var_c"),
                                        new PrintStmt(new VarExp("var_c"))),new PrintStmt(new ConstExp(0))),
                                new CloseRFileStmt(new VarExp("var_f"))))));
        PrgState prg2 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex2);
        IRepository repo2 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log2.txt");
        repo2.addProgram(prg2);
        controller.Controller ctr2 = new controller.Controller(repo2);

        IStmt ex3 = new CompStmt(new OpenRFileStmt("var_f","D:\\IdeaProjects\\Interpreter\\file1.txt"),
                new OpenRFileStmt("var_f","D:\\IdeaProjects\\Interpreter\\file1.txt"));
        PrgState prg3 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex3);
        IRepository repo3 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log3.txt");
        repo3.addProgram(prg3);
        controller.Controller ctr3 = new controller.Controller(repo3);

        IStmt ex4 = new OpenRFileStmt("var_f","D:\\IdeaProjects\\Interpreter\\file0.txt");
        PrgState prg4 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex4);
        IRepository repo4 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log4.txt");
        repo4.addProgram(prg4);
        controller.Controller ctr4 = new controller.Controller(repo4);

        IStmt ex5 = new ReadFileStmt(new ConstExp(1),"var_c");
        PrgState prg5 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex5);
        IRepository repo5 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log5.txt");
        repo5.addProgram(prg5);
        controller.Controller ctr5 = new controller.Controller(repo5);

        IStmt ex6 = new CloseRFileStmt(new ConstExp(1));
        PrgState prg6 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex6);
        IRepository repo6 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log6.txt");
        repo6.addProgram(prg6);
        controller.Controller ctr6 = new controller.Controller(repo6);

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
        IRepository repo7 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log7.txt");
        repo7.addProgram(prg7);
        controller.Controller ctr7 = new controller.Controller(repo7);

        IStmt ex8 = new HpWriteStmt("a",new ConstExp(10));
        PrgState prg8 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex8);
        IRepository repo8 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log8.txt");
        repo8.addProgram(prg8);
        controller.Controller ctr8 = new controller.Controller(repo8);

        IStmt ex9 = new CompStmt(new AssignStmt("a",new ConstExp(1)),new PrintStmt(new HpReadExp("a")));
        PrgState prg9 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),new MyHeap<>(),
                ex9);
        IRepository repo9 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log9.txt");
        repo9.addProgram(prg9);
        controller.Controller ctr9 = new controller.Controller(repo9);

        IStmt ex10 = new HeapAllocationStmt("v",new ConstExp(3));
        PrgState prg10 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),
                new MyHeap<>(),ex10);
        IRepository repo10 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log10.txt");
        repo10.addProgram(prg10);
        controller.Controller ctr10 = new controller.Controller(repo10);

        IStmt ex11 = new CompStmt(
                new PrintStmt(new ArithExp("+",new ConstExp(10),new BoolExp(new ConstExp(2),new ConstExp(6),"<"))),
                new PrintStmt(new BoolExp(new ArithExp("+",new ConstExp(10),new ConstExp(2)),new ConstExp(6),"<=")));
        PrgState prg11 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),
                new MyHeap<>(),ex11);
        IRepository repo11 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log11.txt");
        repo11.addProgram(prg11);
        controller.Controller ctr11 = new controller.Controller(repo11);

        IStmt ex12 = new CompStmt(new AssignStmt("v",new ConstExp(6)),new CompStmt(
                new WhileStmt(new ArithExp("-",new VarExp("v"),new ConstExp(4)),
                        new CompStmt(new PrintStmt(new VarExp("v")),
                                new AssignStmt("v",new ArithExp("-",new VarExp("v"),new ConstExp(1))))),
                new PrintStmt(new VarExp("v"))));
        PrgState prg12 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),
                new MyHeap<>(),ex12);
        IRepository repo12 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log12.txt");
        repo12.addProgram(prg12);
        controller.Controller ctr12 = new controller.Controller(repo12);

        IStmt ex13 = new CompStmt(new OpenRFileStmt("f1","D:\\IdeaProjects\\Interpreter\\file1.txt"),
                new OpenRFileStmt("f2","D:\\IdeaProjects\\Interpreter\\file2.txt"));
        PrgState prg13 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),
                new MyHeap<>(),ex13);
        IRepository repo13 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log13.txt");
        repo13.addProgram(prg13);
        controller.Controller ctr13 = new controller.Controller(repo13);

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
        IRepository repo14 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log14.txt");
        repo14.addProgram(prg14);
        controller.Controller ctr14 = new controller.Controller(repo14);

        IStmt ex15 = new CompStmt(new ForkStmt(
                new CompStmt(new ForkStmt(new AssignStmt("a",new ConstExp(10))),new PrintStmt(new ConstExp(30)))),
                new CompStmt(new PrintStmt(new ConstExp(1)), new PrintStmt(new ConstExp(2))));
        PrgState prg15 = new PrgState(1,new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyMap<>(),
                new MyHeap<>(),ex15);
        IRepository repo15 = new Repository("D:\\IdeaProjects\\InterpreterP1\\log15.txt");
        repo15.addProgram(prg15);
        controller.Controller ctr15 = new controller.Controller(repo15);

        addCommand(new ExitCommand(0,"exit"));
        addCommand(new RunExample(1,ex1.toString(),ctr1));
        addCommand(new RunExample(2,ex2.toString(),ctr2));
        addCommand(new RunExample(3,ex3.toString(),ctr3));
        addCommand(new RunExample(4,ex4.toString(),ctr4));
        addCommand(new RunExample(5,ex5.toString(),ctr5));
        addCommand(new RunExample(6,ex6.toString(),ctr6));
        addCommand(new RunExample(7,ex7.toString(),ctr7));
        addCommand(new RunExample(8,ex8.toString(),ctr8));
        addCommand(new RunExample(9,ex9.toString(),ctr9));
        addCommand(new RunExample(10,ex10.toString(),ctr10));
        addCommand(new RunExample(11,ex11.toString(),ctr11));
        addCommand(new RunExample(12,ex12.toString(),ctr12));
        addCommand(new RunExample(13,ex13.toString(),ctr13));
        addCommand(new RunExample(14,ex14.toString(),ctr14));
        addCommand(new RunExample(15,ex15.toString(),ctr15));*/
    }

}
