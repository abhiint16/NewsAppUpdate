package abhishekint.com.newsappupdate.app.MainActivity.NewsFragment;

/**
 * Created by abhishek on 19-03-2018.
 */

public interface NewsFragMethodCall {
    void interfaceOnNextCall(NewsSourceDataModel value);
    void interfaceOnIconNextCall(NewsSourceIconDataModel value,NewsSourceDataModel newsSourceDataModel);
}
