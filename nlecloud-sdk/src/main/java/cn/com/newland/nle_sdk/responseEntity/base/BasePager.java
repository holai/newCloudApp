package cn.com.newland.nle_sdk.responseEntity.base;

import java.util.List;

/**
 * Created by marco on 2017/8/31.
 * 基础分页
 */

public class BasePager<T> {
    private List<T> PageSet;
    private int PageCount;
    private int PageIndex;
    private int PageSize;
    private int RecordCount;

    public int getPageCount() {
        return PageCount;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public int getRecordCount() {
        return RecordCount;
    }

    List<T> getPageSet() {
        return PageSet;
    }
}
