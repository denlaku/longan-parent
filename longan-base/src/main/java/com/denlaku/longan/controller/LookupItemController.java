package com.denlaku.longan.controller;

import com.denlaku.longan.service.LookupItemService;
import com.denlaku.longan.vo.LookupItem;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/lookup-item")
public class LookupItemController {

    @Autowired
    private LookupItemService lookupItemService;

    /**
     * 查询lookupItem
     * @param query 查询参数
     * @return lookupItem列表
     */
    @PostMapping("/list")
    public Return<List<LookupItem>> list(LookupItem query) {
        List<LookupItem> list = lookupItemService.list(query);
        return Resp.success(list);
    }
}
