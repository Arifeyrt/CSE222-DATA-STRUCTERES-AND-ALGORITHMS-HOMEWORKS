import java.util.LinkedList;
import java.util.Comparator;
import java.util.Collections;

/**
 * Represents a file system.
 */
class FileSystem {
    /** The root directory of the file system. */
    private Directory root;
    /** The current directory in the file system. */
    private Directory currentDir;

    /**
     * Constructs a new FileSystem object with a root directory named "root".
     */
    public FileSystem() {
        this.root = new Directory("root", null);
        this.currentDir = root;
    }

    /**
     * Creates a new file with the given name and parent directory.
     *
     * @param name   the name of the file
     * @param parent the parent directory of the file
     */
    public void createFile(String name, Directory parent) {
        File file = new File(name, parent);
        parent.addElement(file);
        System.out.println("Directory created: " + name);
    }

    /**
     * Creates a new directory with the given name and parent directory.
     *
     * @param name   the name of the directory
     * @param parent the parent directory of the directory
     */
    public void createDirectory(String name, Directory parent) {
        Directory directory = new Directory(name, parent);
        parent.addElement(directory);
        System.out.println("Directory created: " + name);

    }

    /**
     * Deletes the file or directory with the given name from the specified parent
     * directory.
     *
     * @param name   the name of the file or directory to delete
     * @param parent the parent directory of the file or directory
     */
    public void delete(String name, Directory parent) {
        FileSystemElement elementToDelete = find(name, parent); // Silinecek dosya veya dizini bul
        if (elementToDelete != null) {
            // Silinecek nesne bir dosya mı yoksa bir dizin mi kontrol et
            if (elementToDelete instanceof File) {
                parent.removeElement(elementToDelete); // Dosyayı doğrudan kaldır
                System.out.println("File deleted: " + name);
            } else if (elementToDelete instanceof Directory) {
                deleteDirectoryRecursive((Directory) elementToDelete); // Dizin ise içeriğiyle birlikte sil
                parent.removeElement(elementToDelete);

            }
        } else {
            System.out.println("Error: File/directory not found.");
        }

    }

    /**
     * Recursively deletes the directory and its contents.
     *
     * @param parent the directory to delete
     */
    public void deleteDirectoryRecursive(Directory parent) {
        LinkedList<FileSystemElement> elements = parent.getChildren();
        // Her öğeyi sırayla kontrol et
        for (FileSystemElement element : elements) {
            if (element instanceof Directory) {
                // Eğer öğe bir dizin ise, içeriğini silmek için recursive olarak fonksiyonu
                // çağır
                deleteDirectoryRecursive((Directory) element);
                parent.removeElement(element);
            } else {
                // Eğer öğe bir dosya ise, direkt olarak sil
                parent.removeElement(element);
            }
        }
    }

    /**
     * Moves the file or directory with the given name to the specified new parent
     * directory.
     *
     * @param name      the name of the file or directory to move
     * @param newParent the new parent directory
     */
    public void moveElement(String name, Directory newParent) {
        FileSystemElement element = find(name, currentDir);

        // Dosya veya dizin bulunamadıysa hata mesajı yazdır ve işlemi sonlandır
        if (element == null) {
            System.out.println("Error: File/directory not found in current directory.");
            return;
        }
        currentDir.removeElement(element); // Bulunduğumuz dizinden kaldır
        element.setParent(newParent);
        newParent.addElement(element); // Yeni dizine ekle

    }

    /**
     * Searches for files or directories with the given query in the file system
     * starting from the root directory.
     *
     * @param query the search query
     */
    public void search(String query) {
        System.out.println("Searching from root...");
        searchRecursive(query, root);
    }

    /**
     * Recursively searches for files or directories with the given query starting
     * from the specified directory.
     *
     * @param query     the search query
     * @param directory the directory to start the search from
     */
    public void searchRecursive(String query, Directory directory) {
        LinkedList<FileSystemElement> elements = directory.getChildren();

        // Her öğeyi sırayla kontrol et
        for (FileSystemElement element : elements) {
            if (element instanceof Directory) {
                // Eğer öğe bir dizin ise, içeriğini aramak için recursive olarak fonksiyonu
                // çağır
                if (element.getName().equals(query)) {
                    // Dizin adı sorgu ile eşleşirse, bilgiyi yazdır ve aramayı sonlandır
                    System.out.println("Found directory: " + getPath(element));
                    return;
                } else {
                    // Eğer bir dizinse ve adı sorgu ile eşleşmiyorsa, içeriğini aramak için devam
                    // et
                    searchRecursive(query, (Directory) element);
                }
            } else if (element instanceof File) {
                // Eğer öğe bir dosya ise, dosya adını kontrol et
                if (element.getName().equals(query)) {
                    // Dosya adı sorgu ile eşleşirse, bilgiyi yazdır ve aramayı sonlandır
                    System.out.println("Found file: " + getPath(element));
                    return;
                }
            }
        }
    }

    /**
     * Returns the path of the specified file system element from the root
     * directory.
     *
     * @param file the file system element
     * @return the path of the file system element from the root directory
     */
    private String getPath(FileSystemElement file) {
        StringBuilder pathBuilder = new StringBuilder();
        FileSystemElement currentElement = file;
        // Dosyanın bulunduğu dizinlerin adını al ve pathBuilder'a ekle
        while (currentElement != null) {
            if (currentElement.getName().equals("root")) {
                break;
            }
            // Dizin adını başına ekle
            pathBuilder.insert(0, "/" + currentElement.getName());
            // Üst dizine git
            currentElement = currentElement.getParent();
        }
        return pathBuilder.toString();
    }

    /**
     * Prints the directory tree structure starting from the specified current
     * directory.
     *
     * @param currentElement the current directory element
     */
    public void printDirectoryTree(FileSystemElement currentElement) {
        System.out.println("Path to current directory from root:");

        // Mevcut öğeden root'a kadar olan tüm yolu listele
        LinkedList<String> path = getPathToRoot(currentElement);
        int a = 0;
        // Yolu tersten yazdır (root'tan mevcut öğeye)
        for (int i = 0; i < path.size() - 1; i++) {
            for (int j = 0; j <= i; j++) {
                a = 0;
                a = a + 1;
                System.out.print(" ");
            }
            System.out.println("* " + path.get(i));
        }

        for (int i = 0; i <= a + 2; i++) {
            System.out.print(" ");
        }
        System.out.println("* " + currentElement.getName() + " (Current Directory)");

        // Eğer mevcut öğe bir dizin ise, altındaki öğeleri listele
        if (currentElement instanceof Directory) {
            Directory currentDirectory = (Directory) currentElement;
            LinkedList<FileSystemElement> elements = currentDirectory.getChildren();
            for (FileSystemElement element : elements) {
                // Eğer öğe bir dizin ise, sadece adını yazdır
                if (element instanceof Directory) {
                    for (int i = 0; i <= a + 3; i++) {
                        System.out.print(" ");
                    }
                    System.out.println("* " + element.getName() + "/");

                } else {
                    for (int i = 0; i <= a + 3; i++) {
                        System.out.print(" ");
                    }

                    // Eğer öğe bir dosya ise, dosya adını yazdır
                    System.out.println(" " + element.getName());
                }
            }
        }
    }

    /**
     * Returns the path from the specified element to the root directory.
     *
     * @param element the file system element
     * @return the path from the specified element to the root directory
     */
    private LinkedList<String> getPathToRoot(FileSystemElement element) {
        LinkedList<String> path = new LinkedList<>();
        FileSystemElement currentElement = element;

        // Mevcut öğeden başlayarak root'a kadar yolu oluştur
        while (currentElement != null) {
            path.addFirst(currentElement.getName());
            currentElement = currentElement.getParent();
        }

        return path;
    }

    /**
     * Lists the contents of the specified directory.
     *
     * @param directory the directory whose contents are to be listed
     */
    public void listContents(Directory directory) {
        LinkedList<FileSystemElement> elements = directory.getChildren();

        for (FileSystemElement element : elements) {
            if (element instanceof Directory) {
                // Eğer öğe bir dizin ise "*" karakteri ile işaretleyerek listele
                System.out.println("* " + element.getName() + "/");
            } else {
                // Eğer öğe bir dosya ise sadece ismini listele
                System.out.println(element.getName());
            }
        }
    }

    /**
     * Sorts the contents of the specified directory by date created.
     *
     * @param directory the directory whose contents are to be sorted
     */
    public void sortDirectoryByDate(Directory directory) {
        LinkedList<FileSystemElement> elements = directory.getChildren();

        // Olusturma zamanına göre dosya ve dizinleri sırala
        Collections.sort(elements, new Comparator<FileSystemElement>() {
            @Override
            public int compare(FileSystemElement element1, FileSystemElement element2) {
                return element1.getDateCreated().compareTo(element2.getDateCreated());
            }
        });

        // Sıralanmış dosya ve dizinleri yazdır
        for (FileSystemElement element : elements) {
            System.out.println(element.getName() + " (" + element.getDateCreated() + ")");
        }
    }

    /**
     * Returns the current path of the specified directory relative to the root
     * directory.
     *
     * @param directory the directory whose current path is to be determined
     * @return the current path of the directory relative to the root directory
     */
    public String getCurrentPath(Directory directory) {
        StringBuilder path = new StringBuilder();
        FileSystemElement current = directory;

        // Root dizini durumunu kontrol et
        if (current == root) {
            return "/"; // Root dizinin yolu "/"
        }

        // Mevcut dizin kök dizine ulaşana kadar bir döngü kullanarak yol oluştur
        while (current != root) {
            path.insert(0, current.getName() + "/"); // Dizini başlangıç tarafına ekle
            current = current.getParent(); // Üst dizine git
        }

        return path.toString();
    }

    /**
     * Returns the current path of the current directory relative to the root
     * directory.
     *
     * @return the current path of the current directory relative to the root
     *         directory
     */
    public String getCurrentPath() {
        {
            StringBuilder path = new StringBuilder();
            FileSystemElement current = currentDir;

            // Root directory durumunu kontrol et
            if (current == root) {
                return "/"; // Root dizinin yolu "/"
            }

            // Mevcut dizin kök dizine ulaşana kadar bir döngü kullanarak yol oluştur
            while (current != root) {
                path.insert(0, current.getName() + "/"); // Dizini başlangıç ​​tarafına ekle
                current = current.getParent(); // Üst dizine git
            }

            return path.toString();
        }

    }

    /**
     * Finds the directory specified by the given path.
     *
     * @param path the path of the directory to find
     * @return the directory specified by the path, or null if not found
     */
    public Directory findcurrentpath(String path) {
        // Kök dizin kontrolü
        if (path.equals("/")) {
            return root;
        }

        // Yeni yolun başında "/" varsa, başından sil
        if (path.startsWith("/")) {
            path = path.substring(1);
        }

        // Yolu "/" karakterine göre parçala
        String[] directories = path.split("/");

        // Geçici bir dizin oluştur ve kök dizinle başlat
        Directory tempDir = root;

        // Her dizini sırayla kontrol et ve yol boyunca ilerle
        for (String directory : directories) {
            // Eğer mevcut dizin null ise, belirtilen yol bulunamadı
            if (tempDir == null) {
                return null;
            }

            // Geçici dizinde belirtilen adı ara
            boolean found = false;
            for (FileSystemElement element : tempDir.getChildren()) {
                if (element instanceof Directory && element.getName().equals(directory)) {
                    tempDir = (Directory) element;
                    found = true;
                    break;
                }
            }

            // Eğer belirtilen adlı dizin bulunamazsa, hata mesajı yaz ve işlemi sonlandır
            if (!found) {
                System.out.println("Error: Directory not found.");
                return null;
            }
        }

        // Yeni dizini mevcut dizin olarak ayarla ve başarı mesajı yazdır
        return tempDir;
    }

    /**
     * Changes the current directory to the one specified by the given path.
     *
     * @param path the path of the directory to change to
     * @return the directory after the change, or the current directory if the
     *         change fails
     */
    public Directory changeDirectory(String path) {
        // Kök dizin kontrolü
        if (path.equals("/")) {
            currentDir = root;
            System.out.println("Directory changed to: " + getCurrentPath(root));
            return currentDir;
        }

        // Yeni yolun başında "/" varsa, başından sil
        if (path.startsWith("/")) {
            path = path.substring(1);
        }

        // Yolu "/" karakterine göre parçala
        String[] directories = path.split("/");

        // Geçici bir dizin oluştur ve kök dizinle başlat
        Directory tempDir = root;

        // Her dizini sırayla kontrol et ve yol boyunca ilerle
        for (String directory : directories) {
            // Eğer mevcut dizin null ise, belirtilen yol bulunamadı
            if (tempDir == null) {
                System.out.println("Error: Directory not found.");
                return currentDir;
            }

            // Geçici dizinde belirtilen adı ara
            boolean found = false;
            for (FileSystemElement element : tempDir.getChildren()) {
                if (element instanceof Directory && element.getName().equals(directory)) {
                    tempDir = (Directory) element;
                    found = true;
                    break;
                }
            }

            // Eğer belirtilen adlı dizin bulunamazsa, hata mesajı yaz ve işlemi sonlandır
            if (!found) {
                System.out.println("Error: Directory not found.");
                return currentDir;
            }
        }

        // Yeni dizini mevcut dizin olarak ayarla ve başarı mesajı yazdır
        currentDir = tempDir;
        System.out.println("Directory changed to: " + getCurrentPath(currentDir));
        return currentDir;
    }

    /**
     * Finds the file system element (file or directory) with the specified name in
     * the given directory.
     *
     * @param name      the name of the file system element to find
     * @param directory the directory in which to search for the file system element
     * @return the file system element with the specified name, or null if not found
     */
    public FileSystemElement find(String name, Directory directory) {
        // Dizin içindeki tüm öğeleri al
        LinkedList<FileSystemElement> elements = directory.getChildren();

        // Her öğeyi sırayla kontrol et
        for (FileSystemElement element : elements) {
            // Eğer öğe adı verilen ad ile eşleşiyorsa, bu dosya veya dizini bulduk, onu
            // döndür
            if (element.getName().equals(name)) {
                return element;
            }
        }

        // Dizin içinde belirtilen ad ile eşleşen bir dosya veya dizin bulunamadı, null
        // döndür
        return null;
    }

    /**
     * Returns the root directory of the file system.
     *
     * @return the root directory of the file system
     */
    public Directory getRoot() {
        return root;
    }

    /**
     * Sets the current directory of the file system.
     *
     * @param current the directory to set as the current directory
     */
    public void setCurrent(Directory current) {
        this.currentDir = current;
    }

}
