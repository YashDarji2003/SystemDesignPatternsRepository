
import java.util.ArrayList;

public class CompositePattern {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        root.add(new File("file1.txt", 1));
        root.add(new File("file2.txt", 2));
        root.add(new File("file3.txt", 3));

        Folder docs = new Folder("docs");
        docs.add(new File("resume.pdf", 4));
        docs.add(new File("photo.jpg", 4));
        docs.add(new File("notes.docx", 4));

        Folder image = new Folder("img");
        image.add(new File("photo.png", 5));
        image.add(new File("photo1.png", 5));
        root.add(docs);
        root.add(image);

        // root.ls();
       //   docs.ls();
       // root.openAll();

   //    FileSystem cdFileSystem=root.cd("img");
    //    if(cdFileSystem!= null)
    //    {
    //     cdFileSystem.ls();
    //    }
    //    else
    //    {
    //     System.out.println("not found ");
    //    }


System.out.println("total Size " +docs .getName() +" = "+docs.getSize());
    }
}

abstract class FileSystem {
    abstract void ls();

    abstract void openAll();

    abstract int getSize();

    abstract FileSystem cd(String name);

    abstract String getName();

    abstract boolean isFolder();
}

class File extends FileSystem {
    String name;
    int size;

    File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    void ls() {
        System.out.print(" " + name + " \n ");
    }

    @Override
    void openAll() {
        System.out.print(" " + name + " \n ");
    }

    @Override
    int getSize() {
        return size;
    }

    @Override
    FileSystem cd(String name) {
        return null;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    boolean isFolder() {
        return false;
    }
}

class Folder extends FileSystem {
    String name;
    ArrayList<FileSystem> child=new ArrayList<>();

    Folder(String name) {
        this.name = name;
    }

    void add(FileSystem f) {
        child.add(f);
    }

    @Override
    void ls() {
        System.out.println("Folder: " + name);
        for (FileSystem folder : child) {
            if (folder.isFolder()) {
                System.out.print(" + " + folder.getName() + " " + "\n");
            } else {
                System.out.print(" + " + folder.getName() + " " + "\n");
            }
        }
    }

    @Override
    void openAll() {
        System.out.println("opening Folder " + name + "\n");
        for (FileSystem folder : child) {
            folder.openAll();
        }
    }

    @Override
    int getSize() {
        int total = 0;
        for (FileSystem folder : child) {
            total += folder.getSize();
        }
        return total;
    }

    @Override
    FileSystem cd(String name) {
        for (FileSystem folder : child) {
            if (folder.isFolder() && folder.getName().equals(name)) {
                return folder;
            }
        }
        return null;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    boolean isFolder() {
        return true;
    }

}