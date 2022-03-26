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

import com.incloud.hcp.domain.MtrEstrategiaFacturacion;
import com.incloud.hcp.domain.MtrEstrategiaFacturacionFirma;
import com.incloud.hcp.domain.MtrSector;
import com.incloud.hcp.domain.MtrTipoGerencia;
import com.incloud.hcp.domain.response.MtrEstrategiaFacturacionFirmaResponse;
import com.incloud.hcp.service.delta.MtrEstrategiaFacturacionFirmaDeltaService;
import com.incloud.hcp.service.impl.MtrEstrategiaFacturacionFirmaServiceImpl;
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
 * A simple DTO Facility for MtrEstrategiaFacturacionFirma.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MtrEstrategiaFacturacionFirmaDeltaServiceImpl extends MtrEstrategiaFacturacionFirmaServiceImpl
        implements MtrEstrategiaFacturacionFirmaDeltaService {

    /**************************/
    /* Metodos Personalizados */
    /**************************/

    /***********************/
    /* Metodos de Busqueda */
    /***********************/

    protected Sort setFindAll(Sort sort) {
        return sort;
    }

    protected Sort setFind(MtrEstrategiaFacturacionFirma req, ExampleMatcher matcher, Example<MtrEstrategiaFacturacionFirma> example, Sort sort) {
        return sort;
    }

    protected void setFindPaginated(PageRequestByExample<MtrEstrategiaFacturacionFirma> req, ExampleMatcher matcher,
            Example<MtrEstrategiaFacturacionFirma> example) {
        return;
    }

    protected List<Predicate> setAdicionalDeltaPredicate(List<Predicate> predicates, MtrEstrategiaFacturacionFirmaResponse bean, CriteriaBuilder cb,
            CriteriaQuery<MtrEstrategiaFacturacionFirma> query, Root<MtrEstrategiaFacturacionFirma> root) throws Exception {

        MtrEstrategiaFacturacionFirma entity = bean.getBean();
        //Ejemplo
        /*
        if (Optional.ofNullable(entity.get<VariableManytoOne>()).isPresent()) {
            Join<MtrEstrategiaFacturacionFirma, <ClaseManytoOne>> from<ClaseManytoOne> = countRoot.join("<variableManytoOne>", JoinType.INNER);
        
            if (Optional.ofNullable(entity.get<VariableManytoOne>().get<Atributo>()).isPresent()) {
                Join<MtrEstrategiaFacturacionFirma, <ClaseManytoOne>> from<ClaseManytoOne> = countRoot.join("<variableManytoOne>", JoinType.INNER);
                Predicate thirdCondition = cb.equal(from<ClaseManytoOne>.get("<Atributo>"), entity.get<ClaseManytoOne>().get<Atributo>());
                predicates.add(thirdCondition);
            }
            
        }
        query.orderBy(cb.desc(root.get("<campo entity>")));
        */
        return predicates;
    }

    protected Root<MtrEstrategiaFacturacionFirma> setAdicionalDeltaTotalPredicate(MtrEstrategiaFacturacionFirmaResponse bean,
            Root<MtrEstrategiaFacturacionFirma> countRoot) throws Exception {
        MtrEstrategiaFacturacionFirma entity = bean.getBean();
        //Ejemplo
        /*
        if (Optional.ofNullable(entity.get<VariableManytoOne>()).isPresent()) {
            if (Optional.ofNullable(entity.get<VariableManytoOne>()).isPresent()) {
                Join<MtrEstrategiaFacturacionFirma, <ClaseManytoOne>> from<ClaseManytoOne> = countRoot.join("<variableManytoOne>", JoinType.INNER);
            }
        }
        */
        return countRoot;
    }

    /****************/
    /* METODOS CRUD */
    /****************/

    protected String setValidacionesPrevias(MtrEstrategiaFacturacionFirma bean) throws Exception {
        String mensaje = "";
        return mensaje;
    }

    protected MtrEstrategiaFacturacionFirma setCreate(MtrEstrategiaFacturacionFirma bean) throws Exception {
        return bean;
    }

    protected void setSave(MtrEstrategiaFacturacionFirma dto) throws Exception {
        return;
    }

    protected void setDelete(Integer id) throws Exception {

    }

    protected void setDeleteAll() throws Exception {

    }

    /************************/
    /* METODOS CRUD Masivos */
    /************************/

    protected MtrEstrategiaFacturacionFirma setUploadExcel(Cell currentCell, MtrEstrategiaFacturacionFirma mtrEstrategiaFacturacionFirma, int contador)
            throws Exception {
        mtrEstrategiaFacturacionFirma = super.setUploadExcel(currentCell, mtrEstrategiaFacturacionFirma, contador);
        return mtrEstrategiaFacturacionFirma;
    }

    protected String setSaveMasivo(MtrEstrategiaFacturacionFirma dto) throws Exception {
        return "";
    }

    protected List<MtrEstrategiaFacturacionFirma> setBeforeDeleteMasivo(List<MtrEstrategiaFacturacionFirma> listaDto) throws Exception {
        return listaDto;
    }

    protected void setAfterDeleteMasivo() throws Exception {
        return;
    }

    /*****************************/
    /* Metodos que generan Excel */
    /*****************************/

    protected void setDownloadExcelItem(MtrEstrategiaFacturacionFirma bean, HSSFRow dataRow) {

    }

    protected void setDownloadExcel(HSSFSheet sheet) {

    }

    /*****************/
    /* Otros Metodos */
    /*****************/

    protected String setGraphDescripcionByMtrEstrategiaFacturacion(MtrEstrategiaFacturacion mtrEstrategiaFacturacion) {
        return mtrEstrategiaFacturacion.getId().toString();
    }

    protected String setGraphPieChartTituloByMtrEstrategiaFacturacion() {
        return "MtrEstrategiaFacturacion";
    }

    protected String setGraphBarChartTituloByMtrEstrategiaFacturacion() {
        return "MtrEstrategiaFacturacion";
    }

    protected String setGraphDescripcionByMtrTipoGerencia(MtrTipoGerencia mtrTipoGerencia) {
        return mtrTipoGerencia.getId().toString();
    }

    protected String setGraphPieChartTituloByMtrTipoGerencia() {
        return "MtrTipoGerencia";
    }

    protected String setGraphBarChartTituloByMtrTipoGerencia() {
        return "MtrTipoGerencia";
    }

    protected String setGraphDescripcionByMtrSector(MtrSector mtrSector) {
        return mtrSector.getId().toString();
    }

    protected String setGraphPieChartTituloByMtrSector() {
        return "MtrSector";
    }

    protected String setGraphBarChartTituloByMtrSector() {
        return "MtrSector";
    }

}