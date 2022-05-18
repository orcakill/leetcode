package other.articles.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("articles")
public class ArticlePO implements Serializable {
    private static final long serialVersionUID = -1849698844197610571L;
    @TableId
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




}
