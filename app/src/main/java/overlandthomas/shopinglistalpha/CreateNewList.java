package overlandthomas.shopinglistalpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateNewList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_list);
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
        Log.d("info", "create new list on Start");

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
    public void create(View view){
        EditText field = (EditText) findViewById(R.id.fileName);
        String fil = field.getText().toString();
        if(fil.equals(null)){
            return;
        }
        fil=removeWhiteSpace(fil);
        File item = new File(getFilesDir()+"/"+fil);
        if(item.exists()){
            return;
        }else{
            try{
                item.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        Intent intent = getIntent();
        String path = intent.getStringExtra("File");
        File data = new File(path);//TODO replace with getting intent for the database
        Scanner read;
        ArrayList<String> fill = new ArrayList<>();
        try{
            read=new Scanner(data);
        }catch(FileNotFoundException e){
            e.printStackTrace();
            read=new Scanner("");
        }
        while(read.hasNextLine()){
            fill.add(read.nextLine());
        }
        PrintStream out;
        try{
            out=new PrintStream(data);
        }catch(FileNotFoundException e){
            e.printStackTrace();
            out=System.out;
        }
        for(int i=0; i<fill.size();i++){
            out.println(fill.get(i));
        }
        out.println(item.toString());
       Intent output = new Intent(this,MainActivity.class);
        output.putExtra("File",item.toString());
        startActivity(output);

    }
    public static String removeWhiteSpace(String in){
        String out ="";
        Scanner read = new Scanner(in);
        if(read.hasNext()) {
            out += read.next();
        }
        while(read.hasNext()){
            out+= "-";
            out+=read.next();
        }
        return out;
    }
}
