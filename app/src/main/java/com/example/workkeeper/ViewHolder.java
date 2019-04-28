package com.example.workkeeper;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public  class ViewHolder extends RecyclerView.ViewHolder {
    public final View view;
    public final TextView companyName;
    public final TextView payment;
    public final TextView hours;
    public final TextView moneyTotal;
    public final TextView date;
    public final ConstraintLayout item;


  public final ImageView imageView;

    public ViewHolder(View view) {
        super(view);
        this.view = view;
        companyName = view.findViewById(R.id.companyName);
        payment = view.findViewById((R.id.payment));
        hours = view.findViewById(R.id.hours);
        moneyTotal=view.findViewById(R.id.moneyTotal);
        imageView=view.findViewById(R.id.companyLogo);
        date=view.findViewById(R.id.dateView);
        item=view.findViewById(R.id.cLayout);
    }




}
