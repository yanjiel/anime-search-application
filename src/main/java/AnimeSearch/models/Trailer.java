
package AnimeSearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Trailer {

    @SerializedName("youtube_id")
    @Expose
    private Object youtubeId;
    @SerializedName("url")
    @Expose
    private Object url;
    @SerializedName("embed_url")
    @Expose
    private Object embedUrl;
    @SerializedName("images")
    @Expose
    private TrailerImage images;

    public Object getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(Object youtubeId) {
        this.youtubeId = youtubeId;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public Object getEmbedUrl() {
        return embedUrl;
    }

    public void setEmbedUrl(Object embedUrl) {
        this.embedUrl = embedUrl;
    }

    public TrailerImage getImages() {
        return images;
    }

    public void setImages(TrailerImage images) {
        this.images = images;
    }

}
