package com.hdavidzhu.savethechildren;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RecordTutorSessionActivity extends Activity {

    Context context;

    EditText nameEditText;
    EditText startTimeEditText;
    EditText startDateEditText;
    EditText endTimeEditText;
    EditText endDateEditText;
    EditText notesEditText;
    Button submitButton;

    View.OnClickListener submitButtonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_tutor_session);

        context = getApplicationContext();

        nameEditText = (EditText) findViewById(R.id.et_name);
        startTimeEditText = (EditText) findViewById(R.id.et_start_time);
        startDateEditText = (EditText) findViewById(R.id.et_start_date);
        endTimeEditText = (EditText) findViewById(R.id.et_end_time);
        endDateEditText = (EditText) findViewById(R.id.et_end_date);
        notesEditText = (EditText) findViewById(R.id.et_additional_notes);
        submitButton = (Button) findViewById(R.id.bt_submit);

        submitButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Form form = new Form(
                        nameEditText.getText().toString(),
                        startTimeEditText.getText().toString(),
                        startDateEditText.getText().toString(),
                        endTimeEditText.getText().toString(),
                        endDateEditText.getText().toString(),
                        notesEditText.getText().toString()
                );

                JSONObject jsonForm = form.javaToJSONObjectConverter();
                Log.d("something","something");
                postNewForm(context, jsonForm);
                Log.d("something","something2");

            }
        };

        submitButton.setOnClickListener(submitButtonListener);

    }

    public void postNewForm(Context context, JSONObject jsonForm){
        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.56.101:3000/api/tests";

        final JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonForm,
                new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("onResponse", "Success");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("David still stinks");
                Log.d("This is a tag", "David still stinks");
            }
        });

        Log.d("Request in add post", "printing request");
        System.out.println(jsonRequest);
        queue.add(jsonRequest);
        Log.d("Printing the queue", "queue");
        System.out.println(jsonRequest);
    }
}