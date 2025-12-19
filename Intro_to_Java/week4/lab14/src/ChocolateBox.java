
public class ChocolateBox {
    private int rows;
    private int columns;
    private Chocolate[][] chocolateBox;

    /**
     * constructor for chocolate box
     * @param rows number of rows
     * @param columns number of columns
     */
    public ChocolateBox(int rows, int columns){
        this.rows=rows;
        this.columns=columns;
        chocolateBox= new Chocolate[rows][columns];
    }
    Chocolate getChocolate(int row, int col){
        return chocolateBox[row][col];
    }

    /**
     * determines how many non-null instances of chocolate are in chocolate box
     * @return number of non-null instances
     */
    int numberOfChocolates(){
        int counter=0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (chocolateBox[i][j]!=null)
                    counter+=1;
            }
        }
        return counter;
    }


    /**
     * add a chocolate to the box array unless there is already a chocolate instance at that spot
     * @param kind type of chocolate
     * @param oz weight
     * @param row row it is added
     * @param col column it is added
     * @return instantiate the instance of chocolate, and return true if it is empty, otherwise false
     */
    boolean addChocolate(String kind, int oz, int row, int col){
        if (chocolateBox[row][col]==null){
            chocolateBox[row][col]=new Chocolate(kind,oz);
            return true;
        }
        return false;
    }

    /**
     * randomizes the elements
     */
    void shuffleChocolate(){

    }

    /**
     * remove the first instance of a specific kind of chocolate
     * @param kind type to be removed
     * @return the positioned was removed if kind is present in the box, otherwise -1
     */
    int removeFirst(String kind){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (chocolateBox[i][j].equals(kind)){
                    chocolateBox[i][j]= null;
                    return i*j;
                }
            }
        }
        return -1;
    }

    /**
     * remove a specific kind of chocolate from the box and shift the box over accordingly
     * @param kind type to be removed
     * @return created chocolateBox
     */
    ChocolateBox allergyBox(String kind){
        ChocolateBox allergy = new ChocolateBox(chocolateBox.length, chocolateBox[0].length);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (chocolateBox[i][j].equals(kind)){
                    chocolateBox[i][j]=null;
                }
            }
        }
        return allergy;
    }
}
