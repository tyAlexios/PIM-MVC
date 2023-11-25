package PIM.view;

public abstract class LoadView
{
    public enum ViewPage
    {
        NotOverwrite("<Keep the current PIR>\n"),
        YesOverwrite("<Overwrite the current PIR with the loaded one>\n"),
        ConflictDecision("""
                            
                < Conflict PIR in current PIM >
                %s
                < Conflict PIR in loading PIM >
                %s
                Do you want to overwrite this PIR?
                \t[0] No\t[1] Yes
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

    public static String getView(ViewPage page)
    {
        return page.getInfo();
    }

    public static String getView(ViewPage page, String para1, String para2)
    {
        return String.format( page.getInfo(), para1, para2 );
    }
}
