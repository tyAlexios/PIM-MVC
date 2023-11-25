package PIM.model;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface API
{
    int verify(String[] cmd) throws FileNotFoundException;
    String[] init(String[] para) throws FileNotFoundException;
    void exe(String[] para) throws Exception;
}
