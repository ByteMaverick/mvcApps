package mvc;
/*
Edits:
Anthony Kieu: 3/6 created file
Anthony Kieu: 3/6 created initial implementation of Model, with fields unsavedChanges and fileName,
              a default constructor, getters and setters for the fields, and a changed method.
 */
import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {
    private boolean unsavedChanges = false;
    private String fileName = null;

    public Model() {

    }

    public void setUnsavedChanges(boolean b) {
        unsavedChanges = b;
    }

    public boolean getUnsavedChanges() {
        return unsavedChanges;
    }

    public void setFileName(String fName) {
        fileName = fName;
    }

    public String getFileName() {
        return fileName;
    }

    public void changed() {
        unsavedChanges = true;
        this.notifySubscribers();
    }
}
