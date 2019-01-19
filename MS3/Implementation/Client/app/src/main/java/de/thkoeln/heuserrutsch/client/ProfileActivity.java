package de.thkoeln.heuserrutsch.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


    }

    ImageView profilepic = (ImageView) findViewById(R.id.profile_pic);
    TextView username = (TextView) findViewById(R.id.Profile_Name);
    TextView useragegender = (TextView) findViewById(R.id.Profile_agegender);
    TextView userrating = (TextView) findViewById(R.id.Profile_agegender);
    TextView usermail = (TextView) findViewById(R.id.Profile_agegender);


}
