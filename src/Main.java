import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter source file : ");
        String source = scanner.nextLine();
        System.out.print("Enter destination file : ");
        String destination = scanner.nextLine();

        File sourceFile = new File(source);
        File destinationFile = new File(destination);
        try {
            if (!sourceFile.exists() || !destinationFile.exists())
                throw new FileNotFoundException();

//            copyFileUsingJava7Files(sourceFile, destinationFile);
            copyFileUsingStream(sourceFile, destinationFile);
            System.out.println("Copy completed!");
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("Khong tim thay file");
        } catch (IOException e) {
            System.out.println("Copy failed!");
            e.printStackTrace();
        }
    }

    public static void copyFileUsingJava7Files(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[10];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}
