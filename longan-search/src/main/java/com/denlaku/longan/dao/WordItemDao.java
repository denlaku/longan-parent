package com.denlaku.longan.dao;

import com.denlaku.longan.vo.WordItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tianx
 */
@Repository
public interface WordItemDao {

    /**
     * 添加词条
     *
     * @param wordItem 参数
     * @return 添加行数
     */
    int add(WordItem wordItem);

    /**
     * 删除词条
     *
     * @param wordItem 参数
     * @return 删除行数
     */
    int delete(WordItem wordItem);

    /**
     * 获取词条
     *
     * @param wordItem 参数
     * @return 词条
     */
    WordItem get(WordItem wordItem);

    /**
     * 查询词条列表
     *
     * @param wordItem 查询参数
     * @return list
     */
    List<WordItem> list(WordItem wordItem);

}
