package overlandthomas.shopinglistalpha;

import android.content.Context;
import android.util.Log;
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
    //this is a object to store and display an item

    /**
     * Creates a food item to store and display a item added to the list
     * @param in a string the name of the item
     * @param t the context that is used to create each item
     * @param s the array list that is used to store these items for refrence
     * @param q the quantity of the food item needed
     */
    public FoodItem(String in,Context t,ArrayList<FoodItem> s,Unit q){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
        quantity=q;
        store=s;
        Log.d("info", "food item created");
        Log.d("info",in);
        s.add(this);
        food=in;
        layout=new LinearLayout(t);//creates a layout to be used to display the item
        layout.setOrientation(LinearLayout.HORIZONTAL);
        item=new TextView(t); //a text view to display the name and quantity
        String label = in+q.toString();
        item.append(label);
        layout.addView(item,0,params);

        compleate=new Button(t);  // a button to remove the item when it is obtained
        compleate.setText("remove item");
        layout.addView(compleate,1,params);
        compleate.setOnClickListener(new View.OnClickListener() {//code to add interactivity to button calls remove meathod
            public void onClick(View v) {
                Log.d("info","button clicked");
                remove();
            }
        });

        }
    public FoodItem(String in,Context t,Unit q){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        quantity=q;
        store=null;
        Log.d("info", "food item created");
        Log.d("info",in);

        food=in;
        layout=new LinearLayout(t);//creates a layout to be used to display the item
        layout.setOrientation(LinearLayout.HORIZONTAL);
        item=new TextView(t); //a text view to display the name and quantity
        String label = in+q.toString();
        item.append(label);
        layout.addView(item,0,params);

        compleate=new Button(t);  // a button to remove the item when it is obtained
        compleate.setText("remove item");
        layout.addView(compleate,1,params);
        compleate.setOnClickListener(new View.OnClickListener() {//code to add interactivity to button calls remove meathod
            public void onClick(View v) {
                Log.d("info","button clicked");
                remove();
            }
        });

    }
    public void remove(){// removes the item from the array list and calls the contexts remove function if it is a Rmove
        Log.d("info","food item remove called");
        if(this.store!=null) {
            store.remove(this);
        }
        if(layout.getContext() instanceof Rmove){
            Log.d("info","food item rmove true");
            ((Rmove) layout.getContext()).remove(this);
        }

    }
    public void add (Unit u){//increases the value of quanity and updates the label
        quantity = Units.add(this.quantity,u);
        quantity = Units.toGreatestUnit(quantity);
        String label = food +" "+ quantity.toString();
        item.setText(label);
    }
    //ovrrided equals meathod to check against itself
    //used with the remove meathod because I don't want it removeing a food item with the same string instead of this one
        @Override
    public boolean equals(Object o){
        return o==this;
    }

    @Override
    public String toString(){
        String rep = "";
        rep+=this.food;
        rep += "&"+this.quantity.getName();
        rep +="&"+this.quantity.getFamily();
        rep+="&"+this.quantity.getQuantity();
        return rep;
    }
}
