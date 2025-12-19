import java.util.Arrays;

public class MiniStringBuilder {
    private char[] character;
    private int count;
    private int resizeFactor;
    private int maxLength;

    /**
     * constructor that initializes array length, max length, count, and resize factor
     */
    public MiniStringBuilder(){
        maxLength=20;
        count=0;
        resizeFactor=2;
        character = new char[20];
    }

    /**
     * constructor that initializes the array to a given string
     * @param s string given
     */
    public MiniStringBuilder(String s){
        this();
        for (int i = 0; i < s.length(); i++) {
            character[i]=s.charAt(i);
        }
        count=s.length();
    }

    /**
     * determines if two MiniStringBuilder objects represent the same string
     * @param o object provided, may or may not be from MiniStringBuilder
     * @return true if they are the same, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof MiniStringBuilder){
            MiniStringBuilder compared= (MiniStringBuilder) o;
            for (int i = 0; i < compared.character.length; i++) {
                if (compared.character[i]!=character[i])
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * finds the hash code of the object
     * @return hash code of the MiniStringBuilder object
     */
    @Override
    public int hashCode(){
        return character.hashCode();
    }

    /**
     * stringifies the logical character (not including brackets or commas) within the character array
     * @return stringified character array
     */
    @Override
    public String toString(){
        String s="";
        for (int i = 0; i < count; i++) {
            if (character[i]!=',' && character[i]!='{' && character[i]!='}')
                s+=character[i];
        }
        return s;
    }

    /**
     * resizes the instance variable to allocate more space for characters
     */
    void resize(){
        maxLength=maxLength*resizeFactor;
        char[] arr= new char[maxLength];
        for (int i = 0; i < character.length; i++) {
            arr[i]=character[i];
        }
        character=arr;
    }
    /**
     * appends the given string onto the current character array
     * @param s string given
     */
    void append(String s){
        if (s.length()+character.length>=maxLength){
            resize();
        }
        int currentLength=count;
        for (int i = 0; i < s.length(); i++) {
            character[i+currentLength]=s.charAt(i);
        }
        count+=s.length();
    }

    /**
     * resets the values of the character array and puts the default size back to 20
     */
    void clear(){
        maxLength=20;
        char[] arr= new char[20];
        character=arr;
        count=0;
    }
}
