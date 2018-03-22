package abhishekint.com.newsappupdate.app.MainActivity.NewsFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import abhishekint.com.newsappupdate.NewsApplication;
import abhishekint.com.newsappupdate.R;
import abhishekint.com.newsappupdate.schedulers.AppSchedulerProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * Created by abhishek on 17-03-2018.
 */

public class NewsFragment extends Fragment implements NewsFragMethodCall{
    @BindView(R.id.common_recycler_view)
    RecyclerView common_recycler_view;
    /*@Inject
    public NewsApiClient newsApiClient;*/
    @Inject
    public NewsApiHit newsApiHit;
    /*@Inject
    public NewsIconClient newsIconClient;*/
    @Inject
    public AppSchedulerProvider appSchedulerProvider;
    View view;
    NewsAdapter newsAdapter;
    RecyclerView.LayoutManager layoutManager;
    NewsPresenter newsPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_news,container,false);
        ButterKnife.bind(this,view);
        ((NewsApplication) getActivity().getApplicationContext()).getNewsAppComponent().c(this);
        initializeView();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        newsPresenter=new NewsPresenter(newsApiHit,appSchedulerProvider,this);
        newsPresenter.loadNewsFeed();
        layoutManager=new GridLayoutManager(getActivity(),2);
        common_recycler_view.setLayoutManager(layoutManager);
        common_recycler_view.setNestedScrollingEnabled(false);
    }

    private void initializeView() {
    }

    @Override
    public void interfaceOnNextCall(NewsSourceDataModel value) {
        newsPresenter.loadNewsIcon(value);
        /*newsAdapter=new NewsAdapter(value);
        common_recycler_view.setAdapter(newsAdapter);
        Toast.makeText(getActivity(), ""+value.getSources().get(0).getId(), Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void interfaceOnIconNextCall(NewsSourceIconDataModel value,NewsSourceDataModel newsSourceDataModel) {
        newsAdapter=new NewsAdapter(value,newsSourceDataModel,getActivity());
        common_recycler_view.setAdapter(newsAdapter);
    }
}
