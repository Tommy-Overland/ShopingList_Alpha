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
        int whole = (int) quantity;
        s = "" + whole;
        int end = (int) quantity * 100;
        end = end % 100;
        if (end == 0) {

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
            whole++;
            s = "" + whole;
        }

        s += " " + name;
        return s;
    }
}
