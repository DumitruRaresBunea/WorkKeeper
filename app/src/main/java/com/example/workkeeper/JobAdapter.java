package com.example.workkeeper;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<com.example.workkeeper.ViewHolder> {

    private List<Job> jobList;

    public JobAdapter(List<Job> jobList)
    {
        this.jobList=jobList;
    }

    @NonNull
    @Override
    public com.example.workkeeper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.work_card_layou, parent, false);

        return new com.example.workkeeper.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull com.example.workkeeper.ViewHolder viewHolder, int i) {
        Job job= jobList.get(i);
        viewHolder.companyName.setText(job.getCompanyName());
       viewHolder.payment.setText(String.valueOf(job.getSalary()));
       viewHolder.hours.setText(String.valueOf(job.getHours()));
       viewHolder.moneyTotal.setText(String.valueOf(job.getMoneyTotal()));
       viewHolder.date.setText(job.getDate());



    }



    public int getItemCount() {
        return jobList.size();
    }
}
