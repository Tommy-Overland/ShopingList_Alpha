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
import java.util.Scanner;

public class OpenActivity extends AppCompatActivity {
public File Stored;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
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
        Log.d("info", "Open on Start");
    }
    public void Open(DisplayButton b){
        File f = new File(b.getFilePath());
            if(!f.exists()){
                Log.d("info","file does not exist");
                return;
            }
            Intent out = new Intent(this,MainActivity.class);
            out.putExtra(HomeScreen.getFile,b.getFilePath());
            startActivity(out);


    }
    public void onStop(){
        super.onStop();
    }
    public void onPause(){
        super.onPause();
        LinearLayout layout = (LinearLayout) findViewById(R.id.OpenList);
        layout.removeAllViews();
    }
    public void onResume(){
        super.onResume();
        Intent intent = getIntent();
        Stored = new File(intent.getStringExtra(HomeScreen.getFile));
        try{
            if(!Stored.exists()){
                Stored.createNewFile();
            }
            Scanner scan = new Scanner(Stored);
            LinearLayout layout = (LinearLayout) findViewById(R.id.OpenList);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            while(scan.hasNextLine()){
                DisplayButton b = new DisplayButton(this,scan.nextLine());
                b.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        if(view instanceof DisplayButton){
                            Open((DisplayButton)view);
                        }
                    }
                });
                layout.addView(b,0,params);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
