package overlandthomas.shopinglistalpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import java.io.*;
import java.util.Scanner;
public class HomeScreen extends AppCompatActivity {
public File database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void onStart(){
        super.onStart();
        Log.d("info", "home screen on Start");
        database=new File(getFilesDir()+"/"+"database.txt");
        if(!database.exists()){
            try{
                database.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public void onStop(){
        super.onStop();
    }
    public void onPause(){
        super.onPause();
    }
    public void onResume(){
        super.onResume();

    }
    public void CreateList(View view){
        Intent intent = new Intent(this,CreateNewList.class);
        intent.putExtra("File",database.toString());
        startActivity(intent);
    }


}
