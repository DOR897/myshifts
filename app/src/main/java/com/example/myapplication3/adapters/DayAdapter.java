package com.example.myapplication3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.R;
import com.example.myapplication3.fragments.CalendarFragment;
import com.example.myapplication3.models.Day;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {

    private Context context;
    private List<Day> dayList;

    public DayAdapter(Context context, List<Day> dayList) {
        this.context = context;
        this.dayList = dayList;
    }

    public DayAdapter(CalendarFragment calendarFragment, List<Day> dayList) {
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.day, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        Day day = dayList.get(position);
        holder.textViewDate.setText(day.getDay());
        holder.textViewCheckIn.setText(day.getCheckInTime());
        holder.textViewCheckOut.setText(day.getCheckOutTime());
    }

    @Override
    public int getItemCount() {

        if(dayList==null){
            return 0;
        }
        else return dayList.size();
    }

    class DayViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDate;
        TextView textViewCheckIn;
        TextView textViewCheckOut;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.dayTextviewDayPage);
            textViewCheckIn = itemView.findViewById(R.id.checkInTextviewDayPage);
            textViewCheckOut = itemView.findViewById(R.id.checkOutTextviewDayPage);
        }
    }
}
