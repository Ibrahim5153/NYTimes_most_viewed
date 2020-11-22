package com.ibrahim.nytmostviewd.views;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ibrahim.nytmostviewd.DI.DaggerNewsComponent;
import com.ibrahim.nytmostviewd.DI.NetworkModule;
import com.ibrahim.nytmostviewd.DI.NewsComponent;
import com.ibrahim.nytmostviewd.DI.ViewModelFactory;
import com.ibrahim.nytmostviewd.R;
import com.ibrahim.nytmostviewd.databinding.ActivityMainBinding;
import com.ibrahim.nytmostviewd.repositories.NewsRepository;
import com.ibrahim.nytmostviewd.viewmodels.NewsViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.fragment.NavHostFragment;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NewsComponent newsComponent = DaggerNewsComponent.builder().networkModule(new NetworkModule("https://api.nytimes.com/svc/")).build();
        newsComponent.inject(this);

        NewsViewModel newsViewModel = new ViewModelProvider(this, viewModelFactory).get(NewsViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewmodel(newsViewModel);
        View view = binding.getRoot();
        setContentView(view);
    }
}