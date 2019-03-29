package view;

import java.util.HashMap;
import java.util.Scanner;

public class TextMenu
{
    private HashMap<Integer, Command> commands;

    public TextMenu()
    {
        commands = new HashMap<>();
    }

    public void addCommand(Command c)
    {
        commands.put(c.getKey(), c);
    }

    private void printMenu()
    {
        System.out.println("\n\tMenu\n");
        for (Command com : commands.values())
        {
            String line = String.format("%4s :\n%s\n", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }

    public void show()
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            printMenu();
            System.out.println("Input the option: ");
            String line = scanner.nextLine();
            int key = Integer.parseInt(line);
            Command com = commands.get(key);
            if (com == null)
            {
                System.out.println("Invalid Option");
                continue;
            }
            com.execute();
        }
    }
}
