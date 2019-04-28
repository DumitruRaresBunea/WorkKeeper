package com.example.workkeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateNewWokingPlaceActivity extends AppCompatActivity {

    private Job job;
    private Spinner spComany;
    private Spinner spHours;
    private TextView payment;
    private Button add;
    private DatabaseReference myRef;
    private Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_woking_place);
        payment=findViewById(R.id.payment);

       add=findViewById(R.id.addJob);

       back=findViewById(R.id.addJob);





        FirebaseDatabase database = FirebaseDatabase.getInstance();
         myRef = database.getReference("jobs");

        final String[] companyNames = getResources().getStringArray(R.array.jobs_array);
        spComany = findViewById(R.id.companySpinner);
        if (spComany != null) {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, companyNames);
            spComany.setAdapter(arrayAdapter);

            spComany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });

            final String[] hourArray =getResources().getStringArray(R.array.hours_array);

            spHours = findViewById(R.id.hoursSpinner);
            if (spHours != null) {
                ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, hourArray);
                spHours.setAdapter(arrayAdapter1);

                spHours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }

                });
            }
        }


    }

    public void getBack(View v)
    {
        Intent intent = new Intent(this,WorkPlacesActivity.class);
        startActivity(intent);
    }

    public void creatNewJob(View v)
    {


        if(!TextUtils.isEmpty(payment.getText().toString()))
        {
            String companyName=spComany.getSelectedItem().toString();
            Double val=Double.valueOf(""+payment.getText().toString());
            Double h = Double.valueOf(spHours.getSelectedItem().toString());
            String id =myRef.push().getKey();


            Job job = new Job(id,companyName,val,h);
            myRef.child(id).setValue(job);

            Toast.makeText(this, "Job Added", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,WorkPlacesActivity.class);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(this, "Please enter the payment", Toast.LENGTH_SHORT).show();
        }


    }


}