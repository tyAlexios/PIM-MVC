package PIM.view;

public class SystemView
{
    public enum ViewPage
    {
        WelcomePage("""
            ------------------------------------------------------------------------------
                      Welcome to Personal Information Manager (PIM) System
            ------------------------------------------------------------------------------
                      Enter "help" for help menu
                  
                      <Help Menu>
                      Four types of PIM entities are supported: txt, task, contact, event
                      Five operations are supported: create, modify, del, print, search
                      Two PIM operations are supported: load, save
            ------------------------------------------------------------------------------
                      Enter command after ">>> "
                      Command format: {operation} {PIR type} {PIR name}
                      Example: create txt mytxt
            ------------------------------------------------------------------------------
                      Enter "quit" to quit the system
            ------------------------------------------------------------------------------
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


