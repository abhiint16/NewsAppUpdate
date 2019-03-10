package abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Adapter.SubAdapter;

import java.util.List;

import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationModel.NewsModel;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationModel.SourceModel;

/**
 * Created by abhishek on 10-04-2018.
 */

public interface ViewLayer {
    void getAllTopName(List<NewsModel> newsModels);
    void getAllSourcesName(List<SourceModel> sourceModels);
    void notifyExperiment();
    void setIndiaNews();
}
