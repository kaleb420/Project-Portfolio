public class Matrix {
    private int rows;
    private int columns;
    private int[][] M;

    /**
     * constructor to set the number of rows, columns, and values for M
     * @param rows rows in the 2d array
     * @param columns columns in the 2d array
     * @param M copies values assigned from the variable into the instance variable
     */
    public Matrix(int rows, int columns, int[][] M){
        this.rows=rows;
        this.columns=columns;
        this.M= new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.M[i][j]=M[i][j];
            }
        }
    }
    int getRows(){
        return rows;
    }
    int getCols(){
        return columns;
    }
    int[][] getMatrix(){
        return M;
    }

    /**
     * sets the value at row i and column j to val
     * @param i row value is being assigned to
     * @param j column value is being assigned to
     * @param val value being assigned
     */
    void set(int i, int j, int val){
        if (rows>i && columns>j){
            M[i][j]=val;
        }
    }

    /**
     * adds the value of the passed matrix to the current matrix
     * @param m matrix provided
     * @return added matrix
     */
    boolean add(Matrix m){
        if ((m.rows==0 && m.columns==0) || (M.length==0 && M[0].length==0))
            return true;
        if (M.length!=m.rows || M[0].length!=m.columns)
            return false;
        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.columns; j++) {
                M[i][j]+= m.getMatrix()[i][j];
            }
        }
        return true;
    }

    /**
     * multiplies M by m, only if the number of columns of one matrix equals the number of rows in the other
     * @param m matrix to multiply by
     * @return true if it can be multiplied, false otherwise
     */
    boolean multiply(Matrix m){
        int[][] arr = new int[rows][m.columns];
        if (columns==m.rows){
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < m.columns; j++) {
                    for (int k = 0; k < columns; k++) {
                        arr[i][j]+=M[i][k]*m.getMatrix()[k][j];
                    }
                }
            }
            columns=m.columns;
            M=arr;
            return true;
        }
        return false;
    }

    /**
     * the rows become the columns and the columns become the rows
     */
    void transpose(){
        int[][] arr = new int[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[j][i]=M[i][j];
            }
        }
        int temp = columns;
        columns = rows;
        rows = temp;
        M=arr;
    }

    /**
     * rotates the matrix 90 degrees clockwise by first transposing it and then inverting the rows
     * i.e. i=0 becomes the last index and the last index becomes i=0
     */
    void rotate(){
        transpose();
        int[][] arr= M;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j]=M[arr.length-1-i][j];
            }
        }
        M=arr;
    }

    /**
     * Override function that stringifies the matrix
     * @return string version of the matrix
     */
    @Override
    public String toString(){
        String s="[";
        for (int i = 0; i < rows; i++) {
            s+="[";
            for (int j = 0; j < columns; j++) {
                if (j==columns-1)
                    s+=M[i][j];
                else
                    s+=M[i][j] + ", ";
            }
            if (i==rows-1)
                s+="]";
            else
                s+="], ";
        }
        s+="]";
        return s;
    }
}
