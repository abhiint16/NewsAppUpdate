package abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Presenter;

import android.util.Log;

import java.util.List;

import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Adapter.SubAdapter.SourceItemAdapterLayer;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Adapter.SubAdapter.ViewHolderLayer;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Adapter.SubAdapter.ViewLayer;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Interater.NewsApiHit;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Interater.NewsApiHitInterface;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationLayer.NewsView;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationModel.NewsModel;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationModel.SourceModel;
import abhishekint.com.newsappupdate.schedulers.AppSchedulerProvider;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by abhishek on 19-03-2018.
 */

public class NewsPresenterImpl implements NewsPresenter{

    NewsApiHit newsApiHit;
    AppSchedulerProvider appSchedulerProvider;
    NewsView newsView;
    NewsApiHitInterface newsApiHitInterface;
    ViewLayer viewLayer;
    public NewsPresenterImpl(NewsApiHit newsApiHit, AppSchedulerProvider appSchedulerProvider, NewsView newsView,ViewLayer viewLayer) {
        this.newsApiHit=newsApiHit;
        this.appSchedulerProvider=appSchedulerProvider;
        this.newsView=newsView;
    }

    @Override
    public void loadCategorywiseCountryData(int page, String country, String category) {

    }

    @Override
    public int getNewsSize(NewsModel newsModel) {
        /*if (newsModel.getArticles()==null||newsModel.getArticles().size()==0)
        {
            hitAPI();
        }
        else*/
        return newsModel.getArticles().size();
    }

    @Override
    public void getTopName(ViewLayer viewLayer) {
        this.viewLayer=viewLayer;
        viewLayer.getAllTopName(newsApiHit.fillModelWithTopName());
    }

    @Override
    public void getSources(ViewLayer viewLayer) {
        viewLayer.getAllSourcesName(newsApiHit.fillModelWithSourcesName());
    }

    @Override
    public void getSourcesWithCategory(final SourceModel sourceModel, final SourceItemAdapterLayer sourceItemAdapterLayer, String nameId) {
        newsApiHit.loadSourcesWithCategory(nameId)
                .subscribeOn(appSchedulerProvider.io())
                .observeOn(appSchedulerProvider.ui())
                .subscribe(new Observer<SourceModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Timber.e(""+d);
                    }

                    @Override
                    public void onNext(SourceModel sourceModel1) {
                        //newsModel.setArticles(countryTopHeadNews.getArticles());
                        if (sourceModel.getSources()==null)
                        {
                            sourceModel.setSources(sourceModel1.getSources());
                        }
                        else
                        {
                            sourceModel.getSources().addAll(sourceModel1.getSources());
                        }
                        sourceItemAdapterLayer.setSourceItem();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void getIndiaNews(final NewsModel newsModel, final ViewHolderLayer viewHolderLayer,String nameId,int page,int pageSize) {
            newsApiHit.loadNewsDataFromApi(page,pageSize,nameId,newsModel)
                    .subscribeOn(appSchedulerProvider.io())
                    .observeOn(appSchedulerProvider.ui())
                    .subscribe(new Observer<NewsModel>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Timber.e(""+d);
                        }

                        @Override
                        public void onNext(NewsModel countryTopHeadNews) {
                            //newsModel.setArticles(countryTopHeadNews.getArticles());
                            viewHolderLayer.setIndiaItem();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Timber.e(e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }


    /*public void hitAPI()
    {
        *//*Observable<String>  observable=*//*newsApiHit.loadNewsDataFromApi(page,country)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())
            .subscribe(new Observer<CountryTopHeadNews>() {
                @Override
                public void onSubscribe(Disposable d) {
                    Timber.e(""+d);
                }

                @Override
                public void onNext(CountryTopHeadNews countryTopHeadNews) {
                    newsFragMethodCall.interfaceOnNextCall(countryTopHeadNews);
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e(e);
                }

                @Override
                public void onComplete() {

                }
            });
    }*/
}
