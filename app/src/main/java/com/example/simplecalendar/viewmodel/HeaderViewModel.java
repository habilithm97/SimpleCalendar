package com.example.simplecalendar.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.simplecalendar.model.MainModel;

public class HeaderViewModel extends ViewModel {
    public MainModel<Long> headerDate = new MainModel<>();

    public void setHeaderDate(long headerDate) {
        this.headerDate.setValue(headerDate);
    }
}
