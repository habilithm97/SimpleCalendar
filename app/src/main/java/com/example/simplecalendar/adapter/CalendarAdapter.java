package com.example.simplecalendar.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.simplecalendar.R;
import com.example.simplecalendar.databinding.DayBinding;
import com.example.simplecalendar.databinding.EmptyDayBinding;
import com.example.simplecalendar.databinding.HeaderBinding;
import com.example.simplecalendar.viewmodel.DayViewModel;
import com.example.simplecalendar.viewmodel.EmptyViewModel;
import com.example.simplecalendar.viewmodel.HeaderViewModel;
import com.google.gson.Gson;

import java.util.Calendar;

public class CalendarAdapter extends ListAdapter<Object, RecyclerView.ViewHolder> {
    private final int HEADER = 0;
    private final int EMPTY = 1;
    private final int DAY = 2;

    public CalendarAdapter() {
        super(new DiffUtil.ItemCallback<Object>() {
            @Override // 아이템 항목 자체가 같은지 비교함
            public boolean areItemsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
                return oldItem == newItem;
            }
            @Override // 아이템의 내용을 비교함
            public boolean areContentsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
                Gson gson = new Gson();
                return gson.toJson(oldItem).equals(gson.toJson(newItem));
            }
        });
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        private HeaderBinding headerBinding;

        private HeaderViewHolder(@NonNull HeaderBinding headerBinding) {
            super(headerBinding.getRoot());
            this.headerBinding = headerBinding;
        }

        private void setViewModel(HeaderViewModel headerViewModel) {
            headerBinding.setModel(headerViewModel);
            headerBinding.executePendingBindings(); // 즉각적으로 변경사항을 적용시킴(뷰를 강제적으로 업데이트)
        }
    }

    private class EmptyViewHolder extends RecyclerView.ViewHolder {
        private EmptyDayBinding emptyDayBinding;

        private EmptyViewHolder(@NonNull EmptyDayBinding emptyDayBinding) {
            super(emptyDayBinding.getRoot());
            this.emptyDayBinding = emptyDayBinding;
        }
        private void setViewModel(EmptyViewModel emptyViewModel) {
            emptyDayBinding.setModel(emptyViewModel);
            emptyDayBinding.executePendingBindings();
        }
    }

    private class DayViewHolder extends RecyclerView.ViewHolder {
        private DayBinding dayBinding;

        private DayViewHolder(@NonNull DayBinding dayBinding) {
            super(dayBinding.getRoot());
            this.dayBinding = dayBinding;
        }

        private void setViewModel(DayViewModel dayViewModel) {
            dayBinding.setModel(dayViewModel);
            dayBinding.executePendingBindings();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER) {
            HeaderBinding headerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_header, parent, false);
            // StaggeredGridLayout : 높이가 불규칙한 그리드 레이아웃
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) headerBinding.getRoot().getLayoutParams();
            params.setFullSpan(true); // Span을 하나로 통합하기
            headerBinding.getRoot().setLayoutParams(params);
            return new HeaderViewHolder(headerBinding);

        } else if (viewType == EMPTY) {
            EmptyDayBinding emptyDayBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_day_empty, parent, false);
            return new EmptyViewHolder(emptyDayBinding);
        }
        DayBinding dayBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_day, parent, false);// 일자 타입
        return new DayViewHolder(dayBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        if (viewType == HEADER) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            HeaderViewModel headerViewModel = new HeaderViewModel();

            Object item = getItem(position);
            if (item instanceof Long) {
                headerViewModel.setHeaderDate((Long) item);
            }
            headerViewHolder.setViewModel(headerViewModel);

        } else if (viewType == EMPTY) {
            EmptyViewHolder emptyViewHolder = (EmptyViewHolder) holder;
            EmptyViewModel emptyViewModel = new EmptyViewModel();
            emptyViewHolder.setViewModel(emptyViewModel);

        } else if (viewType == DAY) {
            DayViewHolder emptyViewHolder = (DayViewHolder) holder;
            DayViewModel dayViewModel = new DayViewModel();

            Object item = getItem(position);
            if (item instanceof Calendar) {
                dayViewModel.setCalender((Calendar) item);
            }
            emptyViewHolder.setViewModel(dayViewModel);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object item = getItem(position);

        if(item instanceof Long) {
            return HEADER;
        } else if(item instanceof String) {
            return EMPTY;
        } else {
            return DAY;
        }
    }
}
