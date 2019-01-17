package de.thkoeln.heuserrutsch.client;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class newEntry extends AppCompatActivity {

    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);


        //Buttons und Textfelder erstellen
        final EditText editTitle = (EditText) findViewById(R.id.neTitle);
        final EditText editStart = (EditText) findViewById(R.id.neStart);
        final EditText editDestination = (EditText) findViewById(R.id.neDestination);
        final EditText editPeriodStart = (EditText) findViewById(R.id.nePeriodStart);
        final EditText editPeriodEnd = (EditText) findViewById(R.id.nePeriodEnd);
        final CheckBox chkHhaveTransporter = (CheckBox) findViewById(R.id.neChkHhaveTransporter);
        final CheckBox chkHdriveTransporter = (CheckBox) findViewById(R.id.neChkHdriveTransporter);
        final CheckBox chkHCanTransport = (CheckBox) findViewById(R.id.neChkHCanTransport);
        final CheckBox chkHCanDischarge = (CheckBox) findViewById(R.id.neChkHCanDischarge);
        final CheckBox chkHCanMontate = (CheckBox) findViewById(R.id.neChkHCanMontate);
        final CheckBox chkHCanInstall = (CheckBox) findViewById(R.id.neChkHCanInstall);

        final CheckBox chkNhaveTransporter = (CheckBox) findViewById(R.id.neChkNhaveTransporter);
        final CheckBox chkNdriveTransporter = (CheckBox) findViewById(R.id.neChkNdriveTransporter);
        final CheckBox chkNCanTransport = (CheckBox) findViewById(R.id.neChkNCanTransport);
        final CheckBox chkNCanDischarge = (CheckBox) findViewById(R.id.neChkNCanDischarge);
        final CheckBox chkNCanMontate = (CheckBox) findViewById(R.id.neChkNCanMontate);
        final CheckBox chkNCanInstall = (CheckBox) findViewById(R.id.neChkNCanInstall);
        Button button = (Button) findViewById(R.id.neButton);




        editPeriodStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(newEntry.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editPeriodStart.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject json = new JSONObject()
                            .put("id", "123")
                            .put("title", editTitle.getText())
                            .put("userID", "1")
                            .put("date", "01.01.2019");
                    JSONObject jsonStart = new JSONObject()
                            .put("text", editStart.getText());
                    JSONObject jsonDestination = new JSONObject()
                            .put("text", editDestination.getText());
                    JSONObject jsonRoute = new JSONObject()
                            .put("start", jsonStart)
                            .put("destination", jsonDestination);
                    json.put("route", jsonRoute);
                    JSONObject jsonPeriod = new JSONObject()
                            .put("start", editPeriodStart)
                            .put("end", editPeriodEnd);
                    json.put("period", jsonPeriod);
                    JSONObject jsonNeed = new JSONObject()
                            .put("haveTransporter", chkNhaveTransporter.isChecked())
                            .put("driveTransporter", chkNdriveTransporter.isChecked())
                            .put("canDischarge", chkNCanDischarge.isChecked())
                            .put("canMontate", chkNCanMontate.isChecked())
                            .put("canInstall", chkNCanInstall.isChecked())
                            .put("canTransport", chkNCanTransport.isChecked());
                    json.put("needObstacles", jsonNeed);
                    JSONObject jsonHave = new JSONObject()
                            .put("haveTransporter", chkHhaveTransporter.isChecked())
                            .put("driveTransporter", chkHdriveTransporter.isChecked())
                            .put("canDischarge", chkHCanDischarge.isChecked())
                            .put("canMontate", chkHCanMontate.isChecked())
                            .put("canInstall", chkHCanInstall.isChecked())
                            .put("canTransport", chkHCanTransport.isChecked());
                    json.put("haveObstacles", jsonNeed);
                    JSONObject jsonCharge = new JSONObject()
                            .put("package", "TEST")
                            .put("weight", "TEST")
                            .put("size", "TEST")
                            .put("height", "TEST")
                            .put("length", "TEST")
                            .put("width", "TEST");
                    json.put("charge", jsonCharge);
                    json.put("matchedPartner", "TEST");
                    json.put("active", true);
                    json.put("suceed", false);
                    json.put("transporter", "TEST");

                    System.out.println(json);
                    //Request POST auf Eintrag

                    String url ="https://rutscheuser.herokuapp.com/entry";

                    RequestQueue requestQueue = Volley.newRequestQueue(newEntry.this);

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                           System.out.println("Response1234:" + response);
                            Intent listIntent = new Intent(getApplication().getApplicationContext(), ListActivity.class);
                            startActivity(listIntent);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("Error1234: " + volleyError);


                        }
                    });

                    requestQueue.add(jsonObjectRequest);

                    // Add the request to the RequestQueue.

                    System.out.println("JETZT Ende");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });









    }
}
