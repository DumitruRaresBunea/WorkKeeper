package com.example.workkeeper;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;


import com.firebase.ui.database.FirebaseIndexArray;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class WorkPlacesActivity extends AppCompatActivity {

    private FloatingActionButton btn;
    private RecyclerView jobView;
    private DatabaseReference databaseJobs;
    private List<Job> jobList;
    private JobAdapter adapter;
    private ImageView imageView;
    private final int[] icons = {R.drawable.cleaning,R.drawable.construction,R.drawable.delivery,R.drawable.kitchen,R.drawable.packing,R.drawable.warekouse};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_places);

        imageView=findViewById(R.id.companyLogo);

        jobList= new ArrayList<>();

        databaseJobs = FirebaseDatabase.getInstance().getReference("jobs");


        jobView = findViewById(R.id.jobView);

        btn=findViewById(R.id.addJob);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddJobs(v);
            }
        });

        this.jobView = (RecyclerView) findViewById(R.id.jobView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.jobView.setLayoutManager(mLayoutManager);

        adapter = new JobAdapter(jobList);
        this.jobView.setAdapter(adapter);

        databaseJobs.child("jobs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              Iterable<DataSnapshot> children=  dataSnapshot.getChildren();

              for(DataSnapshot child: children)
              {
                 Job job= child.getValue(Job.class);
                jobList.add(job);
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Job> options = new FirebaseRecyclerOptions.Builder<Job>().setQuery(databaseJobs,Job.class).build();

        FirebaseRecyclerAdapter<Job, ViewHolder> adapter=
        new FirebaseRecyclerAdapter<Job, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Job model) {
                holder.companyName.setText("Company: "+model.getCompanyName());
                holder.hours.setText("Hours: "+String.valueOf(model.getHours()));
                holder.moneyTotal.setText("Total Payment:"+String.valueOf(model.getMoneyTotal()));
                holder.payment.setText("Hourly Payment: "+String.valueOf(model.getSalary()));
                holder.date.setText("Date: "+model.getDate());

                if(position%2==0) {
                    holder.item.setBackgroundColor(Color.parseColor("#80dbad"));
                }
                else{
                    holder.item.setBackgroundColor(Color.parseColor("#c4db80"));
                }



                switch (model.getCompanyName())
                {

                    case "Cleaning":
                    Drawable d0 = getResources().getDrawable(icons[0]);
                    holder.imageView.setImageDrawable(d0);
                        break;

                    case "Construction":
                        Drawable d1 = getResources().getDrawable(icons[1]);
                        holder.imageView.setImageDrawable(d1);
                        break;

                    case "Delivery":
                        Drawable d2 = getResources().getDrawable(icons[2]);
                        holder.imageView.setImageDrawable(d2);
                        break;

                    case "Kitchen":
                        Drawable d3 = getResources().getDrawable(icons[3]);
                        holder.imageView.setImageDrawable(d3);
                        break;

                    case "Packing":
                        Drawable d4 = getResources().getDrawable(icons[4]);
                        holder.imageView.setImageDrawable(d4);
                        break;

                    case "Warehouse":
                        Drawable d5 = getResources().getDrawable(icons[5]);
                        holder.imageView.setImageDrawable(d5);
                        break;

                    default:


                }
            }




            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.work_card_layou,viewGroup,false);
                ViewHolder viewHolder = new ViewHolder(view);
                return viewHolder;
            }

        };
jobView.setAdapter(adapter);
adapter.startListening();


    }

    public void goToAddJobs(View view)
    {
        Intent intent = new Intent(this,CreateNewWokingPlaceActivity.class);
        startActivity(intent);
    }




}
