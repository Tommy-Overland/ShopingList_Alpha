package overlandthomas.shopinglistalpha.UnitConversions;

/**
 * Created by overtho17 on 2/5/2016.
 */
public class Unit {
    private double quantity;
    private final String name;
    private final String family;
    public Unit (String n, double q,String family){
        quantity=q;
        name=n;
        this.family=family;
    }
    public double getQuantity(){
        return quantity;
    }
    public String getName(){
        return name;
    }
    public String getFamily(){
        return family;
    }
    //public void add(Unit in);

}
