package abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import abhishekint.com.newsappupdate.R;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Adapter.NewsViewHolder.NewsItemViewHolder;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Adapter.SubAdapter.IndiaAdapter;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Adapter.SubAdapter.SourceItemAdapter;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Adapter.SubAdapter.ViewHolderLayer;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Adapter.SubAdapter.ViewLayer;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationLayer.NewsAdapterView;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationModel.NewsModel;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationModel.SourceModel;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Presenter.NewsPresenter;

/**
 * Created by abhishek on 28-03-2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ViewLayer {
    NewsAdapterView newsAdapterView;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    IndiaAdapter indiaAdapter;
    SourceItemAdapter sourceItemAdapter;
    NewsPresenter newsPresenter;
    NewsModel newsModel;
    List<NewsModel> newsModels;
    List<SourceModel> sourceModels;
    int page=1;
    int pageSize=10;
    List<Integer> images=new ArrayList<>();

    public NewsAdapter(NewsAdapterView newsAdapterView,NewsPresenter newsPresenter, Context context) {
        this.newsAdapterView = newsAdapterView;
        this.context=context;
        this.newsPresenter=newsPresenter;
        newsModel=new NewsModel();
        images.add(R.drawable.entertainment);
        images.add(R.drawable.science);
        images.add(R.drawable.general);
        images.add(R.drawable.technology);
        images.add(R.drawable.business);
        images.add(R.drawable.sports);
        images.add(R.drawable.health);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_genre,parent,false);
            return new NewsAdapterViewHolder(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_genre,parent,false);
            return new NewsAdapterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()==0)
        {
            ((NewsAdapterViewHolder)holder).textView.setText(newsModels.get(position).getTopName());
            layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            ((NewsAdapterViewHolder)holder).recyclerView.setLayoutManager(layoutManager);
            indiaAdapter=new IndiaAdapter(context,newsAdapterView,newsPresenter,newsModels.get(position),page,pageSize);
            ((NewsAdapterViewHolder)holder).recyclerView.setAdapter(indiaAdapter);
            newsPresenter.getIndiaNews(newsModels.get(position),indiaAdapter,newsModels.get(position).getNameId(),page,pageSize);
        }
        else
        {
            ((NewsAdapterViewHolder)holder).textView.setText(sourceModels.get(position-5).getTopSourceName());
            layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            ((NewsAdapterViewHolder)holder).recyclerView.setLayoutManager(layoutManager);
            sourceItemAdapter=new SourceItemAdapter(context,newsPresenter,sourceModels.get(position-5),images.get(position-5));
            ((NewsAdapterViewHolder)holder).recyclerView.setAdapter(sourceItemAdapter);
            newsPresenter.getSourcesWithCategory(sourceModels.get(position-5),sourceItemAdapter,sourceModels.get(position-5).getSourceId());
        }
        /*layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        ((NewsAdapterViewHolder)holder).recyclerView.setLayoutManager(layoutManager);
        indiaAdapter=new IndiaAdapter(context,newsAdapterView,newsPresenter,newsModel);
        ((NewsAdapterViewHolder)holder).recyclerView.setAdapter(indiaAdapter);*/
    }



    @Override
    public int getItemCount() {
        Log.e("chck for cunt","check for count"+newsModels.size());
        if (sourceModels==null)
        return newsModels != null ? newsModels.size() : 0;
        else
            return newsModels.size()+sourceModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0||position==1||position==2||position==3||position==4)
            return 0;
        else return 1;
    }

    @Override
    public void getAllTopName(List<NewsModel> newsModels) {
        Log.e("chck for method","cehckf for mehod"+newsModels.size());
        this.newsModels=newsModels;
        notifyDataSetChanged();
    }

    @Override
    public void getAllSourcesName(List<SourceModel> sourceModels) {
        this.sourceModels=sourceModels;
        notifyDataSetChanged();
    }

    @Override
    public void notifyExperiment() {
        notifyItemMoved(10,12);
    }

    @Override
    public void setIndiaNews() {

    }

    public class NewsAdapterViewHolder extends RecyclerView.ViewHolder
    {
        public RecyclerView recyclerView;
        public TextView textView;
        Button aa;
        public NewsAdapterViewHolder(View itemView) {
            super(itemView);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.item_news_genre_recyclerview);
            textView=(TextView)itemView.findViewById(R.id.fragment_fashion_new_title);
            aa=(Button)itemView.findViewById(R.id.aa);
            aa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyExperiment();
                }
            });
        }

        /*@Override
        public void setIndiaItem() {
            *//*layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            recyclerView.setLayoutManager(layoutManager);
            indiaAdapter=new IndiaAdapter(context,newsAdapterView,newsPresenter,newsModels.get(getAdapterPosition()));
            recyclerView.setAdapter(indiaAdapter);*//*

        }*/
    }
}
