package abhishekint.com.newsappupdate.app.MainActivity;

import android.app.Fragment;

import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.Interater.NewsApiHit;
import abhishekint.com.newsappupdate.app.MainActivity.NewsFragment.PresentationModel.EverythingModel;
import abhishekint.com.newsappupdate.schedulers.AppSchedulerProvider;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by abhishek on 16-03-2018.
 */

public class MainPresenterImpl implements MainPresenter{

    private final MainActivityView mainActivityView;
    NewsApiHit newsApiHit;
    AppSchedulerProvider appSchedulerProvider;
    public MainPresenterImpl(MainActivityView mainActivityView, NewsApiHit newsApiHit, AppSchedulerProvider appSchedulerProvider) {
        this.mainActivityView = mainActivityView;
        this.newsApiHit=newsApiHit;
        this.appSchedulerProvider=appSchedulerProvider;
    }

    @Override
    public void SearchButtonClick(final Fragment fragment, String text,String type) {
        if (text.length()>=3)
        {
            newsApiHit.loadSearchDataFromApi(text)               //here we can save this connection between the observer and
                    .subscribeOn(appSchedulerProvider.io())      //Observable to a DisposableObserver just to unconnect lateron.
                    .observeOn(appSchedulerProvider.ui())
                    .subscribe(new Observer<EverythingModel>() {     //and if we have more than one such connection(hence more than
                        @Override                                    //one DisposableOberver) then we can just store them all in
                        public void onSubscribe(Disposable d) {    //one CompositeDisposable just to unconnect at once.
                        }

                        @Override
                        public void onNext(EverythingModel everythingModel) {
                            mainActivityView.searchCallWithData(fragment,everythingModel);
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
        else if ("textsubmit".equals(type))
        {
            mainActivityView.showToast("write at least 3 charaters....");
        }
    }
}
