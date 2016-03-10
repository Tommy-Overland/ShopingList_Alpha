package overlandthomas.shopinglistalpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;

public class CreateList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
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
        Log.d("info", "Create List on Start");

    }
    public void onStop(){
        super.onStop();
        Log.d("info", "Create List on Stop");
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
        i.putExtra(Strings.getClass,"shoping");
        startActivityForResult(i,1);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
               //add code to add in file to the list
                Log.d("info","result recieved");
                Log.d("info",data.getStringExtra(Strings.getFeedback));
            }
        }
    }

}
