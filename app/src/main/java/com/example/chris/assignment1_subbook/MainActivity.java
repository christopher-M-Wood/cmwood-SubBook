package com.example.chris.assignment1_subbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {

    public static final String FILENAME = "savefile.sav";
    private ListView oldSubscriptionList;
    private ArrayList<Subscription> subscriptionList;
    private ArrayAdapter<Subscription> adapter;
    String EXTRA_TEXT = "com.example.chris.assignment1_subbook";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Subscription newSub = null;
        if(getIntent().hasExtra(EXTRA_TEXT)){
            newSub = (Subscription)getIntent().getSerializableExtra(EXTRA_TEXT);
        }

        oldSubscriptionList = (ListView) findViewById(R.id.oldSubscriptionList);
        Button createButton = (Button) findViewById(R.id.create);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(getApplicationContext(), SubscriptionAdd.class);
                startActivity(add);
            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Subscription>(this, R.layout.list_item, subscriptionList);
        oldSubscriptionList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
			/*Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-js
            24-02-2018*/
            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();
            subscriptionList = gson.fromJson(in,listType);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            subscriptionList = new ArrayList<Subscription>();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(subscriptionList, out);
            out.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i("In Destroy method", "The app is closing");
    }
}
