package PIM.model;

public interface API
{
    int verify(String[] cmd);
    String[] init(String[] para);
    void exe(String[] para);
}
