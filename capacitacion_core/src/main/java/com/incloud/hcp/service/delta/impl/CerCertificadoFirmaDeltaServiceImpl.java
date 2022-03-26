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

import com.incloud.hcp._security.UserSession;
import com.incloud.hcp.domain.*;
import com.incloud.hcp.domain.response.CerCertificadoFirmaResponse;
import com.incloud.hcp.repository.delta.MtrAprobadorDeltaRepository;
import com.incloud.hcp.repository.delta.MtrEstadoDeltaRepository;
import com.incloud.hcp.repository.delta.MtrTipoFirmaDeltaRepository;
import com.incloud.hcp.service.delta.CerCertificadoFirmaDeltaService;
import com.incloud.hcp.service.dto.CerCertificadoFirmaEntradaDto;
import com.incloud.hcp.service.impl.CerCertificadoFirmaServiceImpl;
import com.incloud.hcp.service.support.PageRequestByExample;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

/**
 * A simple DTO Facility for CerCertificadoFirma.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CerCertificadoFirmaDeltaServiceImpl extends CerCertificadoFirmaServiceImpl implements CerCertificadoFirmaDeltaService {

    @Autowired
    private MtrAprobadorDeltaRepository mtrAprobadorDeltaRepository;

    @Autowired
    private MtrEstadoDeltaRepository mtrEstadoDeltaRepository;

    @Autowired
    private MtrTipoFirmaDeltaRepository mtrTipoFirmaDeltaRepository;

    /**************************/
    /* Metodos Personalizados */
    /**************************/

    public List<CerCertificadoFirma> findByPorAprobador(CerCertificadoFirmaEntradaDto cerCertificadoFirmaEntradaDto) throws Exception {
        UserSession userSession = cerCertificadoFirmaEntradaDto.getUserSession();
        List<MtrAprobador> mtrAprobadorList = this.mtrAprobadorDeltaRepository.findByCodigoIdp(userSession.getId());
        if (mtrAprobadorList == null || mtrAprobadorList.size() <= 0) {
            throw new Exception("No se encontró Aprobador con Código IDP: " + userSession.getId());
        }
        log.error("Ingresando findByPorAprobador mtrAprobadorList size:" + mtrAprobadorList.size());
        MtrAprobador mtrAprobador = mtrAprobadorList.get(0);
        log.error("Ingresando findByPorAprobador mtrAprobadorList :" + mtrAprobador.toString());

        MtrEstado mtrEstado = this.mtrEstadoDeltaRepository.getByCodigoAgrupadoAndCodigoEstado(
                cerCertificadoFirmaEntradaDto.getCodigoAgrupadoEstado(),
                cerCertificadoFirmaEntradaDto.getCodigoEstado());
        if (!Optional.ofNullable(mtrEstado).isPresent()) {
            throw new Exception(
                    "No se encontró Estado con Código Agrupado: " +
                            cerCertificadoFirmaEntradaDto.getCodigoAgrupadoEstado() +
                            " y Código Estado: " + cerCertificadoFirmaEntradaDto.getCodigoEstado()
            );
        }
        log.error("Ingresando findByPorAprobador mtrEstado :" + mtrEstado.toString());

        MtrTipoFirma mtrTipoFirma = this.mtrTipoFirmaDeltaRepository.
                getByCodigoTipoFirma(cerCertificadoFirmaEntradaDto.getCodigoTipoFirma());
        if (!Optional.ofNullable(mtrTipoFirma).isPresent()) {
            throw new Exception("No se ingresó Tipo de Firma con Código: " + cerCertificadoFirmaEntradaDto.getCodigoTipoFirma());
        }
        log.error("Ingresando findByPorAprobador mtrTipoFirma :" + mtrTipoFirma.toString());

        List<CerCertificadoFirma> cerCertificadoFirmaList = this.cerCertificadoFirmaDeltaRepository.findByAprobador(
                mtrAprobador.getId(),
                mtrEstado.getId(),
                mtrTipoFirma.getId()
        );
        log.error("Ingresando findByPorAprobador cerCertificadoFirmaList size:" + cerCertificadoFirmaList.size());
        log.error("Ingresando findByPorAprobador cerCertificadoFirmaList :" + cerCertificadoFirmaList.toString());
        return cerCertificadoFirmaList;
    }

    /***********************/
    /* Metodos de Busqueda */
    /***********************/

    protected Sort setFindAll(Sort sort) {
        sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "descripcion"));
        return sort;
    }

    protected Sort setFind(CerCertificadoFirma req, ExampleMatcher matcher, Example<CerCertificadoFirma> example, Sort sort) {
        sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "descripcion"));
        return sort;
    }

    protected void setFindPaginated(PageRequestByExample<CerCertificadoFirma> req, ExampleMatcher matcher, Example<CerCertificadoFirma> example) {
        return;
    }

    protected List<Predicate> setAdicionalDeltaPredicate(List<Predicate> predicates, CerCertificadoFirmaResponse bean, CriteriaBuilder cb,
                                                         CriteriaQuery<CerCertificadoFirma> query, Root<CerCertificadoFirma> root) {

        CerCertificadoFirma entity = bean.getBean();
        //Ejemplo
        /*
        if (Optional.ofNullable(entity.get<VariableManytoOne>()).isPresent()) {
            Join<CerCertificadoFirma, <ClaseManytoOne>> from<ClaseManytoOne> = countRoot.join("<variableManytoOne>", JoinType.INNER);

            if (Optional.ofNullable(entity.get<VariableManytoOne>().get<Atributo>()).isPresent()) {
                Join<CerCertificadoFirma, <ClaseManytoOne>> from<ClaseManytoOne> = countRoot.join("<variableManytoOne>", JoinType.INNER);
                Predicate thirdCondition = cb.equal(from<ClaseManytoOne>.get("<Atributo>"), entity.get<ClaseManytoOne>().get<Atributo>());
                predicates.add(thirdCondition);
            }

        }
        query.orderBy(cb.desc(root.get("<campo entity>")));
        */
        return predicates;
    }

    protected Root<CerCertificadoFirma> setAdicionalDeltaTotalPredicate(CerCertificadoFirmaResponse bean, Root<CerCertificadoFirma> countRoot) {
        CerCertificadoFirma entity = bean.getBean();
        //Ejemplo
        /*
        if (Optional.ofNullable(entity.get<VariableManytoOne>()).isPresent()) {
            if (Optional.ofNullable(entity.get<VariableManytoOne>()).isPresent()) {
                Join<CerCertificadoFirma, <ClaseManytoOne>> from<ClaseManytoOne> = countRoot.join("<variableManytoOne>", JoinType.INNER);
            }
        }
        */
        return countRoot;
    }

    /****************/
    /* METODOS CRUD */
    /****************/

    protected String setValidacionesPrevias(CerCertificadoFirma bean) throws Exception {
        String mensaje = "";
        return mensaje;
    }

    protected CerCertificadoFirma setCreate(CerCertificadoFirma bean) throws Exception {
        return bean;
    }

    protected void setSave(CerCertificadoFirma dto) throws Exception {
        return;
    }

    protected void setDelete(Integer id) throws Exception {

    }

    protected void setDeleteAll() throws Exception {

    }

    /************************/
    /* METODOS CRUD Masivos */
    /************************/

    protected CerCertificadoFirma setUploadExcel(Cell currentCell, CerCertificadoFirma cerCertificadoFirma, int contador) throws Exception {
        cerCertificadoFirma = super.setUploadExcel(currentCell, cerCertificadoFirma, contador);
        return cerCertificadoFirma;
    }

    protected String setSaveMasivo(CerCertificadoFirma dto) throws Exception {
        return "";
    }

    protected List<CerCertificadoFirma> setBeforeDeleteMasivo(List<CerCertificadoFirma> listaDto) throws Exception {
        return listaDto;
    }

    protected void setAfterDeleteMasivo() throws Exception {
        return;
    }

    /*****************************/
    /* Metodos que generan Excel */
    /*****************************/

    protected void setDownloadExcelItem(CerCertificadoFirma bean, HSSFRow dataRow) {

    }

    protected void setDownloadExcel(HSSFSheet sheet) {

    }

    /*****************/
    /* Otros Metodos */
    /*****************/

    protected String setGraphDescripcionByCerCertificado(CerCertificado cerCertificado) {
        return cerCertificado.getId().toString();
    }

    protected String setGraphPieChartTituloByCerCertificado() {
        return "CerCertificado";
    }

    protected String setGraphBarChartTituloByCerCertificado() {
        return "CerCertificado";
    }

    protected String setGraphDescripcionByCerFirma(CerFirma cerFirma) {
        return cerFirma.getId().toString();
    }

    protected String setGraphPieChartTituloByCerFirma() {
        return "CerFirma";
    }

    protected String setGraphBarChartTituloByCerFirma() {
        return "CerFirma";
    }

}
