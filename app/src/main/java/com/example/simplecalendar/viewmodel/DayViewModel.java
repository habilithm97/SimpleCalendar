package com.example.simplecalendar.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.simplecalendar.model.MainModel;

import java.util.Calendar;

public class DayViewModel extends ViewModel {
    public MainModel<Calendar> calender = new MainModel<>();

    public void setCalender(Calendar calender) {
        this.calender.setValue(calender);
    }
}
