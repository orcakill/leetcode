package com.example.demo.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 游戏附录信息
 * @TableName game_annex
 */
@TableName(value ="game_annex")
@Data
public class GameAnnexPO implements Serializable {
    /**
     * 代码
     */
    @TableId(value = "code")
    private String code;

    /**
     * 父代码
     */
    @TableId(value = "parent_code")
    private String parentCode;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 序号
     */
    @TableField(value = "number")
    private Integer number;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        GameAnnexPO other = (GameAnnexPO) that;
        return (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getParentCode() == null ? other.getParentCode() == null : this.getParentCode().equals(other.getParentCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getParentCode() == null) ? 0 : getParentCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass ().getSimpleName () +
               " [" +
               "Hash = " + hashCode () +
               ", code=" + code +
               ", parentCode=" + parentCode +
               ", name=" + name +
               ", number=" + number +
               ", serialVersionUID=" + serialVersionUID +
               "]";
    }
}