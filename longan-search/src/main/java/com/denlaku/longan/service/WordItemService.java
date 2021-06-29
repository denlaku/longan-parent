package com.denlaku.longan.service;

import com.denlaku.longan.dao.WordItemDao;
import com.denlaku.longan.util.Strings;
import com.denlaku.longan.vo.WordItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianx
 */
@Service
public class WordItemService {

    @Autowired
    private WordItemDao wordItemDao;

    @Autowired
    private IndexService indexService;

    /**
     * 添加词条
     *
     * @param wordItem 参数
     * @return 添加行数
     */
    public int add(WordItem wordItem) {
        String text = wordItem.getText();
        if (Strings.isEmpty(text)) {
            return 0;
        }
        wordItem.setText(text.trim());
        WordItem old = this.get(wordItem);
        if (old != null) {
            return 0;
        }
        int add = wordItemDao.add(wordItem);
        indexService.loadWordItems(wordItem.getTenantId());
        return add;
    }

    /**
     * 删除词条
     *
     * @param wordItem 参数
     * @return 删除行数
     */
    public int delete(WordItem wordItem) {
        return wordItemDao.delete(wordItem);
    }

    /**
     * 获取词条
     *
     * @param wordItem 参数
     * @return 词条
     */
    public WordItem get(WordItem wordItem) {
        return wordItemDao.get(wordItem);
    }

    /**
     * 查询词条列表
     *
     * @param wordItem 查询参数
     * @return list
     */
    public List<WordItem> list(WordItem wordItem) {
        return wordItemDao.list(wordItem);
    }

}
