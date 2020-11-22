package com.ibrahim.nytmostviewd.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.ibrahim.nytmostviewd.R;
import com.ibrahim.nytmostviewd.databinding.FragmentDetailBinding;
import com.ibrahim.nytmostviewd.databinding.FragmentMasterBinding;
import com.ibrahim.nytmostviewd.viewmodels.NewsViewModel;

public class DetailFragment extends Fragment {

    private NewsViewModel newsViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        FragmentDetailBinding binding = FragmentDetailBinding.inflate(inflater, container, false);
        newsViewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);
        binding.setViewmodel(newsViewModel);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsViewModel.masterFragmentViewModelObserver.setToolbarTitle("Article Detail");

    }
}