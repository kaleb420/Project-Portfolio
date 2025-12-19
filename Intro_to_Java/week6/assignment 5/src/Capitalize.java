import java.io.*;
import java.util.*;

public class Capitalize{
    /**
     * reads a file and capitalizes the start of each sentence into a new .out file of the same name
     * @param in file name given
     */
    static void capitalize(String in){
        try (BufferedReader br = new BufferedReader(new FileReader(in))){
            String line="";
            String text="";
            String newFile="";
            while ((line = br.readLine()) != null) {
                String[] spliced = line.split("\\.");
                for (int i = 0; i < spliced.length; i++) {
                    for (int j = 0; j < spliced[i].length(); j++) {
                        if (i==0 && j==0 && !(spliced[i].charAt(j) >= 65 && spliced[i].charAt(j) <= 90)) // on the first sentence of the line there will be no space before the letter
                            text+= (char) (spliced[i].charAt(j)-32);
                        else if (i!=0 && j == 1 && !(spliced[i].charAt(j) >= 65 && spliced[i].charAt(j) <= 90)) // but after the first sentence there will be a space before the character
                            text += (char) (spliced[i].charAt(j) - 32);
                        else
                            text += spliced[i].charAt(j);
                    }
                    text+=".";
                }
                text+="\n";
            }
            text=text.substring(0,text.length()-1);
            for (int j = 0; j < in.length(); j++) {
                if (in.charAt(j)=='.')
                    break;
                newFile+=in.charAt(j);
            }
            newFile+=".out";
            PrintWriter output = new PrintWriter(new FileWriter(newFile));
            output.println(text);
            output.close();
        }
        catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }
}
