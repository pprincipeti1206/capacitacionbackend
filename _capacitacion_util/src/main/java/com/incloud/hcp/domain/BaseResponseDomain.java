package com.incloud.hcp.domain;

import com.incloud.hcp.support.PageRequestCondicion;

public class BaseResponseDomain<T extends BaseDomain> {

    protected T bean;
    protected PageRequestCondicion pageRequest;

    public void setBean(T bean) {
        this.bean = bean;
    }

    public T getBean() {
        return this.bean;
    }

    public PageRequestCondicion getPageRequest() {
        return pageRequest;
    }

    public void setPageRequest(PageRequestCondicion pageRequest) {
        this.pageRequest = pageRequest;
    }

}
