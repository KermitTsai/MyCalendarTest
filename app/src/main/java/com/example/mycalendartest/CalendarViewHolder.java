package com.example.mycalendartest;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public  final TextView dayOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;
    public  final  View parentView;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);

        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        parentView = itemView.findViewById(R.id.parentView);
        this.onItemListener = onItemListener;
    }


    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(getAdapterPosition(),(String)dayOfMonth.getText());
    }
}
