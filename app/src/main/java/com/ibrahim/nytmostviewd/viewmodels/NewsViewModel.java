package com.ibrahim.nytmostviewd.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ibrahim.nytmostviewd.data.local.NewsModelLocal;
import com.ibrahim.nytmostviewd.data.observers.MasterFragmentViewModelObserver;

import com.ibrahim.nytmostviewd.repositories.NewsRepository;
import com.ibrahim.nytmostviewd.utils.Event;
import com.ibrahim.nytmostviewd.views.DetailFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NewsViewModel extends BaseViewModel {

    public MasterFragmentViewModelObserver masterFragmentViewModelObserver = new MasterFragmentViewModelObserver();
    public MutableLiveData<Event<Type>> navigationLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<NewsModelLocal>> items = new MutableLiveData<>();

    NewsRepository newsRepository;

    @Inject
    public NewsViewModel(NewsRepository newsRepository) {

        this.newsRepository = newsRepository;
        refreshData();
    }



    public void onNewTapped(NewsModelLocal newsModelLocal){

        masterFragmentViewModelObserver.setNewsModelLocal(newsModelLocal);
        navigationLiveData.postValue(new Event<>(DetailFragment.class));
    }

    public void refreshData(){

        masterFragmentViewModelObserver.setLoading(true);
        newsRepository.getMostViewedNews(items);
    }

}
