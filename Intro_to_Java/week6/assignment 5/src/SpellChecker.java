import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SpellChecker {
    /**
     * compares words from the file in to the file dict, if the word from in is not in dict, put brackets around the word
     * @param dict dictionary of words separated on a new line
     * @param in single sentence without punctuation
     */
    static void spellCheck(String dict, String in){
        try (BufferedReader dictionary = new BufferedReader(new FileReader(dict))){
            Set<String> validWords = new HashSet<>();
            String line= "";
            while ((line=dictionary.readLine())!=null){
                validWords.add(line.toLowerCase());
            }
            try (BufferedReader sentence = new BufferedReader(new FileReader(in))){
                String[] word = sentence.readLine().split(" ");
                String formatted="";
                for (int i = 0; i < word.length; i++) {
                    if (!validWords.contains(word[i].toLowerCase()) && i==word.length-1)
                        formatted+="[" + word[i] + "]";
                    else if (!validWords.contains(word[i].toLowerCase()))
                        formatted+="[" + word[i] + "] ";
                    else if (validWords.contains(word[i].toLowerCase()) && i==word.length-1)
                        formatted+=word[i];
                    else
                        formatted+=word[i] + " ";
                }
                String newFile="";
                for (int j = 0; j < in.length(); j++) {
                    if (in.charAt(j)=='.')
                        break;
                    else
                        newFile+=in.charAt(j);
                }
                newFile+=".out";
                PrintWriter output = new PrintWriter(new FileWriter(newFile));
                output.println(formatted);
                output.close();
            }
        }
        catch (FileNotFoundException ex){
            throw new RuntimeException();
        }
        catch (IOException ex){
            throw new RuntimeException();
        }
    }
}
