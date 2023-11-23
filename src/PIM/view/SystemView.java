package PIM.view;

public class SystemView
{
    public enum ViewPage
    {
        WelcomePage("Welcome\n"),
        ByePage("~~~Bye~~~\n"),
        Navigate(">>> "),

        InputPrompt(">>> Your input:\n>>> "),
        IdSelectionPrompt(">>> Your choice (a single option number from above): "),

        Success("< %s successfully >\n"),

        HelpMenu("""
            
            <Help>
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


