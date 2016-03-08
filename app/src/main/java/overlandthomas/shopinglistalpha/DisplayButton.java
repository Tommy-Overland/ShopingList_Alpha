package overlandthomas.shopinglistalpha;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.Scanner;

/**
 * Created by overtho17 on 3/8/2016.
 */
public class DisplayButton extends Button{
    public String FilePath;
    public String FileName;
    public DisplayButton (Context context,String FilePath){
        super(context);
        Scanner sc = new Scanner(FilePath);
        this.FilePath=sc.next();
        if(sc.hasNext()){
            this.FileName=sc.next();
        }else{
            FileName="failed to get name";
        }
        super.setHint(FileName);
    }
    public String getFilePath(){
        return FilePath;
    }

}
