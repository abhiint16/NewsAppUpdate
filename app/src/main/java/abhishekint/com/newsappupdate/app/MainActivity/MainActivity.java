package abhishekint.com.newsappupdate.app.MainActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import javax.inject.Inject;

import abhishekint.com.newsappupdate.NewsApplication;
import abhishekint.com.newsappupdate.R;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Interater.NewsApiHit;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationLayer.NewsFragment;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationModel.EverythingModel;
import abhishekint.com.newsappupdate.app.MainActivity.SearchFragment.SearchFragment;
import abhishekint.com.newsappupdate.schedulers.AppSchedulerProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by abhishek on 14-03-2018.
 */

public class MainActivity extends AppCompatActivity implements MainActivityView, BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener, SearchView.OnQueryTextListener {

    @BindView(R.id.main_frame_layout)
    FrameLayout main_frame_layout;
    @BindView(R.id.fragment_news_toolbar_search)
    SearchView fragment_news_toolbar_search;
    @BindView(R.id.main_bottom_navigation_view)
    BottomNavigationView main_bottom_navigation_view;
    @Inject
    public NewsApiHit newsApiHit;
    @Inject
    public AppSchedulerProvider appSchedulerProvider;
    Unbinder unbinder;
    MainPresenter mainPresenter;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((NewsApplication) getApplicationContext()).getNewsAppComponent().b(this);
        unbinder = ButterKnife.bind(this);
        presenterInit();
        initializeViews();
        initializeRest();
        initializeToolbar();
        firstTimeApplaunch(new NewsFragment());
    }

    private void initializeToolbar() {
        fragment_news_toolbar_search.setOnSearchClickListener(this);
        fragment_news_toolbar_search.setOnQueryTextListener(this);
    }

    private void initializeRest() {
        fragmentManager = getFragmentManager();
        main_bottom_navigation_view.setOnNavigationItemSelectedListener(this);
    }

    private void initializeViews() {
    }

    private void presenterInit() {
        mainPresenter = new MainPresenterImpl(this,newsApiHit,appSchedulerProvider);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void firstTimeApplaunch(Fragment fragment) {
        fragmentManager.beginTransaction()
                .add(R.id.main_frame_layout, fragment)
                .addToBackStack("")
                .commit();
    }

    @Override
    public void launchFragmentWIthoutSaving(Fragment fragment) {
        fragmentManager.beginTransaction()
                .add(R.id.main_frame_layout, fragment)
                .addToBackStack("")
                .commit();
    }

    @Override
    public void showToast(String toastMsg) {
        Toast.makeText(this,toastMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchCallWithData(Fragment fragment, EverythingModel everythingModel) {
        SearchFragment searchFragment= (SearchFragment) fragment;
        searchFragment.getSearchQueryData(everythingModel);
        launchFragmentWIthoutSaving(fragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_news:
            {
                Timber.e(""+fragmentManager.findFragmentById(R.id.main_frame_layout).getClass().getName());
                Timber.e(""+new NewsFragment().getClass().getName());
                if (fragmentManager.findFragmentById(R.id.main_frame_layout).getClass().getName().equals(new NewsFragment().getClass().getName()))
                {
                    Toast.makeText(this, "You're already on News", Toast.LENGTH_SHORT).show();
                }
                else if (!fragmentManager.findFragmentById(R.id.main_frame_layout).getClass().getName().equals(new NewsFragment().getClass().getName()))
                {
                    firstTimeApplaunch(new NewsFragment());
                    Toast.makeText(this, "opening News", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this, "wrong class name", Toast.LENGTH_SHORT).show();
            }
                break;
            case R.id.menu_articles:
                Toast.makeText(this, "Comming Soon", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        mainPresenter.SearchButtonClick(new SearchFragment(),"","onclick");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mainPresenter.SearchButtonClick(new SearchFragment(),query,"textsubmit");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mainPresenter.SearchButtonClick(new SearchFragment(),newText,"textchange");
        return false;
    }
}
