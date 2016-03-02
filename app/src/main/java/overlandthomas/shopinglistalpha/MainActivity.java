package overlandthomas.shopinglistalpha;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import overlandthomas.shopinglistalpha.UnitConversions.Unit;

public class MainActivity extends AppCompatActivity implements AddFood.OnFragmentInteractionListener, MainList.OnFragmentInteractionListener{
    public MainList main;
    public AddFood foodFrag;
   //public LinearLayout mainLayout = (LinearLayout) findViewById(R.id.ListOfFood);
    //public EditText input = (EditText) findViewById(R.id.item);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(findViewById(R.id.layout)!=null){

        }
         main = new MainList();
        getSupportFragmentManager().beginTransaction().add(R.id.layout,main).commit();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /*
    public void newItem(View view){
        String s = input.toString();
        FoodItem food = new FoodItem(s,this,foods);
        mainLayout.addView(food.layout);
        input.setText("");
    }
    */

    /**
     * replaces the currently displayed fragment with the main list fragment
     *
     */
    public void end(){
        Log.d("info","end called");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.layout, main);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();

    }
    public void get(View view){
        Log.d("info", "get called");
        Unit u = new Unit("Cup",0,"volume");
        add(u,"test item add");
        //main.get(view);
    }
    /**
     * adds a food item to the main list then calls end
     * @param u
     * @param n
     */
    public void add(Unit u, String n){
        Log.d("info", "main add called");
        FoodItem food = new FoodItem(n,this,main.foods,u);
        main.mainLayout.addView(food.layout);
        //main.addFood(food);
        //end();
    }
    public void addItem(View view){
        Log.d("info","Add Item Called");
        foodFrag.addItem(view);
    }
    public void terminate(View view){
        Log.d("info", "terminate called");
        foodFrag.terminate(view);
    }
    public void onStart(){

        Log.d("info", "Main on Start");
        super.onStart();
    }
    public void onPause(){

        Log.d("info","Main on Pause");
        super.onPause();

    }
    public void onResume(){

        Log.d("info","Main on Resume");
        super.onResume();

    }
    public void onStop() {
        Log.d("info","Main on Stop");
        super.onStop();



    }
    public void onFragmentInteraction(Uri uri){

    }

    /**
     * swaps fragment to a new add food inorder to allow for a new food item to be inputed
     */
    public void getFood(){
        foodFrag=new AddFood();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.layout, foodFrag);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }
}
