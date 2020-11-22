package com.ibrahim.nytmostviewd.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ibrahim.nytmostviewd.data.local.NewsModelLocal;
import com.ibrahim.nytmostviewd.data.pojo.MediaMetadata;
import com.ibrahim.nytmostviewd.data.pojo.Medium;
import com.ibrahim.nytmostviewd.data.pojo.Post;
import com.ibrahim.nytmostviewd.data.pojo.Response;
import com.ibrahim.nytmostviewd.webservice.NYTimesApiService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class NewsRepository extends BaseRepository {

    Retrofit retrofit;

    @Inject
    public NewsRepository(Retrofit retrofit)
    {
        this.retrofit = retrofit;
    }

    public void getMostViewedNews(MutableLiveData<ArrayList<NewsModelLocal>> dataLD){
        retrofit.create(NYTimesApiService.class).getNewsList(1, "6gSR7gqbLsxSHYnYiLsuYPBpMKTh4fTs").enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if(response.code() == 200){

                    ArrayList<NewsModelLocal> newsData = new ArrayList<>();

                    List<Post> posts =  response.body().getPosts();
                    for (Post post :
                            posts) {

                        String imageTitle = "";
                        String copyrights = "";
                        MediaMetadata smallThumb = null;
                        MediaMetadata largeThumb = null;

                        if(post.getMedia() != null)
                        {
                            for (Medium medium :
                                    post.getMedia()) {

                                if(medium.getType().equals("image")){

                                    imageTitle = medium.getCaption();
                                    copyrights = medium.getCopyright();

                                    if(medium.getMediaMetadata()!= null && medium.getMediaMetadata().size()>0)
                                    {
                                        smallThumb = medium.getMediaMetadata().get(0);
                                        largeThumb = medium.getMediaMetadata().get(medium.getMediaMetadata().size() -1);
                                    }
                                }
                            }
                        }

                        newsData.add(new NewsModelLocal(post.getId(),
                                smallThumb != null ? smallThumb.getUrl() : "",
                                largeThumb != null ? largeThumb.getUrl() : "",
                                imageTitle,
                                post.getTitle(),
                                post.getAbstract(),
                                copyrights
                                ));
                    }


                    dataLD.postValue(newsData);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                int i = 0 ;
            }
        });

    }
}
