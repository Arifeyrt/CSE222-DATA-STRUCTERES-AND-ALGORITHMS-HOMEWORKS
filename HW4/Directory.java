import java.util.LinkedList;

/**
 * Represents a directory in a file system.
 */
class Directory extends FileSystemElement {
    /**
     * The list of children elements (files or sub-directories) contained within
     * this directory.
     */
    private LinkedList<FileSystemElement> children;

    /**
     * Constructs a new Directory object with the specified name and parent
     * directory.
     *
     * @param name   the name of the directory
     * @param parent the parent directory of the directory
     */
    public Directory(String name, FileSystemElement parent) {
        super(name, parent);
        this.children = new LinkedList<>();
    }

    /**
     * Removes the specified element from the directory.
     *
     * @param element the element to remove
     * @return the removed element, or null if not found
     */
    public FileSystemElement remove(FileSystemElement element) {
        int i = 0;
        for (FileSystemElement e : children) {
            if (e.getName().equals(element.getName())) {
                return children.remove(i);
            }
            i++;
        }
        return null;
    }

    /**
     * Retrieves the list of children elements of this directory.
     *
     * @return the list of children elements
     */
    public LinkedList<FileSystemElement> getChildren() {
        return children;
    }

    /**
     * Adds a new element to the directory.
     *
     * @param element the element to add
     */
    public void addElement(FileSystemElement element) {
        children.add(element);
    }

    /**
     * Removes the specified element from the directory.
     *
     * @param element the element to remove
     */
    public void removeElement(FileSystemElement element) {
        children.remove(element);
    }

    /**
     * Prints the directory structure with appropriate indentation.
     *
     * @param indent the indentation string
     */
    public void print(String indent) {
        System.out.println(indent + "Directory: " + getName());
        for (FileSystemElement element : children) {
            element.print(indent + "  ");
        }
    }
}
