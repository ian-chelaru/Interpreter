package controller;

import exception.modelexception.FileDescriptorException;
import exception.repositoryexception.LogFileException;
import model.adt.MyIMap;
import model.adt.MyITuple;
import model.adt.PrgState;
import repository.IRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller
{
    private IRepository repo;
    private ExecutorService executor;

    public Controller(IRepository repo)
    {
        this.repo = repo;
        executor = Executors.newFixedThreadPool(2);
    }

    public IRepository getRepo()
    {
        return repo;
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList)
    {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException
    {
        List<Callable<PrgState>> callList = prgList.stream()
                .map(p -> (Callable<PrgState>) (() -> p.oneStep()))
                .collect(Collectors.toList());

        List<PrgState> threadList = executor.invokeAll(callList)
                .stream()
                .map(future ->
                {
                    try
                    {
                        return future.get();
                    }
                    catch (InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    }
                    catch (ExecutionException e)
                    {
                        throw new RuntimeException(e);
                    }
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());

        prgList.addAll(threadList);

        /*prgList.forEach(prg ->
        {
            try
            {
                repo.logPrgStateExec(prg);
            }
            catch (LogFileException e)
            {
                throw new RuntimeException(e);
            }
        });*/

        repo.setProgramList(prgList);
    }

    public void callConservativeGarbageCollector(List<PrgState> prgList)
    {
        Set<Integer> symTablesValues = new HashSet<>();
        prgList.forEach(prg ->
        {
            symTablesValues.addAll(prg.getSymTable()
                    .getContent()
                    .values());
        });
        MyIMap<Integer> heap = prgList.get(0)
                .getHeap();
        heap.setContent(conservativeGarbageCollector(symTablesValues,heap.getContent()));
    }

    public void shutdownExecutor()
    {
        executor.shutdown();
    }

    public void closeFiles() throws LogFileException
    {
        MyIMap<MyITuple<String,BufferedReader>> fileTable = repo.getProgramList()
                .get(0)
                .getFileTable();
        closeOpenedFiles(fileTable);
        fileTable.setContent(new HashMap<>());
        for (PrgState prg : repo.getProgramList())
        {
            repo.logPrgStateExec(prg);
        }
    }

    public void allSteps() throws InterruptedException, LogFileException
    {
        repo.logPrgStateExec(repo.getProgramList()
                .get(0));
        List<PrgState> prgList = removeCompletedPrg(repo.getProgramList());

        while (prgList.size() > 0)
        {
            // one step for all programs
            oneStepForAllPrg(prgList);

            // call conservativeGarbageCollector
            Set<Integer> symTablesValues = new HashSet<>();
            prgList.forEach(prg ->
            {
                symTablesValues.addAll(prg.getSymTable()
                        .getContent()
                        .values());
            });
            MyIMap<Integer> heap = prgList.get(0)
                    .getHeap();
            heap.setContent(conservativeGarbageCollector(symTablesValues,heap.getContent()));

            // display program states

            prgList.forEach(prg ->
            {
                try
                {
                    repo.logPrgStateExec(prg);
                }
                catch (LogFileException e)
                {
                    throw new RuntimeException(e);
                }
            });

            // remove the completed programs
            prgList = removeCompletedPrg(repo.getProgramList());
        }
        executor.shutdown();

        // close files from file table
        MyIMap<MyITuple<String,BufferedReader>> fileTable = repo.getProgramList()
                .get(0)
                .getFileTable();
        closeOpenedFiles(fileTable);
        fileTable.setContent(new HashMap<>());
        for (PrgState prg : repo.getProgramList())
        {
            repo.logPrgStateExec(prg);
        }

        // update the repository state
        repo.setProgramList(prgList);
    }

    public Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
            Map<Integer,Integer> heap)
    {
        return heap.entrySet()
                .stream()
                .filter(e -> symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    public void closeOpenedFiles(MyIMap<MyITuple<String,BufferedReader>> fileTable)
    {
        fileTable.getContent()
                .values()
                .stream()
                .map(v -> v.getY())
                .forEach(br ->
                {
                    try
                    {
                        br.close();
                    }
                    catch (IOException e)
                    {
                        System.out.println("Could not close all the opened files");
                    }
                });
    }

}
