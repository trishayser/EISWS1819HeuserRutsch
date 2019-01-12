package de.thkoeln.heuserrutsch.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPostMain = (Button) findViewById(R.id.buttonPostMain);
        Button buttonGetMain = (Button) findViewById(R.id.buttonGetMain);

        buttonPostMain.setOnClickListener( new View.OnClickListener() {

                                               @Override
                                               public void onClick(View v) {
                                                   Intent postIntent = new Intent(getApplication().getApplicationContext(), PostActivity.class);
                                                   startActivity(postIntent);
                                               }
                                           }
        );
        buttonGetMain.setOnClickListener( new View.OnClickListener() {

                                               @Override
                                               public void onClick(View v) {
                                                   Intent getIntent = new Intent(getApplication().getApplicationContext(), GetActivity.class);
                                                   startActivity(getIntent);
                                               }
                                           }
        );

    }
}
