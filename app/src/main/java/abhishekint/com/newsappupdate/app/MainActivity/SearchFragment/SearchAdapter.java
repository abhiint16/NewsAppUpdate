package abhishekint.com.newsappupdate.app.MainActivity.SearchFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import abhishekint.com.newsappupdate.R;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationModel.EverythingModel;

/**
 * Created by abhishek on 14-04-2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    EverythingModel everythingModel;
    public SearchAdapter(Context context, EverythingModel everythingModel) {
        this.context=context;
        this.everythingModel=everythingModel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_recycler,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SearchViewHolder)holder).item_search_recycler_title_tv.setText(everythingModel.getArticles().get(position).getTitle());
        ((SearchViewHolder)holder).item_search_recycler_source_tv.setText(everythingModel.getArticles().get(position).getSource().getName());
        Glide.with(context).load(everythingModel.getArticles().get(position).getUrlToImage())
                .thumbnail(Glide.with(context).load(R.drawable.default_news_img))
                .into(((SearchViewHolder)holder).item_search_recycler_image_iv);
    }

    @Override
    public int getItemCount() {
        if (everythingModel.getArticles()!=null&&everythingModel.getArticles().size()!=0)
        {
            return everythingModel.getArticles().size();
        }
        else return 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder
    {
        ImageView item_search_recycler_image_iv;
        TextView item_search_recycler_title_tv,item_search_recycler_source_tv;
        public SearchViewHolder(View itemView) {
            super(itemView);
            item_search_recycler_image_iv=(ImageView)itemView.findViewById(R.id.item_search_recycler_image_iv);
            item_search_recycler_title_tv=(TextView)itemView.findViewById(R.id.item_search_recycler_title_tv);
            item_search_recycler_source_tv=(TextView)itemView.findViewById(R.id.item_search_recycler_source_tv);
        }
    }
}
