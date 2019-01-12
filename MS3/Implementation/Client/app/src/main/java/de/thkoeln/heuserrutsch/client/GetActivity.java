package de.thkoeln.heuserrutsch.client;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;


public class GetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);


        System.out.println("JETZT Start");
        final TextView tvGetUser2 = (TextView) findViewById(R.id.tvGetUser2);
        final TextView tvGetTitle2 = (TextView) findViewById(R.id.tvGetTitle2);

// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://heuserrutsch.herokuapp.com/entry";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println(response);
                        tvGetUser2.setText(response.toString());
                        tvGetUser2.setText(response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                        // TODO: Handle error

                    }
                });


        queue.add(jsonArrayRequest);

// Add the request to the RequestQueue.

        System.out.println("JETZT Ende");


    }
}
