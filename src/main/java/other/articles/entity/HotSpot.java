package other.articles.entity;

/**
 * @author orcakill
 * @date 2021/1/19  13:42
 **/
public class HotSpot {
    String  hotSpotName; /*热点名称*/
    String  hotSpotWeb;  /*热点网址名称*/
    Integer hotSpotRank; /*热点排行*/
    Integer hotSpotDegree; /*热点热度*/

    public String getHotSpotName() {
        return hotSpotName;
    }

    public void setHotSpotName(String hotSpotName) {
        this.hotSpotName = hotSpotName;
    }

    public String getHotSpotWeb() {
        return hotSpotWeb;
    }

    public void setHotSpotWeb(String hotSpotWeb) {
        this.hotSpotWeb = hotSpotWeb;
    }

    public Integer getHotSpotRank() {
        return hotSpotRank;
    }

    public void setHotSpotRank(Integer hotSpotRank) {
        this.hotSpotRank = hotSpotRank;
    }

    public Integer getHotSpotDegree() {
        return hotSpotDegree;
    }

    public void setHotSpotDegree(Integer hotSpotDegree) {
        this.hotSpotDegree = hotSpotDegree;
    }
}
