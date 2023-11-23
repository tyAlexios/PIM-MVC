package PIM.view;

import java.util.HashMap;

public class UpdateView
{
    public enum ViewPage
    {
        PreVersion("[Previous Data]\n\t%s\n"),
        FillEssential("<Can not save> Please fill all the essential information\n"),
        ModifyConfirm("""
                        
                        [Preview]
                        %s
                        Do you want to confirm?
                        \t[0] No\t[1] Yes
                        
                        """
        ),

        TextMenu("""
            
            <%s Text>
            Please select the appropriate option to perform the operation you want.
            Fields marked with an '*' are essential.
            \t[0] Done               - Finish and save
            \t[1] Cancel             - Go back and do NOT save
            \t[2] *Notes             - Add or edit your notes
            
            """
        ),
        TaskMenu("""
            
            <%s Task>
            Please select the appropriate option to perform the operation you want.
            Fields marked with an '*' are essential.
            \t[0] Done               - Finish and save
            \t[1] Cancel             - Go back and do NOT save
            \t[2] *Deadline          - Add or edit the deadline time in format "YYYY-MM-DD-HH:MM"
            \t[3] Description        - Add or edit the description
            
            """
        ),
        EventMenu("""
            
            <%s Event>
            Please select the appropriate option to perform the operation you want.
            Fields marked with an '*' are essential.
            \t[0] Done               - Finish and save
            \t[1] Cancel             - Go back and do NOT save
            \t[2] *Starting Time     - Add or edit the starting time in format "YYYY-MM-DD-HH:MM"
            \t[3] *Alarm Time        - Add or edit the alarm time in format "YYYY-MM-DD-HH:MM"
            \t[4] Description        - Add or edit the description
            
            """
        ),
        ContactMenu("""
            
            <%s Contact>
            Please select the appropriate option to perform the operation you want.
            Fields marked with an '*' are essential.
            \t[0] Done               - Finish and save
            \t[1] Cancel             - Go back and do NOT save
            \t[2] *Names             - Add or edit names of contact persons
            \t[3] *Addresses         - Add or edit addresses of contact persons
            \t[4] *Mobile Numbers    - Add or edit mobile numbers of contact persons
            
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
        Repository.put("txt", ViewPage.TextMenu);
        Repository.put("task", ViewPage.TaskMenu);
        Repository.put("contact", ViewPage.ContactMenu);
        Repository.put("event", ViewPage.EventMenu);
    }

    public static String getView(String op, String type)
    {
        return String.format( Repository.get(type).getInfo(), op.toUpperCase());
    }

    public static String getView(ViewPage page, String para)
    {
        return String.format(page.getInfo(), para);
    }

    public static String getView(ViewPage page)
    {
        return page.getInfo();
    }

}
