package com.example.mycalendartest;

import static com.example.mycalendartest.MainActivity.selectedDate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;
    private LocalDate nowDate;
    public String pageMonth;

    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell,parent,false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height=(int)(parent.getHeight()*0.166666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position));

        //控制indicator
        final String date = daysOfMonth.get(position);
        nowDate = LocalDate.now();
        String currentDay,currentMonth;
        DateTimeFormatter nowDay = DateTimeFormatter.ofPattern("dd");
        currentDay = nowDate.format(nowDay);
        DateTimeFormatter nowMonth = DateTimeFormatter.ofPattern("MM");
        currentMonth = nowDate.format(nowMonth);
        DateTimeFormatter floatingMonth = DateTimeFormatter.ofPattern("MM");
        pageMonth =selectedDate.format(floatingMonth);
        if (pageMonth.equals(currentMonth))
            if (date.equals(currentDay))
                holder.parentView.setBackgroundResource(R.drawable.indicator);

    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnItemListener{
        void onItemClick(int poistion,String dayText);

    }
}
