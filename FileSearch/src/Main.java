import java.io.File;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static boolean running = true;
    private static String[] commands = {"$ls", "$size", "$cd", "$get_dir", "$exit", "$back"};


    public static void main(String[] args) {
        System.out.println("Building Entities");
        FileEntity main_entity = new FileEntity(new File("C:\\"));
        ArrayList<FileEntity> list = main_entity.getList_of_files();
        System.out.println("Done!");
        Scanner s = new Scanner(System.in);
        while(running){
            System.out.print(main_entity.getFile().getAbsolutePath() + "> ");
            String input = s.nextLine();
            String[] parser = input.split(" ");
            if(parser.length > 2) {
                for (int i = 2; i < parser.length; i++) {
                    parser[1] += " " + parser[i];
                }
            }
            switch (parser[0]){
                case "$ls":
                    for(FileEntity f: list){
                        System.out.printf("\t%s\t"+f.getFile_Size()+"\n", f.getFile().getName());
                    }
                    break;
                case "$size":
                    System.out.println(main_entity.getFile_Size());
                    break;
                case "$cd":
                    for(FileEntity f: list){
                        if(parser[1].equals(f.getFile().getName())){
                            main_entity = f;
                            list = main_entity.getList_of_files();
                            System.out.println("Switched to " + main_entity.getFile().getName());
                            break;
                        }
                    }
                    break;
                case "$get_dir":
                    System.out.println(main_entity.getFile().getName());
                    break;
                case "$exit":
                    s.close();
                    running = false;
                    break;
                case "$back":
                    main_entity = main_entity.getParent();
                    list = main_entity.getList_of_files();
                    break;
                default:
                    System.out.println("Invalid Command");
                    break;
            }
        }
    }
}



