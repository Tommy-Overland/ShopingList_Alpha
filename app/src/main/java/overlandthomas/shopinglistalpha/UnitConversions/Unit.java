package overlandthomas.shopinglistalpha.UnitConversions;

/**
 * Created by overtho17 on 2/5/2016.
 */
public class Unit {
    private double quantity;
    private final String name;
    private final String family;

    /**
     * stores a double value with a unit quantity
     * @param n
     * @param q
     * @param family
     */
    public Unit(String n, double q, String family) {
        quantity = q;
        name = n;
        this.family = family;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    //public void add(Unit in);

    /**
     * overrided to string meathod
     * out puts the quantity and the unit type
     * rounds unit up to nearest clean fraction
     * @return
     */
    public String toString() {
        String s;
        //truncates the value to the whole numbers then assihns to the string
        int whole = (int) quantity;
        s = "" + whole;
        //gets the first two decimal places of the number
        int end = (int) (quantity * 100);
        end = end % 100;
        //rounds the decimal value to a fraction and adds an appropriate string value to represent that fraction
        if (end <= 5) {

        } else if (end <= 12) {
            s += " 1/8";
        } else if (end <= 25) {
            s += " 1/4";
        } else if (end <= 33) {
            s += " 1/3";
        } else if (end <= 50) {
            s += " 1/2";
        } else if (end <= 66) {
            s += " 2/3";
        } else if (end <= 75) {
            s += " 3/4";
        } else if (end <= 87) {
            s += " 7/8";
        } else {
            //rounds the decimal value up to zero reseting the string with a greater value
            whole++;
            s = "" + whole;
        }
        //apendices the name of the unit
        s += " " + name;
        return s;
    }
}
