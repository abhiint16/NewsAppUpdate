package abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Interater;

import java.util.ArrayList;
import java.util.List;

import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationModel.EverythingModel;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationModel.NewsModel;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationModel.SourceModel;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by abhishek on 19-03-2018.
 */

public class NewsApiHit {

    NewsApiClient newsApiClient;
    NewsEverythingClient newsEverythingClient;
    NewsSourceClient newsSourceClient;
    public String api_key;

    public NewsApiHit(NewsApiClient newsApiClient, NewsEverythingClient newsEverythingClient, NewsSourceClient newsSourceClient, String api_key) {
        this.newsApiClient = newsApiClient;
        this.newsEverythingClient = newsEverythingClient;
        this.newsSourceClient=newsSourceClient;
        this.api_key = api_key;
    }

    public List<NewsModel> fillModelWithTopName() {
        List<NewsModel> newsModels = new ArrayList<>();
        NewsModel newsModel1 = new NewsModel();
        newsModel1.setTopName("Top India");
        newsModel1.setNameId("in");
        NewsModel newsModel2 = new NewsModel();
        newsModel2.setTopName("Top United States");
        newsModel2.setNameId("us");
        NewsModel newsModel3 = new NewsModel();
        newsModel3.setTopName("Top Canada");
        newsModel3.setNameId("ca");
        NewsModel newsModel4 = new NewsModel();
        newsModel4.setTopName("Top Australia");
        newsModel4.setNameId("au");
        NewsModel newsModel5 = new NewsModel();
        newsModel5.setTopName("Top Britain");
        newsModel5.setNameId("gb");

        newsModels.add(newsModel1);
        newsModels.add(newsModel2);
        newsModels.add(newsModel3);
        newsModels.add(newsModel4);
        newsModels.add(newsModel5);

        return newsModels;
    }

    public List<SourceModel> fillModelWithSourcesName()
    {
        List<SourceModel> sourceModels=new ArrayList<>();
        SourceModel sourceModel1=new SourceModel();
        sourceModel1.setTopSourceName("All Entertainment Sources");
        sourceModel1.setSourceId("entertainment");
        SourceModel sourceModel2=new SourceModel();
        sourceModel2.setTopSourceName("All Science Sources");
        sourceModel2.setSourceId("science");
        SourceModel sourceModel3=new SourceModel();
        sourceModel3.setTopSourceName("All General Sources");
        sourceModel3.setSourceId("general");
        SourceModel sourceModel4=new SourceModel();
        sourceModel4.setTopSourceName("All Technology Sources");
        sourceModel4.setSourceId("technology");
        SourceModel sourceModel5=new SourceModel();
        sourceModel5.setTopSourceName("All Business Sources");
        sourceModel5.setSourceId("business");
        SourceModel sourceModel6=new SourceModel();
        sourceModel6.setTopSourceName("All Sports Sources");
        sourceModel6.setSourceId("sports");
        SourceModel sourceModel7=new SourceModel();
        sourceModel7.setTopSourceName("All Health Sources");
        sourceModel7.setSourceId("health");

        sourceModels.add(sourceModel1);
        sourceModels.add(sourceModel2);
        sourceModels.add(sourceModel3);
        sourceModels.add(sourceModel4);
        sourceModels.add(sourceModel5);
        sourceModels.add(sourceModel6);
        sourceModels.add(sourceModel7);
        return sourceModels;
    }


    public Observable<NewsModel> loadNewsDataFromApi(int page, int pageSize, String country, final NewsModel newsModel1) {
        return newsApiClient.getCountryTopHead(country,pageSize,page, api_key)
                .doOnNext(a(newsModel1))
                /*.flatMap(b());*/
                /*.filter(filterForNull())*/;
       // return  Observable.just("abhi","aakash","patta","vinay","shalu","rahul");
    }

    /*private Function<? super NewsModel,? extends ObservableSource<? extends NewsModel>> b() {
        return new Function<NewsModel, ObservableSource<? extends NewsModel>>() {
            @Override
            public ObservableSource<? extends NewsModel> apply(NewsModel newsModel) throws Exception {
                return null;
            }
        };
    }*/

    private Consumer<? super NewsModel> a(final NewsModel newsModel1) {
        return new Consumer<NewsModel>() {
            @Override
            public void accept(NewsModel newsModel) throws Exception {
                if (newsModel.getArticles()==null)
                {
                    newsModel1.setArticles(newsModel.getArticles());
                }
                else
                {
                    newsModel1.getArticles().addAll(newsModel.getArticles());
                }
            }
        };
    }

    public Observable<SourceModel> loadSourcesWithCategory(String category) {
        return newsSourceClient.getSourceData("en",category, api_key)
                /*.filter(filterForNull())*/;
        // return  Observable.just("abhi","aakash","patta","vinay","shalu","rahul");
    }

    public Observable<EverythingModel> loadSearchDataFromApi(String query) {
        return newsEverythingClient.getCountryTopHead(query,"en","popularity",5, api_key);
                /*.filter(filterForNull())*/
        // return  Observable.just("abhi","aakash","patta","vinay","shalu","rahul");
    }


    /*private Predicate<? super CountryTopHeadNews> filterForNull() {
        return new Predicate<CountryTopHeadNews>() {
            @Override
            public boolean test(CountryTopHeadNews countryTopHeadNews) throws Exception {
                return countryTopHeadNews !=null;
            }
        };
    }*/
    /*public Observable<NewsSourceIconDataModel> loadNewsIconFromApi()
    {
        return newsEverythingClient.getNewsIconImg("http://abhishekint.16mb.com/NewsApp/retrieve.php")
                .filter(filterIconForNull());
        *//*return  Observable.just("abhi","aakash","patta","vinay","shalu","rahul");*//*
    }

    private Predicate<? super NewsSourceIconDataModel> filterIconForNull() {
        return new Predicate<NewsSourceIconDataModel>() {
            @Override
            public boolean test(NewsSourceIconDataModel newsSourceIconDataModel) throws Exception {
                return (newsSourceIconDataModel!=null);
            }
        };
    }*/

    }

