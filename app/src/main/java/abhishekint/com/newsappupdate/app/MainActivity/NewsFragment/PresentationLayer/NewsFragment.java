package abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationLayer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import abhishekint.com.newsappupdate.NewsApplication;
import abhishekint.com.newsappupdate.R;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Adapter.NewsAdapter;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Interater.NewsApiHit;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Presenter.NewsPresenter;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Presenter.NewsPresenterImpl;
import abhishekint.com.newsappupdate.schedulers.AppSchedulerProvider;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abhishek on 17-03-2018.
 */

public class NewsFragment extends Fragment implements NewsView,NewsAdapterView{
    @BindView(R.id.common_recycler_view)
    RecyclerView common_recycler_view;
    @Inject
    public NewsApiHit newsApiHit;
    @Inject
    public AppSchedulerProvider appSchedulerProvider;
    View view;
    RecyclerView.LayoutManager layoutManager;
    NewsPresenter newsPresenter;
    NewsAdapter newsAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.recycler_view,container,false);
        ButterKnife.bind(this,view);
        ((NewsApplication) getActivity().getApplicationContext()).getNewsAppComponent().c(this);
        initializeView();
        initializeRest();
        adapterInit();
        return view;
    }

    private void adapterInit() {
        newsPresenter.getTopName(newsAdapter);
        newsPresenter.getSources(newsAdapter);
    }

    private void initializeRest() {
        newsPresenter=new NewsPresenterImpl(newsApiHit,appSchedulerProvider,this,newsAdapter);
        newsAdapter=new NewsAdapter(this,newsPresenter,getActivity());
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        SnapHelper snapHelper=new LinearSnapHelper();
        snapHelper.attachToRecyclerView(common_recycler_view);
        common_recycler_view.setLayoutManager(layoutManager);
        common_recycler_view.setNestedScrollingEnabled(false);
        common_recycler_view.setAdapter(newsAdapter);
    }

    private void initializeView() {
    }
}
