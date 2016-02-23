package overlandthomas.shopinglistalpha;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import overlandthomas.shopinglistalpha.UnitConversions.*;
/**
 * Created by overtho17 on 2/4/2016.
 */
public class FoodItem {
    private ArrayList<FoodItem> store;
    public TextView item;
    public LinearLayout layout;
    public Button compleate;
    public String food;
    public Unit quantity;
    public FoodItem(String in,Context t,ArrayList<FoodItem> s,Unit q){
        quantity=q;
        store=s;
        s.add(this);
        food=in;
        layout=new LinearLayout(t);
        item=new TextView(t);
        String label = in+q.toString();
        item.append(label);
        layout.addView(item);
        compleate=new Button(t);
        compleate.append("remove item");
        layout.addView(compleate);
        compleate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                remove();
            }
        });

        }
    public void remove(){
        store.remove(this);
        if(layout.getContext() instanceof Rmove){
            ((Rmove) layout.getContext()).remove(this);
        }

    }
    public void add (Unit u){
        quantity = Units.add(this.quantity,u);
        quantity = Units.toGreatestUnit(quantity);
        String label = food +" "+ quantity.toString();
        item.setText(label);
    }
        @Override
    public boolean equals(Object o){
        return o==this;
    }
}
