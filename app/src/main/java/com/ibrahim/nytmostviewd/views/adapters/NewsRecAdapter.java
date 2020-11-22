package com.ibrahim.nytmostviewd.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ibrahim.nytmostviewd.R;
import com.ibrahim.nytmostviewd.data.local.NewsModelLocal;
import com.ibrahim.nytmostviewd.databinding.ArticlesRecItemBinding;
import com.ibrahim.nytmostviewd.utils.MyDiffUtilCallBack;
import com.ibrahim.nytmostviewd.viewmodels.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewsRecAdapter extends RecyclerView.Adapter<NewsRecAdapter.NewsViewHolder> {

    ArrayList<NewsModelLocal> data = new ArrayList<>();
    NewsViewModel newsViewModel;

    public NewsRecAdapter(NewsViewModel newsViewModel) {
        this.newsViewModel = newsViewModel;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.articles_rec_item, parent, false);
        return new NewsViewHolder(binding);
    }

    public void updateData(List<NewsModelLocal> data) {

        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MyDiffUtilCallBack(this.data, data));
        result.dispatchUpdatesTo(this);
        this.data.clear();
        this.data.addAll(data);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.bind(data.get(position), newsViewModel);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public NewsModelLocal getItem(int position){
        return data.get(position);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        private ArticlesRecItemBinding binding;

        public void bind(NewsModelLocal news, NewsViewModel newsViewModel){

            binding.setNews(news);
            binding.setViewmodel(newsViewModel);
            binding.executePendingBindings();
        }

        public NewsViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ArticlesRecItemBinding) binding;
        }
    }
}
