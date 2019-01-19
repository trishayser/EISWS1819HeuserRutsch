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
    JSONObject entryJSON;

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

        Bundle resultIntent = getIntent().getExtras();
        String idValue = "";

        if(resultIntent != null)
        {
            idValue = resultIntent.getString("_id");
        }

        final RequestQueue queue = Volley.newRequestQueue(this);
        String urle ="https://rutscheuser.herokuapp.com/entry/match/" + idValue;
        System.out.println("123 URL IST" + urle);

        JsonObjectRequest jsonArrayRequestEntry = new JsonObjectRequest
                (Request.Method.GET, urle, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject response) {                        // display response
                        System.out.println("Gibt ne Response");
                        //System.out.println("Response" + response.toString());
                        try {
                            System.out.println("123 TRY");
                            //JSONArray json = new JSONArray(response);
                            System.out.println("123 ARRAY ERSTELLT");
                            for (int i= 0; i < response.length(); i++) {

                                entryJSON = response.getJSONObject(""+ i);
                                System.out.println("Durchlauf Nummer" + i + "OBJEKT ERSTELLT" + entryJSON);

                                try {
                                    //System.out.println("123 USER REQUEST ERFOGLREICH " +  entryJSON.getJSONObject("entry").getString("_id") + " " +  entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("haveTransporter") + " " + responseUser.getString("userID"));

                                    entry.add(new Entry(
                                            entryJSON.getJSONObject("entry").getString("_id"),
                                            entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("haveTransporter"),
                                            entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("driveTransporter"),
                                            entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("canMontate"),
                                            entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("canInstall"),
                                            entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("canDischarge"),
                                            entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("canTransport"),
                                            entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("haveTransporter"),
                                            entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("driveTransporter"),
                                            entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("canMontate"),
                                            entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("canInstall"),
                                            entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("canDischarge"),
                                            entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("canTransport"),
                                            entryJSON.getJSONObject("user").getString("userID"),
                                            entryJSON.getJSONObject("user").getString("name"),
                                            entryJSON.getJSONObject("user").getString("surname"),
                                            entryJSON.getInt("score")
                                    ));
                                    System.out.println("EINTRAG NUMMER " + i + " " + entry);
                                    RVAdapter adapter = new RVAdapter(entry);
                                    lrv.setAdapter(adapter);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                /*String urlu ="https://rutscheuser.herokuapp.com/user/" +  entryJSON.getJSONObject("entry").getString("userID");
                                System.out.print("123 URL USER IST " + urlu);
                                JsonObjectRequest jsonArrayRequestUser = new JsonObjectRequest(Request.Method.GET, urlu, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject responseUser) {
                                        try {
                                            System.out.println("123 USER REQUEST ERFOGLREICH " +  entryJSON.getJSONObject("entry").getString("_id") + " " +  entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("haveTransporter") + " " + responseUser.getString("userID"));

                                            entry.add(new Entry(
                                                    entryJSON.getJSONObject("entry").getString("_id"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("haveTransporter"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("driveTransporter"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("canMontate"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("canInstall"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("canDischarge"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("canTransport"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("haveTransporter"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("driveTransporter"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("canMontate"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("canInstall"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("canDischarge"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("canTransport"),
                                                    responseUser.getString("userID"),
                                                    responseUser.getString("name"),
                                                    responseUser.getString("surname"),
                                                    entryJSON.getInt("score")
                                            ));
                                            System.out.println(entry);
                                            RVAdapter adapter = new RVAdapter(entry);
                                            lrv.setAdapter(adapter);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        System.out.println("Error.Response User " +  error.toString());
                                    }
                                });
                                queue.add(jsonArrayRequestUser);
                                */

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("ERRORERRORERROR!!!!!!!!!!");
                        }





                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });


        /*
        final JsonArrayRequest jsonArrayRequestEntry = new JsonArrayRequest(Request.Method.GET, urle, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        // display response
                        System.out.println("Gibt ne Response");
                        //System.out.println("Response" + response.toString());
                        try {
                            System.out.println("123 TRY");
                            //JSONArray json = new JSONArray(response);
                            System.out.println("123 ARRAY ERSTELLT");
                            for (int i= 0; i < response.length(); i++) {
                                entryJSON = response.getJSONObject(i);
                                System.out.println("Durchlauf Nummer" + i + "OBJEKT ERSTELLT");
                                String urlu ="https://rutscheuser.herokuapp.com/user/" +  entryJSON.getString("userID");
                                JsonArrayRequest jsonArrayRequestUser = new JsonArrayRequest(Request.Method.GET, urlu, null, new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray responseUser) {
                                        try {
                                            JSONObject userJSON = responseUser.getJSONObject(0);
                                            System.out.println("123 USER REQUEST ERFOGLREICH");
                                            entry.add(new Entry(
                                                    entryJSON.getString("entry._id"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("haveTransporter"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("driveTransporter"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("canMontate"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("canInstall"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("canDischarge"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("haveObstacles").getBoolean("canTransport"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("haveTransporter"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("driveTransporter"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("canMontate"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("canInstall"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("canDischarge"),
                                                    entryJSON.getJSONObject("entry").getJSONObject("needObstacles").getBoolean("canTransport"),
                                                    userJSON.getString("userID"),
                                                    userJSON.getString("name"),
                                                    userJSON.getString("surname"),
                                                    userJSON.getString("score")
                                            ));
                                            RVAdapter adapter = new RVAdapter(entry);
                                            lrv.setAdapter(adapter);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        System.out.println("Error.Response User " +  error.toString());
                                    }
                                });
                                queue.add(jsonArrayRequestUser);


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
                        System.out.println("Error.Response Entry " +  error.toString());
                    }
                }
        );
        */


        queue.add(jsonArrayRequestEntry);





    }

}
