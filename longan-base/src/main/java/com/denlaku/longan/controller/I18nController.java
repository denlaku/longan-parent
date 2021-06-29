package com.denlaku.longan.controller;

import com.denlaku.longan.service.I18nService;
import com.denlaku.longan.vo.I18n;
import com.denlaku.longan.vo.PageBy;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/i18n")
public class I18nController {

    @Autowired
    private I18nService i18nService;

    /**
     * 新增国际化
     * @param i18n
     * @return 新增条数
     */
    @PostMapping("/add")
    public Return<Void> add(@RequestBody I18n i18n) {
        i18nService.add(i18n);
        return Resp.success();
    }

    /**
     * 删除国际化
     * @param query
     * @return 删除条数
     */
    @PostMapping("/delete")
    public Return<Void> delete(I18n query) {
        i18nService.delete(query);
        return Resp.success();
    }

    /**
     * 更新国际化
     * @param i18n
     * @return 更新条数
     */
    @PostMapping("/update")
    public Return<Void> update(@RequestBody I18n i18n) {
        i18nService.update(i18n);
        return Resp.success();
    }

    /**
     * 查询国际化
     * @param query
     * @return i18n列表
     */
    @PostMapping("/list")
    public Return<List<I18n>> pageList(@RequestBody I18n query, PageBy pageBy) {
        PageHelper.startPage(pageBy.getPage(), pageBy.getSize());
        List<I18n> list = i18nService.list(query);
        return Resp.success(list);
    }

    /**
     * 获取国际化
     * @param query
     * @return i18n
     */
    @PostMapping("/get")
    public Return<I18n> get(I18n query) {
        I18n i18n = i18nService.get(query);
        return Resp.success(i18n);
    }
}
