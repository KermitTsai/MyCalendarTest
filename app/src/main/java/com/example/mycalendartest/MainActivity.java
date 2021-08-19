package com.example.mycalendartest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements  CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    public static LocalDate selectedDate;
    public String monthString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        selectedDate=LocalDate.now();
        setMonthView();
    }

    private void initWidgets() {
        calendarRecyclerView=findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);

    }

    private void setMonthView() {

        monthYearText.setText(MonthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }
    //控制日期與日期排版
    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int z=0;//解決日期上方空白之計次變數
        int daysInMonth = yearMonth.lengthOfMonth();//當月之中有幾天 //yearMonth ->取得年月日

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int startDayOfFirstWeek = firstOfMonth.getDayOfWeek().getValue();

        //自己寫的迴圈
        // 1.大if:若startDayOfFirstWeek==7，避免第一個禮拜為空白，故額外處理填入日期 2.else if:除了startDayOfFirstWeek==7，填入空白日 3.else 填入日期
        for(int i=1;i<=42;i++){
            //if條件敘述為 日期開始前與後要填入空白 else則填入開始日期的數字
            if(startDayOfFirstWeek==7){
                if(i<=31)
                    daysInMonthArray.add(String.valueOf(i));
                else
                    break;

            }
            else if(i<=startDayOfFirstWeek || i >daysInMonth + startDayOfFirstWeek){
                daysInMonthArray.add(" ");//original code
            }
            else {
                daysInMonthArray.add(String.valueOf(i-startDayOfFirstWeek));
            }
        }

        /*原本的迴圈 cons:第一個禮拜空白
        for(int i=1;i<=42;i++){
            //if條件敘述為 日期開始前與後要填入空白 else則填入開始日期的數字
            if(i<=startDayOfFirstWeek || i >daysInMonth + startDayOfFirstWeek){
                daysInMonthArray.add(" ");//original code
            }
            else {
                daysInMonthArray.add(String.valueOf(i-startDayOfFirstWeek));
            }
        }*/
        return daysInMonthArray;

    }

    private String MonthYearFromDate(LocalDate date){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMMM yyyy", Locale.CHINA);
        return date.format(formatter);
    }

    public void previousMonthAction(View view) {
        selectedDate =selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        selectedDate=selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int poistion, String dayText) {
        if(!dayText.equals("")){
            String message = "Selected Date " + dayText+"  "+MonthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }


}