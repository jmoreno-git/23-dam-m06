/**
 * This Java program asks for a number using the system console and adds it to 
 * the end of a binary file (integers.dat) that contains integers. The program
 * uses a method to show content file before and after adding the new number.
  */
 
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class RandomEx1 {

    static Scanner sc = new Scanner(System.in);
    static RandomAccessFile file = null;

    public static void main(String[] args) {
        int numero;
        try {
            //Open file with read and write mode
            file = new RandomAccessFile("numbers.dat", "rw");
            showFile(); //show original file content
            System.out.print("Enter a number to add at end of file: ");
            numero = sc.nextInt(); //reads int to add at the end of file
            file.seek(file.length()); // Sets the file-pointer offset at end of file
            file.writeInt(numero);       //writes file
            showFile();// show modified file content

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e){
             System.out.println ("Error");
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void showFile() {
        int n;
        try {
            file.seek(0); //Sets the file-pointer offset at the beggining of file
            while (true) {
                n = file.readInt();  //read an int
                System.out.println(n);  
            }
        } catch (EOFException e) {
            System.out.println("End of file");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
