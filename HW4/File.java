/** File class */
class File extends FileSystemElement {
    /**
     * File constructor
     * @param name the name of the file
     * @param parent the parent of the file
     */
    
    public File(String name, FileSystemElement parent) {
        super(name, parent);
    }
        /**
     * Prints the name of the file with the specified indentation.
     *
     * @param indent the indentation to be used for printing
     */
    public void print(String indent) {
        System.out.println(indent + "File: " + name);
    }
}
