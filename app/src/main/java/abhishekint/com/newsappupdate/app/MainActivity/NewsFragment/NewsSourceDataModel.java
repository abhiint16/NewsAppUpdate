package abhishekint.com.newsappupdate.app.MainActivity.NewsFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 20-03-2018.
 */

public class NewsSourceDataModel {
    @SerializedName("server_response")
    private List<NewsIconInner> server_response;

    public List<NewsIconInner> getServer_response() {
        return server_response;
    }

    public void setServer_response(List<NewsIconInner> server_response) {
        this.server_response = server_response;
    }

    public class NewsIconInner
    {
        @SerializedName("img_id")
        private String img_id;

        public String getImg_id() {
            return img_id;
        }

        public void setImg_id(String img_id) {
            this.img_id = img_id;
        }
    }

    @SerializedName("sources")
    private List<NewsInner> sources;

    public List<NewsInner> getSources() {
        return sources;
    }

    public void setSources(List<NewsInner> sources) {
        this.sources = sources;
    }

    public class NewsInner
    {
        @SerializedName("id")
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
