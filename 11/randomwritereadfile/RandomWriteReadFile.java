

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


/**
 *
 * @author provenSoft
 */



public class RandomWriteReadFile {
  
    private static RandomAccessFile raf;
    
    public static void main(String[] args) {
      
        String fileName;
        File fileObject; 
        
        fileName = "randomaccessfile.txt";
        fileObject = new File(fileName);
        
       try {
            if (!fileObject.exists()) {
                initialWrite(fileObject);
            }
            raf = new RandomAccessFile(fileObject, "rw");
            readFile(); // reads and print file data
            incrementReadCounter();
            readFile(); // reads and print file data
        }  catch (FileNotFoundException fnfe) {
             System.out.println("FNFE: " + fnfe.getMessage());
             fnfe.printStackTrace();
             
        } catch (IOException ioe) {
            System.out.println("IOE: " + ioe.getMessage());
            ioe.printStackTrace();
        
        }
  }
    /**
    * Reads fileName that contains a counter(int) and a double and prints its 
    * content. 
    * @throws IOException 
    */
  public static void readFile() throws IOException {
  
    raf.seek(0);
    //Reading...
    int counter = raf.readInt();
    double d = raf.readDouble();
    String value = raf.readUTF();
    
    //Printing...
    System.out.println("Reading file:");
    System.out.println(counter);
    System.out.println(d);
    System.out.println(value);
   
  }
 /**
  * Increments counter by 1 in a file with 3 values a counter(int), a
  * double and a String. Also updates String value with counter info.
  * It maintains file pointer position.
  * @throws IOException 
  */
  public static void incrementReadCounter() throws IOException {
    System.out.println("Increment counter");  
    long currentPosition = raf.getFilePointer();
    raf.seek(0);
    int counter = raf.readInt();
    counter++;
    raf.seek(0);
    raf.writeInt(counter);
    
    
    raf.skipBytes(8); // skip bytes for double
    raf.writeUTF("Counter value: " + counter);
    
    raf.seek(currentPosition);
  }
  /**
   * Writes a file using random access file with 3 values, an int, a double and a String.
   * Afterwards, close random acces file.
   * @param file
   * @throws IOException 
   */
  public static void initialWrite(File file) throws IOException {
        
    raf = new RandomAccessFile(file, "rw");
             
    raf.seek(0);
    raf.writeInt(0);
    raf.writeDouble(45);
    raf.writeUTF("Counter value: " + 0);
    raf.close();
    
    
    
  }

   
}

