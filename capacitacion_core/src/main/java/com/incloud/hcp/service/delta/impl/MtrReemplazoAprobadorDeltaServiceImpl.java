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

import com.incloud.hcp.domain.MtrAprobador;
import com.incloud.hcp.domain.MtrReemplazoAprobador;
import com.incloud.hcp.domain.response.MtrReemplazoAprobadorResponse;
import com.incloud.hcp.service.delta.MtrReemplazoAprobadorDeltaService;
import com.incloud.hcp.service.impl.MtrReemplazoAprobadorServiceImpl;
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
 * A simple DTO Facility for MtrReemplazoAprobador.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MtrReemplazoAprobadorDeltaServiceImpl extends MtrReemplazoAprobadorServiceImpl implements MtrReemplazoAprobadorDeltaService {

    /**************************/
    /* Metodos Personalizados */
    /**************************/

    /***********************/
    /* Metodos de Busqueda */
    /***********************/

    protected Sort setFindAll(Sort sort) {
        return sort;
    }

    protected Sort setFind(MtrReemplazoAprobador req, ExampleMatcher matcher, Example<MtrReemplazoAprobador> example, Sort sort) {
        return sort;
    }

    protected void setFindPaginated(PageRequestByExample<MtrReemplazoAprobador> req, ExampleMatcher matcher, Example<MtrReemplazoAprobador> example) {
        return;
    }

    protected List<Predicate> setAdicionalDeltaPredicate(List<Predicate> predicates, MtrReemplazoAprobadorResponse bean, CriteriaBuilder cb,
            CriteriaQuery<MtrReemplazoAprobador> query, Root<MtrReemplazoAprobador> root) throws Exception {

        MtrReemplazoAprobador entity = bean.getBean();
        //Ejemplo
        /*
        if (Optional.ofNullable(entity.get<VariableManytoOne>()).isPresent()) {
            Join<MtrReemplazoAprobador, <ClaseManytoOne>> from<ClaseManytoOne> = countRoot.join("<variableManytoOne>", JoinType.INNER);
        
            if (Optional.ofNullable(entity.get<VariableManytoOne>().get<Atributo>()).isPresent()) {
                Join<MtrReemplazoAprobador, <ClaseManytoOne>> from<ClaseManytoOne> = countRoot.join("<variableManytoOne>", JoinType.INNER);
                Predicate thirdCondition = cb.equal(from<ClaseManytoOne>.get("<Atributo>"), entity.get<ClaseManytoOne>().get<Atributo>());
                predicates.add(thirdCondition);
            }
            
        }
        query.orderBy(cb.desc(root.get("<campo entity>")));
        */
        return predicates;
    }

    protected Root<MtrReemplazoAprobador> setAdicionalDeltaTotalPredicate(MtrReemplazoAprobadorResponse bean, Root<MtrReemplazoAprobador> countRoot)
            throws Exception {
        MtrReemplazoAprobador entity = bean.getBean();
        //Ejemplo
        /*
        if (Optional.ofNullable(entity.get<VariableManytoOne>()).isPresent()) {
            if (Optional.ofNullable(entity.get<VariableManytoOne>()).isPresent()) {
                Join<MtrReemplazoAprobador, <ClaseManytoOne>> from<ClaseManytoOne> = countRoot.join("<variableManytoOne>", JoinType.INNER);
            }
        }
        */
        return countRoot;
    }

    /****************/
    /* METODOS CRUD */
    /****************/

    protected String setValidacionesPrevias(MtrReemplazoAprobador bean) throws Exception {
        String mensaje = "";
        return mensaje;
    }

    protected MtrReemplazoAprobador setCreate(MtrReemplazoAprobador bean) throws Exception {
        return bean;
    }

    protected void setSave(MtrReemplazoAprobador dto) throws Exception {
        return;
    }

    protected void setDelete(Integer id) throws Exception {

    }

    protected void setDeleteAll() throws Exception {

    }

    /************************/
    /* METODOS CRUD Masivos */
    /************************/

    protected MtrReemplazoAprobador setUploadExcel(Cell currentCell, MtrReemplazoAprobador mtrReemplazoAprobador, int contador) throws Exception {
        mtrReemplazoAprobador = super.setUploadExcel(currentCell, mtrReemplazoAprobador, contador);
        return mtrReemplazoAprobador;
    }

    protected String setSaveMasivo(MtrReemplazoAprobador dto) throws Exception {
        return "";
    }

    protected List<MtrReemplazoAprobador> setBeforeDeleteMasivo(List<MtrReemplazoAprobador> listaDto) throws Exception {
        return listaDto;
    }

    protected void setAfterDeleteMasivo() throws Exception {
        return;
    }

    /*****************************/
    /* Metodos que generan Excel */
    /*****************************/

    protected void setDownloadExcelItem(MtrReemplazoAprobador bean, HSSFRow dataRow) {

    }

    protected void setDownloadExcel(HSSFSheet sheet) {

    }

    /*****************/
    /* Otros Metodos */
    /*****************/

    protected String setGraphDescripcionByMtrAprobador(MtrAprobador mtrAprobador) {
        return mtrAprobador.getId().toString();
    }

    protected String setGraphPieChartTituloByMtrAprobador() {
        return "MtrAprobador";
    }

    protected String setGraphBarChartTituloByMtrAprobador() {
        return "MtrAprobador";
    }



}
