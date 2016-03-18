package overlandthomas.shopinglistalpha.UnitConversions;

/**
 * Created by overtho17 on 2/5/2016.
 */
public class Units {
    //array storing all valid units
    private static final String[] units={"Cup","MiliLiter","tableSpoon","TeaSpoon","Gram","Pound","ounce","Quart"};
    //array storing the classes of units not used yet
    private static final String[] familys={"volume","weight"};
    //an array storing the different units that fall under the volume measurement type
    private static final String[] volumeUnits={"Cup","Quart","TeaSpoon","MiliLiter","tableSpoon","ounce"};
    // the conversion factors for each volume unit based on the cup unit
    private static final double[] volumeCon ={1,4,1/48,1/237,1/16,1/8};
    // the index of the conversion factor and units string are the same to allow interchangable usage of index values
    //base is stored at index 0
    public static boolean isValidUnit(String in){
        for(int i=0; i<units.length;i++){
            if(in.equalsIgnoreCase(units[i])){
                return true;
            }
        }
        return false;
    }

    /**
     * unused code that was not used due to lack of time
     * @param unit
     * @param Family
     * @return
     */
    public static boolean isFamily(String unit, String Family){
        String[] fam;
        if(Family.equalsIgnoreCase("Volume")){
            fam=volumeUnits;
        }
        else{
            fam=units;
        }
        for(int i=0; i<fam.length;i++){
            if(fam[i].equalsIgnoreCase(unit)){
                return true;
            }
        }
        return false;
    }

    /**
     * uncompleated code that was implemented as a check for the addition of different unit types
     * @param unit
     * @return
     */
    public static String getFamily(String unit){
        return "volume";
    }

    /**
     * converts unit to the target unit
     * @param target
     * @param con
     * @return
     */
    public static Unit convert(String target, Unit con){
        //checks if unit is of the correct type of unit not used due to lack of time
        String fam = getFamily(target);
        if(!fam.equalsIgnoreCase(con.getFamily())){
            return null;
        }
        //converts the unit to its base unit for consistant conversions
        con=convertToBase(con);
        double conv;
        //gets the conversion factor for the target unit
        if(fam.equalsIgnoreCase("volume")){
            conv = volumeCon[getIndex(volumeUnits,target)];
        }else{
            conv=1;
        }
        //converts the unit to the target unit from its base state by dividing by conversion factor
            return new Unit(target,con.getQuantity()/conv,con.getFamily());

    }

    /**
     * converts unit to the cup unit type
     * @param con
     * @return
     */
    public static Unit convertToBase(Unit con){
        //unused check to compare against unit types
        if(con.getFamily().equalsIgnoreCase("volume")){
            //gets the conversion factor for the unit
            double conv = volumeCon[getIndex(volumeUnits,con.getName())];
            //returns the converted unit by multiplying by conversion factor
            return new Unit("Cup",con.getQuantity()*conv,"volume");
        }
        return null;
    }

    /**
     * searches for the index of the corrosponding unit in the array storing the units
     * @param in
     * @param read
     * @return
     */
    public static int getIndex (String[] in, String read){
        for(int i=0; i<in.length;i++){
            if(in[i].equalsIgnoreCase(read)){
                return i;
            }
        }
        return -1;
    }

    /**
     * converts both units to cup adds values
     * @param u1
     * @param u2
     * @return
     */
    public static Unit add(Unit u1, Unit u2){
        Unit ub1 = convertToBase(u1);
        Unit ub2 = convertToBase(u2);
        Unit u3 = new Unit(ub1.getName(),ub1.getQuantity()+ub2.getQuantity(),ub1.getFamily());
        return u3;
    }

    /**
     * converts unit to the greatest unit it can be without a fraction
     * finds the largest unit it can without the value being less than 1
     * if value is less than 1 for all it converts to cups
     * @param u
     * @return
     */
    public static Unit toGreatestUnit(Unit u){
        //converts to base unit to provide consistant starting point
            u=convertToBase(u);
        //stores the current best quantity
        double val = u.getQuantity();
        //stores the current best unit
        String unit = u.getName();
        //checks the value of each unit in sequence looking for the best fit
        for(int i=0; i<volumeUnits.length;i++){
            double v = u.getQuantity()/volumeCon[i];//temporary value for quantity
            if(v>1&&v<val){//checks that value is greater than one, but less than val for best fit
                //sets the new best value and unit
                val=v;
                unit = volumeUnits[i];
            }
        }
        //converts unit to best case
        return convert(unit,u);
    }
}
