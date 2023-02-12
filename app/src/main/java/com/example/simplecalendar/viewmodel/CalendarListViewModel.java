package com.example.simplecalendar.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.simplecalendar.model.MainModel;
import com.example.simplecalendar.utils.DateFormat;
import com.example.simplecalendar.utils.Keys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarListViewModel extends ViewModel {
    long currentTime;

    MainModel<String> title = new MainModel<>();
    MainModel<List<Object>> mCalendarList = new MainModel<>(new ArrayList<>());

    public void initCalendarList() {
        GregorianCalendar cal = new GregorianCalendar(); // 오늘 날짜
        setCalendarList(cal);
    }

    public void setCalendarList(GregorianCalendar cal) {
        setTitle(cal.getTimeInMillis());

        List<Object> calendarList = new ArrayList<>();

        for (int i = -300; i < 300; i++) {
            try {
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1, 0, 0, 0);

                calendarList.add(calendar.getTimeInMillis());

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; // DAY_OF_WEEK : calendar가 가리키는 특정 날짜가 무슨 요일인지 알기 위해 쓰임
                int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 월의 말일 구하기

                for (int j = 0; j < dayOfWeek; j++) {
                    calendarList.add(Keys.EMPTY);
                }
                for (int j = 1; j <= max; j++) {
                    calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), j));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mCalendarList.setValue(calendarList);
    }

    public void setTitle(long time) {
        currentTime = time;
        title.setValue(DateFormat.getDate(time, DateFormat.TITLE));
    }
}
