package com.incloud.hcp.repository._framework;

import com.incloud.hcp.domain.BaseDomain;

import java.util.List;
import java.util.Map;

public interface JPACustomMapperMybatis<T extends BaseDomain> {

    List<T> getLista(T bean);

    void insertBean(T bean);

    void insertMap(Map<String, Object> list);

    void updateBean(T bean);

    void deleteBean(T bean);

    void deleteAll();
}
