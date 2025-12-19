public class Chocolate {
    public String name;
    public int ounces;

    /**
     * constructor for creating chocolate
     * @param name name of chocolate
     * @param ounces number of ounces
     */
    public Chocolate(String name, int ounces){
        this.name=name;
        this.ounces=ounces;
    }
    public int getOunces(){
        return ounces;
    }
    public String getKind(){
        return name;
    }
}
