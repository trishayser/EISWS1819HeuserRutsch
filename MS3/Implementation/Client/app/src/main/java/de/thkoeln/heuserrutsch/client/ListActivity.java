package de.thkoeln.heuserrutsch.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        final RecyclerView lrv = (RecyclerView)findViewById(R.id.lrv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        lrv.setLayoutManager(llm);

        final List<Entry> entry;
        entry = new ArrayList<>();


// This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
        /*void initializeData(){
            entry = new ArrayList<>();
            entry.add(new Entry("1", "TEST"));
            entry.add(new Entry("1", "TEST"));
            entry.add(new Entry("1", "TEST"));
        }*/

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://rutscheuser.herokuapp.com/entry";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        // display response
                        System.out.println("Response" + response.toString());
                        try {
                            System.out.println("TRY");
                            //JSONArray json = new JSONArray(response);
                            System.out.println("ARRAY ERSTELLT");
                            for (int i= 0; i < response.length(); i++) {
                                JSONObject entryJSON = response.getJSONObject(i);
                                System.out.println("Durchlauf Nummer" + i + "OBJEKT ERSTELLT");
                                    entry.add(new Entry(
                                            entryJSON.getString("userID"),
                                            entryJSON.getString("title")
                                            //entryJSON.getBoolean("haveObstacles.haveTransporter"),
                                            //entryJSON.getBoolean("haveObstacles.driveTransporter"),
                                            //entryJSON.getBoolean("haveObstacles.canMontate"),
                                            //entryJSON.getBoolean("haveObstacles.canInstall"),
                                            //entryJSON.getBoolean("haveObstacles.canDischarge"),
                                            //entryJSON.getBoolean("haveObstacles.canTransport"),
                                            //entryJSON.getBoolean("needObstacles.haveTransporter"),
                                            //entryJSON.getBoolean("needObstacles.driveTransporter"),
                                            //entryJSON.getBoolean("needObstacles.canMontate"),
                                            //entryJSON.getBoolean("needObstacles.canInstall"),
                                            //entryJSON.getBoolean("needObstacles.canDischarge"),
                                            //entryJSON.getBoolean("needObstacles.canTransport")
                                ));
                                    RVAdapter adapter = new RVAdapter(entry);
                                    lrv.setAdapter(adapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("ERRORERRORERROR!!!!!!!!!!");
                        }



                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error.Response" +  error.toString());
                    }
                }
        );



        queue.add(jsonArrayRequest);



    }

}
