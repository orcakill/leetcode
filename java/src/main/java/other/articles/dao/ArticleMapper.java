package other.articles.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import other.articles.entity.Articles;

/**
 * @author orcakill
 * @date 2021/2/25  11:55
 **/
@Mapper
public interface ArticleMapper extends BaseMapper<Articles> {

}
