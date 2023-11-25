package PIM.view;

import java.util.HashMap;

public abstract class HelpView {

    public enum ViewPage
    {
        CreateHelp("""
                
                [Command format]
                \tcreate {PIRtype} {PIRname}
                [Functionality]
                \tCreate a {PIRtype} type of PIR named as {PIRname}. Then you will follow a menu to fill in data of the PIR
                [Parameter]
                \t{PIRtype} -> txt task event contact
                \t{PIRname} -> anything]
                
                """),
        ModifyHelp("""
                
                [Command format]
                \tmodify {PIRtype} {PIRname}
                [Functionality]
                \tModify a {PIRtype} type of PIR named as {PIRname}. Then you will follow a menu to modify data of the PIR
                [Parameter]
                \t{PIRtype} -> txt task event contact
                \t{PIRname} -> any name that already exists
                
                """),

        DeleteHelp("""
                
                [Command format]
                \tdel {PIRtype} {PIRname}
                [Functionality]
                \tDelete a {PIRtype} type of PIR named as {PIRname}
                [Parameter]
                \t{PIRtype} -> txt task event contact
                \t{PIRname} -> any name that already exists
                
                """),

        SearchHelp("""
                
                [Command format]
                \tsearch {expression}
                \tsearch -{PIRtype}
                \tsearch -{PIRtype} {expression}
                [Functionality]
                \tsearch {expression}
                \t\tSearch PIRs fulfilling the condition of {expression}
                \tsearch -{PIRtype}
                \t\tSearch all PIRs which are as type of {PIRtype}
                \tsearch -{PIRtype} {expression}
                \t\tSearch all PIRs that are as type of {PIRtype} and fulfill the condition of {expression}
                \tType and name of PIRs will return. You might use "print" command to check more PIR details.
                [Parameter]
                \t{PIRtype} -> txt task event contact
                \t{expression} ->
                \t\t"string"
                \t\t\tFor string searches, double quotes are mandatory. Besides, if the {string} contains '"' character, replace '"' with '\\"'
                \t\t{operator}{time}
                \t\t\tFor time searches, there is NO SPACE between {operator} and {time}. {operator} includes '>' '<' '='. {time} should in format as {yyyy-MM-dd-HH:mm} and {HH:mm}
                \t\t&& || ! ( )
                \t\t\tFor condition combination of string and time searches.
                \t\tNotice that all the above components should be separate by white spaces with each other.
                
                """),

        PrintHelp("""
                
                [Command format]
                \tprint -a
                \tprint {PIRtype} {PIRname}
                [Functionality]
                \tprint -a
                \t\tprint all PIRs
                \tprint {PIRtype} {PIRname}
                \t\tprint a specific PIR
                [Parameter]
                \t{PIRtype} -> txt task event contact
                \t{PIRname} -> any name that already exists
                
                """),

        LoadHelp("""
                
                [Command format]
                \tload {PIMpath}
                [Functionality]
                \tload all PIRs in another PIM system stored in {PIMpath}.
                \tBesides, if there are conflict PIRs, you can only choose one of them.
                [Parameter]
                \t{PIMpath} -> This path should exist and the file name must have a ".pim" suffix.
                [Notice]
                \tWe assume the PIM system to load is stored by our system and in right data format.
                
                """),

        StoreHelp("""
                
                [Command format]
                \tstore {PIMname}
                \tstore {PIMname} {SaveDir}
                [Functionality]
                \tstore {PIMname}
                \t\tStore all PIRs to a default directory which is the root directory of this product
                \tstore {PIMname} {SaveDir}
                \t\tStore all PIRs to a specific {SaveDir}
                [Parameter]
                \t{PIMname} -> The file name must have a ".pim" suffix.
                \t{SaveDir} -> The directory must exist.
                
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


    private static final HashMap<String, ViewPage> Repository = new HashMap<>();

    static
    {
        Repository.put("create", ViewPage.CreateHelp);
        Repository.put("modify", ViewPage.ModifyHelp);
        Repository.put("del", ViewPage.DeleteHelp);
        Repository.put("search", ViewPage.SearchHelp);
        Repository.put("print", ViewPage.PrintHelp);
        Repository.put("store", ViewPage.StoreHelp);
        Repository.put("load", ViewPage.LoadHelp);
    }

    public static String getView(String type)
    {
        return Repository.get(type).getInfo();
    }
}
