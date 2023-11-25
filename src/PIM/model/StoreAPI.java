package PIM.model;

import PIM.view.VisualPIRView;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StoreAPI implements API
{
    static final String SEPARATE = "\n------------------------------------------------------------------------------\n";
    private Path savePath;
    @Override
    public int verify(String[] cmd)
    {
        List<String[]> RepoImg = PIRRepo.RepoImage();
        if (RepoImg.isEmpty())
            return 13;

        String saveDir;
        String fileName = cmd[1];
        if (cmd.length == 3)
            saveDir = cmd[2];
        else
            saveDir = System.getProperty("user.dir");

        Path savePath = Paths.get(saveDir, fileName);
        if (Files.exists(savePath))
            return 20;

        return 0;
    }

    @Override
    public String[] init(String[] para)
    {
        String saveDir;
        String fileName = para[1];
        if (para.length == 3)
            saveDir = para[2];
        else
            saveDir = System.getProperty("user.dir");

        savePath = Paths.get(saveDir, fileName);
        return new String[]{savePath.toString()};
    }

    @Override
    public void exe(String[] para) throws IOException {

        StringBuilder content = new StringBuilder();
        List<String[]> RepoImg = PIRRepo.RepoImage();

        for (String[] PIRInfo : RepoImg)
        {
            String key = PIRInfo[0];
            String PIRContent = String.join(",", PIRRepo.getPIR(key).getInfo());
            content.append(PIRContent).append(SEPARATE);
        }

        File file = new File(savePath.toUri());
        //       file.createNewFile();
//        file.setReadOnly();
        FileWriter writer = new FileWriter(file, false);
        writer.write(content.toString());
        writer.close();

    }
}
