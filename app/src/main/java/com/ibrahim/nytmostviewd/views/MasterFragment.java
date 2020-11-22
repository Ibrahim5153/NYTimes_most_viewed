package com.ibrahim.nytmostviewd.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ibrahim.nytmostviewd.R;
import com.ibrahim.nytmostviewd.data.local.NewsModelLocal;
import com.ibrahim.nytmostviewd.databinding.FragmentMasterBinding;
import com.ibrahim.nytmostviewd.utils.Event;
import com.ibrahim.nytmostviewd.viewmodels.NewsViewModel;
import com.ibrahim.nytmostviewd.views.adapters.NewsRecAdapter;

import java.lang.reflect.Type;
import java.util.List;

public class MasterFragment extends Fragment {

    NewsViewModel newsViewModel;
    NewsRecAdapter newsRecAdapter;
    FragmentMasterBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        binding = FragmentMasterBinding.inflate(inflater, container, false);
        newsViewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);
        newsRecAdapter = new NewsRecAdapter(newsViewModel);
        binding.setViewmodel(newsViewModel);
        binding.setAdapter(newsRecAdapter);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setUpNavigation();
    }

    private void setUpNavigation(){

        newsViewModel.navigationLiveData.observe(requireActivity(), typeEvent -> {
            if(!typeEvent.hasBeenHandeled()){
                typeEvent.getContent();
                NavHostFragment.findNavController(MasterFragment.this)
                        .navigate(R.id.action_MasterFragment_to_DetailFragment);
            }
        });
    }

    private void init(){

        newsViewModel.masterFragmentViewModelObserver.setToolbarTitle("NYTimes Most Viewed");
        newsViewModel.items.observe(MasterFragment.this.requireActivity(), new Observer<List<NewsModelLocal>>() {
            @Override
            public void onChanged(List<NewsModelLocal> newsModelLocals) {
                if(newsModelLocals != null){
                    newsRecAdapter.updateData(newsModelLocals);
                }

                newsViewModel.masterFragmentViewModelObserver.setLoading(false);
            }
        });


    }

}