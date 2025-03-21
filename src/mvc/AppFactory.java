package mvc;
/*
Edits:
Anthony Kieu: 3/6 created file

Mohammed Ansari 3/10: Implemented the entire AppFactory interface
 */

public interface AppFactory {
    public Model makeModel();

    public  View makeView(Model m);

    public String getTitle();

    public String[] getHelp() ;

    public String about();

    public String[] getEditCommands();

    public Command makeEditCommand(Model model, String type,Object source);
}
