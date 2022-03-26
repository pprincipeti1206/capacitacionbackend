package com.incloud.hcp.repository._framework;

import com.incloud.hcp.domain.BaseDomain;

import java.util.List;

public interface JPACustomJdbcDao<T extends BaseDomain> {

    void insertJdbc(final List<T> listaInsert);
    void updateJdbc(final List<T> listaUpdate);

}
