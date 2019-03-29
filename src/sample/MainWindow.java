package sample;

import controller.Controller;
import exception.repositoryexception.LogFileException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.adt.MyITuple;
import model.adt.MyTuple;
import model.adt.PrgState;
import model.stmt.IStmt;
import repository.IRepository;
import view.RunExample;

import java.io.BufferedReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainWindow implements Initializable
{
    @FXML
    private TextField numberOfPrgStates;
    @FXML
    private Button runOneStep;
    @FXML
    private TableView<MyITuple<String,Integer>> symTable;
    @FXML
    private TableView<MyITuple<Integer,String>> fileTable;
    @FXML
    private TableView<MyITuple<Integer,Integer>> heapTable;
    @FXML
    private TableView<MyITuple<String,MyITuple<List<String>,IStmt>>> procTable;
    @FXML
    private ListView<Integer> idList;
    @FXML
    private ListView<String> exeStack;
    @FXML
    private ListView<Integer> output;
    @FXML
    private TableColumn<MyITuple<String,Integer>,String> symVarColumn;
    @FXML
    private TableColumn<MyITuple<String,Integer>,Integer> symValueColumn;
    @FXML
    private TableColumn<MyITuple<Integer,String>,Integer> fileDescriptorColumn;
    @FXML
    private TableColumn<MyITuple<Integer,String>,String> fileNameColumn;
    @FXML
    private TableColumn<MyITuple<Integer,Integer>,Integer> heapAddressColumn;
    @FXML
    private TableColumn<MyITuple<Integer,Integer>,Integer> heapValueColumn;
    @FXML
    private TableColumn<MyITuple<String,MyITuple<List<String>,IStmt>>,String> procNameColumn;
    @FXML
    private TableColumn<MyITuple<String,MyITuple<List<String>,IStmt>>,MyITuple<List<String>,IStmt>> bodyProcColumn;
    @FXML
    private TextField errorTf;

    private Controller controller;
    private IRepository repo;
    private PrgState currentPrgState;
    private List<PrgState> prgList;

    public void initData(RunExample command)
    {
        controller = command.getCtr();
        repo = controller.getRepo();
        currentPrgState = repo.getProgramList()
                .get(0);
        try
        {
            repo.logPrgStateExec(currentPrgState);
        }
        catch (LogFileException e)
        {
            errorTf.setText("LogFileException");
            runOneStep.setDisable(true);
        }
        prgList = controller.removeCompletedPrg(repo.getProgramList());
        updateGUI();
    }

    @Override
    public void initialize(URL url,ResourceBundle resourceBundle)
    {
        idList.getSelectionModel()
                .setSelectionMode(SelectionMode.SINGLE);

        symVarColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        symValueColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

        fileDescriptorColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

        heapAddressColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        heapValueColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

        procNameColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        bodyProcColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
    }

    public void updateGUI()
    {
        numberOfPrgStates.setText(String.valueOf(repo.getProgramList()
                .size()));
        exeStack.setItems(getExeStackValues());
        idList.setItems(getPrgStateIds());
        symTable.setItems(getSymTableValues());
        output.setItems(getOutputValues());
        fileTable.setItems(getFileTableValues());
        heapTable.setItems(getHeapValues());
        procTable.setItems(getProcTableValues());
    }

    @FXML
    public void oneStep()
    {
        try
        {
            if (prgList.size() > 0)
            {
                controller.oneStepForAllPrg(prgList);
                controller.callConservativeGarbageCollector(prgList);
                for (PrgState prg : prgList)
                {
                    repo.logPrgStateExec(prg);
                }
                prgList = controller.removeCompletedPrg(repo.getProgramList());
            }
            else
            {
                controller.shutdownExecutor();
                controller.closeFiles();
                repo.setProgramList(prgList);
                runOneStep.setDisable(true);
            }
        }
        catch (InterruptedException e)
        {
            //System.err.println("Interrupted Exception occurred");
            errorTf.setText("Interrupted Exception occurred");
            runOneStep.setDisable(true);
        }
        catch (RuntimeException e)
        {
            String[] s = e.getMessage().split(" ");
            errorTf.setText(s[s.length - 1]);
            //System.err.println("Runtime Exception:  " + s[s.length - 1]);
            //e.printStackTrace();
            runOneStep.setDisable(true);
        }
        catch (LogFileException e)
        {
            errorTf.setText("LogFileException");
            runOneStep.setDisable(true);
        }
        updateGUI();
    }

    @FXML
    public void handlePrgStateSelection()
    {
        int id = idList.getSelectionModel()
                .getSelectedItem();
        currentPrgState = repo.getPrgStateById(id);
        updateGUI();
    }


    public ObservableList<String> getExeStackValues()
    {
        Stack<IStmt> exeStack = currentPrgState.getExeStack()
                .getContent();
        List<String> list = exeStack.stream()
                .map(IStmt::toString)
                .collect(Collectors.toList());
        Collections.reverse(list);
        return FXCollections.observableArrayList(list);
    }

    public ObservableList<Integer> getPrgStateIds()
    {
        return FXCollections.observableArrayList(repo.getProgramList()
                .stream()
                .map(PrgState::getId)
                .collect(Collectors.toList()));
    }

    public ObservableList<Integer> getOutputValues()
    {
        return FXCollections.observableArrayList(currentPrgState.getOut()
                .getContent());
    }

    public ObservableList<MyITuple<String,Integer>> getSymTableValues()
    {
        ObservableList<MyITuple<String,Integer>> list = FXCollections.observableArrayList();
        HashMap<String,Integer> map = currentPrgState.getSymTable()
                .getContent();
        map.keySet()
                .forEach(key -> list.add(new MyTuple<>(key,map.get(key))));
        return list;
    }

    public ObservableList<MyITuple<Integer,String>> getFileTableValues()
    {
        ObservableList<MyITuple<Integer,String>> list = FXCollections.observableArrayList();
        Map<Integer,MyITuple<String,BufferedReader>> map = currentPrgState.getFileTable()
                .getContent();
        map.keySet()
                .forEach(key -> list.add(new MyTuple<>(key,map.get(key)
                        .getX())));
        return list;
    }

    public ObservableList<MyITuple<Integer,Integer>> getHeapValues()
    {
        ObservableList<MyITuple<Integer,Integer>> list = FXCollections.observableArrayList();
        Map<Integer,Integer> map = currentPrgState.getHeap()
                .getContent();
        map.keySet()
                .forEach(key -> list.add(new MyTuple<>(key,map.get(key))));
        return list;
    }

    public ObservableList<MyITuple<String,MyITuple<List<String>,IStmt>>> getProcTableValues()
    {
        ObservableList<MyITuple<String,MyITuple<List<String>,IStmt>>> list = FXCollections.observableArrayList();
        Map<String,MyITuple<List<String>,IStmt>> map = currentPrgState.getProcTable()
                .getContent();
        map.keySet()
                .forEach(key -> list.add(new MyTuple<>(key,map.get(key))));
        return list;
    }
}
