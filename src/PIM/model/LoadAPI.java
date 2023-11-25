package PIM.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LoadAPI implements API
{

    private String fileContent;

    private List<String> overwriteKeys = null;
    public List<String[]> conflictPIRs = null;
    public List<String[]> originalPIRs = null;

    @Override
    public int verify(String[] cmd) throws FileNotFoundException
    {
        String loadPath = cmd[1];
        File loadFile = new File(loadPath);

        if (!loadFile.exists())
            return 23;

        String content = getFileContent(loadFile);

        if (content.isEmpty())
            return 24;

        return 0;
    }

    @Override
    public String[] init(String[] para) throws FileNotFoundException {
        overwriteKeys = new ArrayList<>();
        conflictPIRs = new LinkedList<>();
        originalPIRs = new LinkedList<>();

        String loadPath = para[1];
        File loadFile = new File(loadPath);
        fileContent = getFileContent(loadFile);

        String[] PIRContents = fileContent.split(StoreAPI.SEPARATE);

        for (String PIRContent : PIRContents)
        {
            String[] PIRInfo = PIRContent.split(",");
            PIR originalPIR = PIRRepo.getPIR(PIRInfo[0]);
            if (originalPIR != null)
            {
                conflictPIRs.add(PIRInfo);
                originalPIRs.add(originalPIR.getInfo());
            }
        }
        return null;
    }

    @Override
    public void exe(String[] para)
    {
        String[] PIRContents = fileContent.split(StoreAPI.SEPARATE);

        for (String PIRContent : PIRContents)
        {
            String[] PIRInfo = PIRContent.split(",");
            String key = PIRInfo[0];

            if (PIRRepo.getPIR(key) == null)
            {
                String type = key.substring(1, key.indexOf(']'));
                PIR newPIR = PIRTypeLib.createPIR(type);
                newPIR.setInfo(PIRInfo);
                PIRRepo.insertPIR(PIRInfo[0], newPIR);
            }
            else if (overwriteKeys.contains(key))
            {
                PIRRepo.getPIR(key).setInfo(PIRInfo);
            }
        }
    }

    public String getFileContent(File loadFile) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(loadFile);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            stringBuilder.append(line).append('\n');
        }
        fileContent = stringBuilder.toString();
        scanner.close();

        return fileContent;
    }

    public void setOverwriteKeys(String key)
    {
        overwriteKeys.add(key);
    }

    public List<String[]> getConflictPIRs()
    {
        return conflictPIRs;
    }

    public List<String[]> getOriginalPIRs()
    {
        return originalPIRs;
    }
}
