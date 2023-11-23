package PIM.view;

public class DeleteView
{
    public enum ViewPage
    {
        DeleteCancel("<Deletion Cancelled>\n"),
        DeleteConfirm("""
            
            <DELETE Confirmation>
            [PIR information]
            %s
            Do you want to delete this PIR FOREVER?
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

    public static String getView(ViewPage page, String para)
    {
        return String.format( page.getInfo(), para );
    }

}
