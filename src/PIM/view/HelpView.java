package PIM.view;

public class HelpView {

    public enum ViewPage
    {
        HelpMenu("""
        
        <Help Menu>
        Four types of PIM entities are supported: txt, task, contact, event
        Five operations are supported: new, modify, delete, print, search
        Two PIM operations are supported: load, save
        For more details please read the User Manual
        
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
