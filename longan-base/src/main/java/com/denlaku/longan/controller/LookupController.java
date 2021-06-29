package com.denlaku.longan.controller;

import com.denlaku.longan.Current;
import com.denlaku.longan.service.LookupService;
import com.denlaku.longan.vo.Lookup;
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
@RequestMapping("/api/lookup")
public class LookupController {

    @Autowired
    private LookupService lookupService;

    /**
     * 查询lookup
     * @param query 查询参数
     * @return lookup列表
     */
    @PostMapping("/page-list")
    public Return<List<Lookup>> pageList(Lookup query, PageBy pageBy) {
        query.setTenantId(Current.getTenantId());
        PageHelper.startPage(pageBy.getPage(), pageBy.getSize());
        List<Lookup> list = lookupService.list(query);
        return Resp.success(list);
    }

    /**
     * 获取lookup
     * @param id
     * @return Lookup
     */
    @PostMapping("/get")
    public Return<Lookup> get(String id) {
        Lookup query = new Lookup();
        query.setId(id);
        query.setTenantId(Current.getTenantId());
        Lookup lookup = lookupService.get(query);
        return Resp.success(lookup);
    }

    /**
     * 新增lookup
     * @param lookup
     * @return 新增行数
     */
    @PostMapping("/add")
    Return<Void> add(@RequestBody Lookup lookup) {
        lookupService.add(lookup);
        return Resp.success();
    }

    /**
     * 更新lookup
     * @param lookup lookup
     * @return 更新行数
     */
    @PostMapping("/update")
    public Integer update(@RequestBody Lookup lookup) {
        return lookupService.update(lookup);
    }

    /**
     * 删除lookup
     * @param lookup 参数
     * @return 删除行数
     */
    @PostMapping("/delete")
    public Integer delete(Lookup lookup) {
        return lookupService.delete(lookup);
    }
}
