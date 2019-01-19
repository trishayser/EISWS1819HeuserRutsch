package de.thkoeln.heuserrutsch.client;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton mainFAB = (FloatingActionButton) findViewById(R.id.mainfab);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button7 = (Button) findViewById(R.id.button7);

        TextView usertext = (TextView) findViewById(R.id.User_mail);
        mAuth = FirebaseAuth.getInstance();


        String usermail= getIntent().getStringExtra("EXTRA_Usermail");
        if(usermail!=null){
            usertext.setText("Hallo "+ usermail);
        }else{
            usertext.setText("Nicht angemeldet");
        }


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
        button3.setOnClickListener( new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {
                                            Intent fabIntent = new Intent(getApplication().getApplicationContext(), EntryActivity.class);
                                            startActivity(fabIntent);
                                        }
                                    }
        );

        button4.setOnClickListener( new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {
                                            Intent fabIntent = new Intent(getApplication().getApplicationContext(), ProfileActivity.class);
                                            startActivity(fabIntent);
                                        }
                                    }
        );
        button5.setOnClickListener( new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {
                                            Intent fabIntent = new Intent(getApplication().getApplicationContext(), LoginActivity.class);
                                            startActivity(fabIntent);
                                        }
                                    }
        );
        button7.setOnClickListener( new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {

                                            mAuth.signOut();
                                            Intent fabIntent = new Intent(getApplication().getApplicationContext(), LoginActivity.class);
                                            startActivity(fabIntent);
                                        }
                                    }
        );

    }
}
