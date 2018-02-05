package com.example.chris.assignment1_subbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubscriptionAdd extends AppCompatActivity {
    private EditText name;
    private EditText charge;
    private EditText date;
    private EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String EXTRA_TEXT = "com.example.chris.assignment1_subbook";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscription_add);

        name = (EditText) findViewById(R.id.name);
        charge = (EditText) findViewById(R.id.charge);
        date = (EditText) findViewById(R.id.date);
        comment = (EditText) findViewById(R.id.comment);
        Button addButton = (Button) findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                String sName = name.getText().toString();
                float sCharge = Float.parseFloat(charge.getText().toString());
                String sDate = date.getText().toString();
                String sComment = comment.getText().toString();
                Subscription next = null;
                try {
                    next = new Subscription(sName, sDate, sCharge, sComment);
                } catch (InvalidSubscriptionException e) {
                    e.printStackTrace();
                }
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                if (next != null){
                    back.putExtra(EXTRA_TEXT, next);
                }
                startActivity(back);
            }
        });
    }
}
