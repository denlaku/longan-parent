package com.denlaku.longan.controller;

import com.denlaku.longan.qo.BizTagQuery;
import com.denlaku.longan.service.BizTagService;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.denlaku.longan.vo.BizTag;
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
@RequestMapping("/api/biz-tag")
public class BizTagController {
    @Autowired
    private BizTagService bizTagService;

    /**
     * 添加tag
     * @param bizTag tag
     * @return 添加行数
     */
    @PostMapping("/add")
    public Return<Void> add(@RequestBody BizTag bizTag) {
        bizTagService.add(bizTag);
        return Resp.success();
    }

    /**
     * 删除tag
     * @param query 参数
     * @return 删除行数
     */
    @PostMapping("/delete")
    public Return<Void> delete(BizTagQuery query) {
        bizTagService.delete(query);
        return Resp.success();
    }

    /**
     * 查询tag
     * @param query 参数
     * @return tag列表
     */
    @PostMapping("/list")
    public Return<List<BizTag>> list(@RequestBody BizTagQuery query) {
        List<BizTag> list = bizTagService.list(query);
        return Resp.success(list);
    }
}
