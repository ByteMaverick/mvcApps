package mineField;
/*
Edit History:
Anthony Kieu: 3/11 created MineFieldFactory, implemented all methods except for makeView and makeEditCommands, pending completion

Mohammed Ansari 3/11: Implemented makeView(), and makeEditCommand()
 */
import mvc.*;

public class MineFieldFactory implements AppFactory {
    public Model makeModel() {
        return new MineField();
    }

    public View makeView(Model model) {
        return  new MineFieldView((MineField) model);
    }

    public String getTitle() {
        return "Mine Field";
    }

    public String[] getHelp() {
        String[] help = {
                "N: move player to the tile above",
                "S: move player to the tile below",
                "E: move player to the tile to the right",
                "W: move player to the tile to the left",
                "NW: move player to the top left diagonal tile",
                "NE: move player to the top right diagonal tile",
                "SW: move player to the bottom left diagonal tile",
                "SE: move player to the bottom right diagonal tile",
        };
        return help;
    }

    public String about() {
        return "Mine Field, created by team 5: Anthony Kieu, Mohammed Ansari, Akanksha Bodke";
    }

    public String[] getEditCommands() {
        return new String[] {
                "N", "S", "E", "W", "NW", "NE", "SW", "SE"
        };
    }

    public Command makeEditCommand(Model model, String type,Object source) {
        MoveCommand moveCommand = null;
        switch (type) {
            case "N":
                return new MoveCommand((MineField) model, Heading.N);
            case "S":
                return new MoveCommand((MineField) model, Heading.S);
            case "E":
                return new MoveCommand((MineField) model, Heading.E);
            case "W":
                return new MoveCommand((MineField) model, Heading.W);
            case "NW":
                return new MoveCommand((MineField) model, Heading.NW);
            case "NE":
                return new MoveCommand((MineField) model, Heading.NE);
            case "SW":
                return new MoveCommand((MineField) model, Heading.SW);
            case "SE":
                return new MoveCommand((MineField) model, Heading.SE);


            default: return null;

        }

    }
}
