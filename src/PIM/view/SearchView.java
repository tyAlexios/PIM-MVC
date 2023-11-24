package PIM.view;

public class SearchView
{
    public enum ViewPage
    {
        SearchNone("<No eligible PIR>\n"),
        SearchTitle("\n[Search result]\n\n")
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
