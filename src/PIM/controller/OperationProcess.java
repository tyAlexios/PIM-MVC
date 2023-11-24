package PIM.controller;

public interface OperationProcess
{
    int verify(String[] cmd) throws Exception;
    void process(String[] cmd) throws Exception;
}
