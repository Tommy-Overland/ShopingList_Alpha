package overlandthomas.shopinglistalpha.UnitConversions;

/**
 * Created by overtho17 on 2/5/2016.
 */
public class Units {
    private static final String[] units={"Cup","MiliLiter","tableSpoon","TeaSpoon","Gram","Pound","ounce","Quart"};
    private static final String[] familys={"volume","weight"};
    private static final String[] volumeUnits={"Cup","Quart","TeaSpoon","MiliLiter","tableSpoon","ounce"};
    private static final double[] volumeCon ={1,4,1/48,1/237,1/16,1/8};
    public static boolean isValidUnit(String in){
        for(int i=0; i<units.length;i++){
            if(in.equalsIgnoreCase(units[i])){
                return true;
            }
        }
        return false;
    }
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
    public static String getFamily(String unit){
        return "volume";
    }
    public static Unit convert(String target, Unit con){
        String fam = getFamily(target);
        if(!fam.equalsIgnoreCase(con.getFamily())){
            return null;
        }
        con=convertToBase(con);
        double conv;
        if(fam.equalsIgnoreCase("volume")){
            conv = volumeCon[getIndex(volumeUnits,target)];
        }else{
            conv=1;
        }
            return new Unit(target,con.getQuantity()*conv,con.getFamily());

    }
    public static Unit convertToBase(Unit con){
        if(con.getFamily().equalsIgnoreCase("volume")){
            double conv = volumeCon[getIndex(volumeUnits,con.getName())];

            return new Unit("Cup",con.getQuantity()*conv,"volume");
        }
        return null;
    }
    public static int getIndex (String[] in, String read){
        for(int i=0; i<in.length;i++){
            if(in[i].equalsIgnoreCase(read)){
                return i;
            }
        }
        return -1;
    }
}