public class Temperature {
    private double kelvin;

    /**
     * creates a constructor to initiate the kelvin instance variable
     * @ para k temperature in kelvin
     */
    public Temperature(double k){
        kelvin = k;
    }
    public double getCelsius(){
        return kelvin-273.15;
    }
    public void setCelsius(double c){
        kelvin= c + 273.15;
    }
    double getFahrenheit(){
        return (kelvin-273.15)*(9.0/5)+32;
    }
    public void setFahrenheit(double f){
        kelvin=(f-32)*(5/9.0)+273.15;
    }
    double getKelvin(){
        return this.kelvin;
    }
    public void setKelvin(double k){
        this.kelvin = k;
    }
}
