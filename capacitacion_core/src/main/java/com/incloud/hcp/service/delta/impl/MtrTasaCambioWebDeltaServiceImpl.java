/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/dto/EntitydeltaDTOService.java.e.vm
 */
package com.incloud.hcp.service.delta.impl;

import com.incloud.hcp.domain.MtrTasaCambioWeb;
import com.incloud.hcp.domain.response.MtrTasaCambioWebResponse;
import com.incloud.hcp.service.delta.MtrTasaCambioWebDeltaService;
import com.incloud.hcp.service.impl.MtrTasaCambioWebServiceImpl;
import com.incloud.hcp.service.support.PageRequestByExample;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * A simple DTO Facility for MtrTasaCambioWeb.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MtrTasaCambioWebDeltaServiceImpl extends MtrTasaCambioWebServiceImpl implements MtrTasaCambioWebDeltaService {

    /**************************/
    /* Metodos Personalizados */
    /**************************/

    /***********************/
    /* Metodos de Busqueda */
    /***********************/

    protected Sort setFindAll(Sort sort) {
        return sort;
    }

    protected Sort setFind(MtrTasaCambioWeb req, ExampleMatcher matcher, Example<MtrTasaCambioWeb> example, Sort sort) {
        return sort;
    }

    protected void setFindPaginated(PageRequestByExample<MtrTasaCambioWeb> req, ExampleMatcher matcher, Example<MtrTasaCambioWeb> example) {
        return;
    }

    protected List<Predicate> setAdicionalDeltaPredicate(List<Predicate> predicates, MtrTasaCambioWebResponse bean, CriteriaBuilder cb,
            CriteriaQuery<MtrTasaCambioWeb> query, Root<MtrTasaCambioWeb> root) throws Exception {

        MtrTasaCambioWeb entity = bean.getBean();
        //Ejemplo
        /*
        if (Optional.ofNullable(entity.get<VariableManytoOne>()).isPresent()) {
            Join<MtrTasaCambioWeb, <ClaseManytoOne>> from<ClaseManytoOne> = countRoot.join("<variableManytoOne>", JoinType.INNER);
        
            if (Optional.ofNullable(entity.get<VariableManytoOne>().get<Atributo>()).isPresent()) {
                Join<MtrTasaCambioWeb, <ClaseManytoOne>> from<ClaseManytoOne> = countRoot.join("<variableManytoOne>", JoinType.INNER);
                Predicate thirdCondition = cb.equal(from<ClaseManytoOne>.get("<Atributo>"), entity.get<ClaseManytoOne>().get<Atributo>());
                predicates.add(thirdCondition);
            }
            
        }
        query.orderBy(cb.desc(root.get("<campo entity>")));
        */
        return predicates;
    }

    protected Root<MtrTasaCambioWeb> setAdicionalDeltaTotalPredicate(MtrTasaCambioWebResponse bean, Root<MtrTasaCambioWeb> countRoot) throws Exception {
        MtrTasaCambioWeb entity = bean.getBean();
        //Ejemplo
        /*
        if (Optional.ofNullable(entity.get<VariableManytoOne>()).isPresent()) {
            if (Optional.ofNullable(entity.get<VariableManytoOne>()).isPresent()) {
                Join<MtrTasaCambioWeb, <ClaseManytoOne>> from<ClaseManytoOne> = countRoot.join("<variableManytoOne>", JoinType.INNER);
            }
        }
        */
        return countRoot;
    }

    /****************/
    /* METODOS CRUD */
    /****************/

    protected String setValidacionesPrevias(MtrTasaCambioWeb bean) throws Exception {
        String mensaje = "";
        return mensaje;
    }

    protected MtrTasaCambioWeb setCreate(MtrTasaCambioWeb bean) throws Exception {
        return bean;
    }

    protected void setSave(MtrTasaCambioWeb dto) throws Exception {
        return;
    }

    protected void setDelete(Integer id) throws Exception {

    }

    protected void setDeleteAll() throws Exception {

    }

    /************************/
    /* METODOS CRUD Masivos */
    /************************/

    protected MtrTasaCambioWeb setUploadExcel(Cell currentCell, MtrTasaCambioWeb mtrTasaCambioWeb, int contador) throws Exception {
        mtrTasaCambioWeb = super.setUploadExcel(currentCell, mtrTasaCambioWeb, contador);
        return mtrTasaCambioWeb;
    }

    protected String setSaveMasivo(MtrTasaCambioWeb dto) throws Exception {
        return "";
    }

    protected List<MtrTasaCambioWeb> setBeforeDeleteMasivo(List<MtrTasaCambioWeb> listaDto) throws Exception {
        return listaDto;
    }

    protected void setAfterDeleteMasivo() throws Exception {
        return;
    }

    /*****************************/
    /* Metodos que generan Excel */
    /*****************************/

    protected void setDownloadExcelItem(MtrTasaCambioWeb bean, HSSFRow dataRow) {

    }

    protected void setDownloadExcel(HSSFSheet sheet) {

    }

    /*****************/
    /* Otros Metodos */
    /*****************/

}