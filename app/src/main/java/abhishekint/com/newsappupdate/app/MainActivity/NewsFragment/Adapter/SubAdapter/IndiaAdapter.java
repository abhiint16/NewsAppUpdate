package abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Adapter.SubAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import abhishekint.com.newsappupdate.R;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Adapter.NewsViewHolder.NewsItemViewHolder;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationLayer.NewsAdapterView;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationModel.NewsModel;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Presenter.NewsPresenter;

/**
 * Created by abhishek on 02-04-2018.
 */

public class IndiaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  implements ViewHolderLayer{
    Context context;
    NewsAdapterView newsAdapterView;
    NewsPresenter newsPresenter;
    NewsModel newsModel;
    int page;
    int pageSize;
    public IndiaAdapter(Context context, NewsAdapterView newsAdapterView, NewsPresenter newsPresenter,NewsModel newsModel
            ,int page,int pageSize ) {
        this.context=context;
        this.newsAdapterView=newsAdapterView;
        this.newsPresenter=newsPresenter;
        this.newsModel=newsModel;
        this.page=page;
        this.pageSize=pageSize;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blank_page_trans_back,parent,false);
            return new BlankPageViewHolder(view);
        }
        else if (viewType==1)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more, parent, false);
            return new LoadMoreViewHolder(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_recycler,parent,false);
            return new NewsItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()==1)
        {
            loadMore();
        }
        else if (holder.getItemViewType()==2)
        {
            if (newsModel.getArticles().get(position-1).getUrlToImage()!=null)
            Glide.with(context)
                    .load(newsModel.getArticles().get(position-1).getUrlToImage())
                    .thumbnail(Glide.with(context).load(R.drawable.default_news_img))
                    .into(((NewsItemViewHolder)holder).item_news_recycler_image_iv);
            else
                Glide.with(context)
                        .load(R.drawable.default_news_img)
                        .into(((NewsItemViewHolder)holder).item_news_recycler_image_iv);
            ((NewsItemViewHolder)holder).item_news_recycler_title_tv.setText(newsModel.getArticles().get(position-1).getTitle());
            if (newsModel.getArticles().get(position-1).getSource()!=null)
            ((NewsItemViewHolder)holder).item_news_recycler_source_tv.setText(newsModel.getArticles().get(position-1).getSource().getName());
            else
                ((NewsItemViewHolder)holder).item_news_recycler_source_tv.setVisibility(View.GONE);
        }
    }

    private void  loadMore() {
        newsPresenter.getIndiaNews(newsModel,this,newsModel.getNameId(),page+1,pageSize);
        this.page=page+1;
    }

    @Override
    public int getItemCount() {
        return newsModel.getArticles()!=null?
                (newsModel.getArticles().size()==20?
                        newsModel.getArticles().size()+1:
                        newsModel.getArticles().size()+2):
                0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0)
            return 0;
        else if (position>newsModel.getArticles().size())
        {
            if (newsModel.getArticles().size()!=20)
            return 1;
            return 2;
        }
        else
            return 2;
    }

    @Override
    public void setIndiaItem() {
        notifyDataSetChanged();
    }

    public class BlankPageViewHolder extends RecyclerView.ViewHolder
    {
        public BlankPageViewHolder(View itemView) {
            super(itemView);
        }
    }
    
    public class LoadMoreViewHolder extends RecyclerView.ViewHolder
    {
        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }
}
