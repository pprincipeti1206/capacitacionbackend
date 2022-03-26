package com.incloud.hcp.service._framework;

import com.incloud.hcp.domain.BaseDomain;
import com.incloud.hcp.domain.BaseResponseDomain;
import com.incloud.hcp.service._framework.bean.BeanCargaMasivoDTO;
import com.incloud.hcp.service._framework.bean.BeanListaMasivoDTO;
import com.incloud.hcp.service.support.PageRequestByExample;
import com.incloud.hcp.service.support.PageResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.data.domain.PageRequest;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface JPACustomService<R extends BaseResponseDomain, T extends BaseDomain, I> {

    /* Metodos de Busqueda */
    List<T> findMybatis(T req);
    List<T> findAll();
    List<T> find(T req);
    List<T> findCondicion(R req) throws Exception;
    List<T> findCondicionVersion02(R req) throws Exception;
    PageResponse<T> findCondicionPaginated(R req, PageRequest pageRequest) throws Exception;
    Optional<T> findOne(I id);
    PageResponse<T> findPaginated(PageRequestByExample<T> req);

    /* Metodos que generan Excel */
    HSSFWorkbook downloadExcel(T req);
    SXSSFWorkbook downloadExcelSXLSX(T req);
    SXSSFWorkbook generateInsertExcelSXLSX(T req);

    /* Metodos CRUD */
    T create(T dto) throws Exception;
    T save(T dto) throws Exception;
    List<T> saveAll(List<T> lista) throws Exception;
    void delete(I id) throws Exception;
    void deleteAll() throws Exception;


    /* Metodos CRUD Masivos */
    List<BeanCargaMasivoDTO<T>> uploadExcel(InputStream in) throws Exception;
    BeanListaMasivoDTO<BeanCargaMasivoDTO<T>> saveAllNewRequired(List<T> listaBean) throws Exception;
    BeanListaMasivoDTO<BeanCargaMasivoDTO<T>> deleteAllNewRequired(List<T> listaBean) throws Exception;

    PageResponse<T> findAllPaginatedSortCreatedDate(int page, int size);



}
