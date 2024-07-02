import java.sql.Timestamp;
import java.util.LinkedList;

/**
 * Abstract base class for any file system element (file or directory).
 */
abstract class FileSystemElement {
    /** The name of the file system element. */
    protected String name;

    /** The timestamp indicating when the file system element was created. */
    protected Timestamp dateCreated;

    /** The parent directory of the file system element. */
    protected FileSystemElement parent;

    /**
     * Constructs a new file system element with the specified name and parent
     * directory.
     *
     * @param name   the name of the file system element
     * @param parent the parent directory of the file system element
     */
    public FileSystemElement(String name, FileSystemElement parent) {
        this.name = name;
        this.parent = parent;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Retrieves the name of the file system element.
     *
     * @return the name of the file system element
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the timestamp indicating when the file system element was created.
     *
     * @return the timestamp of creation
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the parent directory of the file system element.
     *
     * @param parent the parent directory to be set
     */
    public void setParent(FileSystemElement parent) {
        this.parent = parent;
    }

    /**
     * Retrieves the parent directory of the file system element.
     *
     * @return the parent directory of the file system element
     */
    public FileSystemElement getParent() {
        return parent;
    }

    /**
     * Abstract method to be implemented by subclasses to print the file system
     * element.
     *
     * @param indent the indentation string for formatting
     */
    public abstract void print(String indent);
}
