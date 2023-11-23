package PIM.controller;

import java.util.Scanner;

public class ScannerStream implements Stream
{
    private final Scanner scanner;

    ScannerStream()
    {
        scanner = new Scanner(System.in);
    }

    public String in()
    {
        return scanner.nextLine();
    }

    public void out(String outStr)
    {
        System.out.printf("%s", outStr);
    }

    public void close()
    {
        scanner.close();
    }


}
