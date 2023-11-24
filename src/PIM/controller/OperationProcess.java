package PIM.controller;

import java.io.FileNotFoundException;

public interface OperationProcess
{
    int verify(String[] cmd) throws FileNotFoundException;
    void process(String[] cmd) throws Exception;
}
