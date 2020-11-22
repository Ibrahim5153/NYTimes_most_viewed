package com.ibrahim.nytmostviewd.DI;

import com.ibrahim.nytmostviewd.views.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewModelModule.class, NetworkModule.class})
public interface NewsComponent {

    void inject(MainActivity mainActivity);
}