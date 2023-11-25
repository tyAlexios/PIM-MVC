package PIM.view;

public abstract class SystemView
{
    public enum ViewPage
    {
        WelcomePage("""
                -----------------------------------------------------------------------------------
                               Welcome to Personal Information Manager (PIM) System
                -----------------------------------------------------------------------------------
                             _________          ________         ______          ______
                            / /______\\ \\        \\__  __/         | _ \\ \\        / / _ |
                           | |       | |           | |           | |  \\ \\      / /  | |
                           | |_______/ /           | |           | |   \\ \\    / /   | |
                           | |________/            | |           | |    \\ \\  / /    | |
                           | |                     | |           | |     \\ \\/ /     | |
                           | |                   __| |__         | |      \\  /      | |
                           \\_/                  /_______\\        \\_/       \\/       \\_/
                           
                -----------------------------------------------------------------------------------
                          If you are a freshman, please check our user manual document to
                                           learn about how to use our PIM
                -----------------------------------------------------------------------------------
                             Type "man {command}" to check use of a specific command
                -----------------------------------------------------------------------------------
                                           Enter "quit" to quit the system
                -----------------------------------------------------------------------------------
                """),
        ByePage("~~~Bye~~~\n"),
        Navigate(">>> "),

        InputPrompt(">>> Your input:\n>>> "),
        IdSelectionPrompt(">>> Your choice (a single option number from above): "),

        Success("< %s successfully >\n"),

        HelpMenu("""
            
            <Help Menu>
            Four types of PIM entities are supported: txt, task, contact, event
            Five operations are supported: new, modify, delete, print, search
            Two PIM operations are supported: load, save
            
            """)
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

    public static String getView(ViewPage page)
    {
        return page.getInfo();
    }
}


