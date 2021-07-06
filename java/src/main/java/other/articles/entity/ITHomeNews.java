package other.articles.entity;

/**
 * @author orcakill
 * @date 2021/1/22  10:55
 **/
public class ITHomeNews {
    private String newsName; /*新闻名称*/
    private Long newsDate; /*新闻时间*/
    private String newsTags; /*新闻标签*/
    private String newsDescribe; /*新闻描述*/
    private String newsSource; /*新闻来源*/
    private String newsIndex; /*新闻网址名称*/
    private String newsHref; /*新闻网址名称*/
    private String newsContent; /*新闻内容*/

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsHref() {
        return newsHref;
    }

    public void setNewsHref(String newsHref) {
        this.newsHref = newsHref;
    }

    public String getNewsIndex() {
        return newsIndex;
    }

    public void setNewsIndex(String newsIndex) {
        this.newsIndex = newsIndex;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public Long getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Long newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsTags() {
        return newsTags;
    }

    public void setNewsTags(String newsTags) {
        this.newsTags = newsTags;
    }

    public String getNewsDescribe() {
        return newsDescribe;
    }

    public void setNewsDescribe(String newsDescribe) {
        this.newsDescribe = newsDescribe;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }


}
