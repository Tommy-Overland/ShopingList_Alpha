package overlandthomas.shopinglistalpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import overlandthomas.shopinglistalpha.UnitConversions.Unit;

/**
 * activity to display and compile the shoping list
 */
public class CreateList extends AppCompatActivity implements Rmove{
public ArrayList<String> filePaths;//an array list that dynamicly stores all the recipie file paths
    public ArrayList<FoodItem> foods; // the arraylist that stores the foods in the list used to store refrences
    public File savedFilePaths; // the file containing the file paths to recipise
    public LinearLayout layout; // the main layout used to dynamicly change the UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("info","create on create");
        setContentView(R.layout.activity_create_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //initializes
        filePaths = new ArrayList<>();
        foods=new ArrayList<>();
        layout = (LinearLayout) findViewById(R.id.shopingList);
        savedFilePaths = new File(getFilesDir()+"/"+"SavedShopingPath");
        //ensures that a file exists
        try{
            if(!savedFilePaths.exists()){
                savedFilePaths.createNewFile();
            }
            //loads the filePaths with the files from the storage
            Scanner read = new Scanner(savedFilePaths);
            while(read.hasNextLine()){
                filePaths.add(read.nextLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //loads the UI with the saved files foods
        for(int i=0; i<filePaths.size();i++){
            File food = new File(filePaths.get(i));
            try{
                if(food.exists()){
                    Scanner scan = new Scanner(food);
                    while (scan.hasNextLine()){
                        addline(scan.nextLine());
                    }
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
    public void onStart(){
        super.onStart();

        Log.d("info", "Create List on Start");

    }

    /**
     * code to read in fooditems from the files and add them to the UI
     * @param itemPath
     */
    public void addline(String itemPath){
            //declare necisary variables and initialize the scanner
            Scanner line = new Scanner(itemPath);
            line.useDelimiter("&");
            String item;
            String unit;
            String fam;
            double quant;
        //checks each section of the line for the proper components
        //if component is not found line fails and nothing happens
            if(line.hasNext()){
                item = line.next();
            }else{
                return;
            }
            if(line.hasNext()){
                unit = line.next();
            }else{
                return;
            }
            if(line.hasNext()){
                fam = line.next();
            }else{
                return;
            }
            if(line.hasNextDouble()){
                quant = line.nextDouble();
            }else{
                return;
            }
        //creates the food item object then adds it to ui
        FoodItem food = new FoodItem(item,this,foods,new Unit(unit,quant,fam));
        addFood(food);
    }

    /**
     * code to add food item to the UI
     * @param food
     */
    public void addFood(FoodItem food){
        //establish UI layout paramiters then add view at index 0
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(food.layout, 0, params);
        //code to consolidate redundant item entries by summing their quantities
        for(int i=0; i<foods.size()-1;i++){
            //if it finds a matching item it consolidates the two items
            if(foods.get(i).food.equalsIgnoreCase(food.food)){
                foods.get(i).add(food.quantity);
                food.remove();
                return;
            }
        }
    }
    public void onStop(){
        super.onStop();
        Log.d("info", "Create List on Stop");
        //checks again for the existance of the file
        try{
         if(!savedFilePaths.exists()){
             savedFilePaths.createNewFile();
         }
            //saves the file paths stored in the arraylist in the file
            PrintStream out = new PrintStream(savedFilePaths);
            for(int i=0; i<filePaths.size();i++){
                out.println(filePaths.get(i));
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void onPause(){
        super.onPause();
        Log.d("info", "Create List on Pause");
    }
    public void onResume(){
        super.onResume();
        Log.d("info", "Create List on Resume");
    }

    /**
     * calls the open activity to return a file path
     * file path is then added to the list of file paths
     * called on a button press in the UI
     * @param view
     */
    public void getFile(View view){
        Intent i = new Intent(this,OpenActivity.class);
        Intent source = getIntent();
        i.putExtra(Strings.getFile,source.getStringExtra(Strings.getFile));
        i.putExtra(Strings.getClass, "shoping");
        startActivityForResult(i, 1);
    }

    /**
     * code to recieve the value from the called open Activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
               //add code to add in file to the list
                Log.d("info", "result recieved");
                Log.d("info", data.getStringExtra(Strings.getFeedback));
                //adds the filepath to array list then scans it adding its items to UI
                filePaths.add(data.getStringExtra(Strings.getFeedback));
                File added = new File(data.getStringExtra(Strings.getFeedback));
                try{
                    Scanner read = new Scanner(added);
                    while (read.hasNextLine()){
                        addline(read.nextLine());
                    }
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * used to remove food items from ui
     * @param food
     */
    public void remove(FoodItem food){
        layout.removeView(food.layout);
    }

    /**
     * resets the ui clearing all saved file paths and removing all added UI elements
     * @param view
     */
    public void Clear(View view){
        for (int i=0; i<foods.size();i++){
            foods.get(i).remove();
        }
        filePaths.clear();
    }

}
