package com.example.demo.model.vo;

/**
 * @Classname IConverter
 * @Description VO 转换函数
 * @Date 2023/1/24 19:27
 * @Created by orcakill
 */
public interface IConverter<T,E> {
  /**
   * VO 转换函数
   *
   * @param t 目标对象
   * @return 转换结果
   */
  E convertToVO(T t);
}

