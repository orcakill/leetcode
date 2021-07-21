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
    private String title;
    private String content;
    private String tags;
    private Integer type;
    private String category;
    private Long gmtCreate;
    private Long gmtUpdate;
    private String tabloid;
    private Integer views;


}
