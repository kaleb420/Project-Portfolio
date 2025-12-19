import javax.management.OperationsException;
import java.io.*;
import java.util.*;

public class ApplyOperations {
    /**
     * apply the operator to the integers in the given file
     * @param inputFileName file containing starting function
     * @param outputFileName file containing end value
     */
    static void applyOperations(String inputFileName, String outputFileName){
        int first=0;
        String operator="";
        int second=0;
        int i=0;
        List<String> output = new ArrayList<>();
        try (Scanner s = new Scanner(new File(inputFileName))){
            while (s.hasNextLine()) {
                String[] line=s.nextLine().split(" ");
                if (line.length==3) {
                    first=Integer.parseInt(line[0]);
                    operator=line[1];
                    second=Integer.parseInt(line[2]);
                }
                else
                    throw new InputMismatchException();
                if (operator!="+" || operator!="-" || operator!="*" || operator!="/")
                    throw new OperationsException();
                switch (operator){
                    case "+": output.add(String.valueOf(first+second));
                    break;
                    case "-": output.add(String.valueOf(first-second));
                    break;
                    case "*": output.add(String.valueOf(first*second));
                    break;
                    case "/": output.add(String.valueOf(first/second));
                    break;
                }
            }
        }
        catch (InputMismatchException|NumberFormatException ex){
            throw new InputMismatchException();
        }
        catch (IOException ex){
            throw new RuntimeException();
        }
        catch (ArithmeticException ex){
            throw new ArithmeticException();
        }
        catch (UnsupportedOperationException ex){
            throw new UnsupportedOperationException();
        }
        catch (OperationsException ex) {
            throw new UnsupportedOperationException();
        }
        try (PrintWriter newFile = new PrintWriter(new FileWriter(outputFileName))){
            while (!output.isEmpty()){
                newFile.println(output.remove(0));
            }
        }
        catch (IOException ex){
            throw new RuntimeException();
        }
    }
}
