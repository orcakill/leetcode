package other.articles.model.dto;

import lombok.Data;

@Data
public class ArticleDTO {
    private String id;
    private String author;
    private String category;
    private String tabloid;
    private String content;
    private String tags;
    private String title;
    private Integer type;
    private Integer views;
    private Long gmtCreate;
    private Long gmtUpdate;
    private String webAddress;
}
