package other.articles.entity;

public class Articles {
    private String id; /*主键id*/
    private String author; /*作者*/
    private String category; /*文章分类*/
    private String tabloid; /*文章摘要*/
    private String content; /*文章内容*/
    private String tags; /*标签*/
    private String title; /*文章标题*/
    private int type; /*文章类型*/
    private int views; /*浏览次数*/
    private Integer gmt_create; /*创建时间*/
    private Integer gmt_update; /*更新时间*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTabloid() {
        return tabloid;
    }

    public void setTabloid(String tabloid) {
        this.tabloid = tabloid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Integer getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Integer gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Integer getGmt_update() {
        return gmt_update;
    }

    public void setGmt_update(Integer gmt_update) {
        this.gmt_update = gmt_update;
    }
}
