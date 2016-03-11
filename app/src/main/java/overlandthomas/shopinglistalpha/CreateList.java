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

public class CreateList extends AppCompatActivity implements Rmove{
public ArrayList<String> filePaths;
    public ArrayList<FoodItem> foods;
    public File savedFilePaths;
    public LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("info","create on create");
        setContentView(R.layout.activity_create_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        filePaths = new ArrayList<>();
        foods=new ArrayList<>();
        layout = (LinearLayout) findViewById(R.id.shopingList);
        savedFilePaths = new File(getFilesDir()+"/"+"SavedShopingPath");
        try{
            if(!savedFilePaths.exists()){
                savedFilePaths.createNewFile();
            }
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
    public void addline(String itemPath){

            Scanner line = new Scanner(itemPath);
            line.useDelimiter("&");
            String item;
            String unit;
            String fam;
            double quant;
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
        FoodItem food = new FoodItem(item,this,foods,new Unit(unit,quant,fam));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(food.layout, 0, params);
    }
    public void onStop(){
        super.onStop();
        Log.d("info", "Create List on Stop");
        try{
         if(!savedFilePaths.exists()){
             savedFilePaths.createNewFile();
         }
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
    public void getFile(View view){
        Intent i = new Intent(this,OpenActivity.class);
        Intent source = getIntent();
        i.putExtra(Strings.getFile,source.getStringExtra(Strings.getFile));
        i.putExtra(Strings.getClass, "shoping");
        startActivityForResult(i, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
               //add code to add in file to the list
                Log.d("info", "result recieved");
                Log.d("info", data.getStringExtra(Strings.getFeedback));
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
    public void remove(FoodItem food){
        //layout.removeView(food.layout);
    }
    public void Clear(View view){

    }

}
