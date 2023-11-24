package PIM.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class LoadAPI implements API
{

    private String fileContent;

    @Override
    public int verify(String[] cmd) throws FileNotFoundException
    {
        String loadPath = cmd[1];
        if (!Files.exists(Paths.get(loadPath)))
            return 23;

        Scanner scanner = new Scanner(new File(loadPath));

        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            stringBuilder.append(line);
        }
        fileContent = stringBuilder.toString();

        scanner.close();

        if (fileContent.equals(""))
            return 24;



        return 0;
    }

    @Override
    public String[] init(String[] para)
    {

        return null;
    }

    @Override
    public void exe(String[] para)
    {


    }
}
