package com.denlaku.longan.vo;

import com.denlaku.longan.util.Lists;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author tianx
 */
@Data
public class CustomApiData {
    private boolean status;
    private String id;
    private List<Map<String, Object>> rows;
    private String sql;
    private List<Object> params;
    private List<Object> debugs;
    public void addDebug(Object debug) {
        if (debugs == null) {
            debugs = Lists.of();
        }
        debugs.add(debug);
    }
}
