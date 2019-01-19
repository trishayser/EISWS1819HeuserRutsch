package de.thkoeln.heuserrutsch.client;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton mainFAB = (FloatingActionButton) findViewById(R.id.mainfab);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);

        mainFAB.setOnClickListener( new View.OnClickListener() {

                                               @Override
                                               public void onClick(View v) {
                                                   Intent fabIntent = new Intent(getApplication().getApplicationContext(), newEntry.class);
                                                   startActivity(fabIntent);
                                               }
                                           }
        );
        button.setOnClickListener( new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {
                                            Intent fabIntent = new Intent(getApplication().getApplicationContext(), newEntry.class);
                                            startActivity(fabIntent);
                                        }
                                    }
        );
        button2.setOnClickListener( new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {
                                            Intent fabIntent = new Intent(getApplication().getApplicationContext(), ListActivity.class);
                                            fabIntent.putExtra("_id", "5c433f3d0a4a9f00174c9e9b");
                                            startActivity(fabIntent);
                                        }
                                    }
        );
    }
}
