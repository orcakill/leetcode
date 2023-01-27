package com.example.demo.model.dto;

/**
 * @Classname IConverter
 * @Description DTO 转换为 PO
 * @Date 2023/1/24 20:09
 * @Created by orcakill
 */
public interface IConverter<T,E>  {
  /**
   * 将对应的 DTO 转换为 PO
   * @param t 需要转换的 DTO 类
   * @return 转换结果
   */
  E convertToPO(T t);
}
