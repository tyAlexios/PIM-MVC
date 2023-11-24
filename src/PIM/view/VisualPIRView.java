package PIM.view;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.HashMap;

public class VisualPIRView
{
    public enum ViewPage
    {
        PrintText("""
            
            <Data of %s>
            [Notes]
               %s
            
            """
        ),
        PrintTask("""
            
            <Data of %s>
            [Deadline]
                %s
            [Description]
                %s
            
            """
        ),
        PrintEvent("""
            
            <Data of %s>
            [Starting Time]
                %s
            [Alarm Time]
                %s
            [Description]
                %s
            
            """
        ),
        PrintContact("""
            
            <Data of %s>
            [Names]
                %s
            [Addresses]
                %s
            [Mobile Numbers]
                %s
            
            """
        )
        ;
        private final String info;
        ViewPage(String info) {
            this.info = info;
        }
        public String getInfo()
        {
            return info;
        }
    }

    private static final HashMap<String, ViewPage> Repository = new HashMap<>();

    static
    {
        Repository.put("txt", ViewPage.PrintText);
        Repository.put("task", ViewPage.PrintTask);
        Repository.put("contact", ViewPage.PrintContact);
        Repository.put("event", ViewPage.PrintEvent);
    }

    public static String getView(String type, String[] PIRInfo)
    {
        return String.format( Repository.get(type).getInfo(), PIRInfo);
    }

}
