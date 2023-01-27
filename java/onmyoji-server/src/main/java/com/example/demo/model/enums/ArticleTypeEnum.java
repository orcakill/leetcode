package com.example.demo.model.enums;

/**
 * @Classname ArticleTypeEnum
 * @Description 文章类型
 * @Date 2023/1/24 19:39
 * @Created by orcakill
 */
public enum ArticleTypeEnum {
  /**
   * 文章类型：原创
   */
  ORIGINAL("原创", 1),
  /**
   * 文章类型：转载
   */
  REPRINT("转载", 0);
	private final String notes;
  private final int flag;

  public String getNotes() {
    return notes;
  }

  public int getFlag() {
    return flag;
  }

  ArticleTypeEnum(String notes, int flag) {
    this.notes = notes;
    this.flag = flag;
  }
}
