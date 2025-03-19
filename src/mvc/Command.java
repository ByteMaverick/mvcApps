package mvc;

/*Edits:

Mohammed Ansari 3/10: Implemented the abstract class Command
 */
public abstract class Command {
    protected Model model;

    public Command(Model model) {
        this.model = model;
    }

    public abstract void execute() throws Exception;
}