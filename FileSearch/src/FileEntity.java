import java.io.File;
import java.util.ArrayList;

public class FileEntity {
    private ArrayList<FileEntity> list_of_files = new  ArrayList<>();
    private FileEntity parent;
    private File file;
    private long File_Size;

    public FileEntity(File f){
        this.file = f;
        File[] generate_list = file.listFiles();
        if(generate_list != null) {
            for(int i = 0; i < generate_list.length; i++) {
                list_of_files.add(new FileEntity(generate_list[i], this));
            }
        }
        File_Size = this.getFileSize();
    }

    public FileEntity(File f, FileEntity parent){
        this.file = f;
        this.parent = parent;
        File[] generate_list = file.listFiles();
        if(generate_list != null) {
            for (File value : generate_list) {
                list_of_files.add(new FileEntity(value, this));
            }
        }
        File_Size = this.getFileSize();
    }

    public long getFileSize(){
        long byte_size = 0;
        for(FileEntity f: this.list_of_files){
            byte_size += f.getFile().length();
        }
        return byte_size;
    }

    public File getFile() {
        return file;
    }

    public ArrayList<FileEntity> getList_of_files() {
        return list_of_files;
    }

    public long getFile_Size() {
        return File_Size;
    }

    public FileEntity getParent() {
        return parent;
    }
}
