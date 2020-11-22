package com.ibrahim.nytmostviewd.utils;

import androidx.recyclerview.widget.DiffUtil;

import com.ibrahim.nytmostviewd.data.local.NewsModelLocal;

import java.util.List;

public class MyDiffUtilCallBack extends DiffUtil.Callback {

    List<NewsModelLocal> oldData;
    List<NewsModelLocal> newData;

    public MyDiffUtilCallBack(List<NewsModelLocal> oldData, List<NewsModelLocal> newData) {
        this.oldData = oldData;
        this.newData = newData;
    }

    @Override
    public int getOldListSize() {
        return oldData.size();
    }

    @Override
    public int getNewListSize() {
        return newData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).getId() == newData.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).compareTo(newData.get(newItemPosition)) == 0;
    }
}
