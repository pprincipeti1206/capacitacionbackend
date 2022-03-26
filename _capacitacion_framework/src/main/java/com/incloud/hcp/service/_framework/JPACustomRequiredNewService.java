package com.incloud.hcp.service._framework;

import com.incloud.hcp.domain.BaseDomain;

import java.util.List;

public interface JPACustomRequiredNewService<T extends BaseDomain> {

    T saveMasivo(T bean) throws Exception;

    void deleteMasivo(T bean) throws Exception;

    void updateMasivo(T bean) throws Exception;

    void insertMasivo(List<T> listaBean) throws Exception;

    void deleteAll() throws Exception;

}
