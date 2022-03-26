/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/dto/EntityDTOService.java.e.vm
 */
package com.incloud.hcp.service.impl;

import com.incloud.hcp.common.enums.AppParametriaLabelEnum;
import com.incloud.hcp.common.enums.AppParametriaModuloEnum;
import com.incloud.hcp.common.excel.ExcelDefault;
import com.incloud.hcp.domain.AppParametria;
import com.incloud.hcp.domain.MtrTipoContrato;
import com.incloud.hcp.domain.MtrTipoContrato_;
import com.incloud.hcp.domain.response.MtrTipoContratoResponse;
import com.incloud.hcp.exception.ServiceException;
import com.incloud.hcp.repository.delta.AppParametriaDeltaRepository;
import com.incloud.hcp.repository.delta.MtrTipoContratoDeltaRepository;
import com.incloud.hcp.service.MtrTipoContratoService;
import com.incloud.hcp.service._framework.bean.BeanCargaMasivoDTO;
import com.incloud.hcp.service._framework.bean.BeanListaMasivoDTO;
import com.incloud.hcp.service._framework.impl.JPACustomServiceImpl;
import com.incloud.hcp.service.requireNew.MtrTipoContratoRequireNewService;
import com.incloud.hcp.service.support.PageRequestByExample;
import com.incloud.hcp.utils.Constants;
import com.incloud.hcp.utils.PredicateUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A simple DTO Facility for MtrTipoContrato.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public abstract class MtrTipoContratoServiceImpl extends JPACustomServiceImpl<MtrTipoContratoResponse, MtrTipoContrato, Integer>
        implements MtrTipoContratoService {

    protected final String NAME_SHEET = "MtrTipoContrato";
    protected final String CONFIG_TITLE = "com/incloud/hcp/excel/MtrTipoContratoExcel.xml";
    private final Integer REGISTROS_COLOR = 10;

    @Autowired
    protected AppParametriaDeltaRepository appParametriaDeltaRepository;

    @Autowired
    protected MtrTipoContratoDeltaRepository mtrTipoContratoDeltaRepository;

    @Autowired
    protected MtrTipoContratoRequireNewService mtrTipoContratoRequireNewService;

    /*****************************/
    /* Metodos de Busqueda       */
    /*****************************/

    protected final ExampleMatcher setAbstractFind(ExampleMatcher matcher) {
        matcher = ExampleMatcher.matching() //
                .withMatcher(MtrTipoContrato_.descripcion.getName(), match -> match.ignoreCase().startsWith());
        return matcher;
    }

    protected abstract Sort setFindAll(Sort sort);

    protected abstract Sort setFind(MtrTipoContrato req, ExampleMatcher matcher, Example<MtrTipoContrato> example, Sort sort);

    protected abstract void setFindPaginated(PageRequestByExample<MtrTipoContrato> req, ExampleMatcher matcher, Example<MtrTipoContrato> example);

    protected final MtrTipoContrato setObtenerBeanResponse(MtrTipoContratoResponse bean) {
        return bean.getBean();
    }

    protected final Class<MtrTipoContrato> setObtenerClassBean() {
        return MtrTipoContrato.class;
    }

    protected List<Predicate> setAbstractPredicate(MtrTipoContratoResponse bean, CriteriaBuilder cb, Root<MtrTipoContrato> root) {
        List<Predicate> predicates = new ArrayList<>();
        MtrTipoContrato entity = bean.getBean();
        /* Obtener condiciones */
        PredicateUtils.addPredicates(predicates, bean.getIdCondicion(), "id", entity.getId(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getDescripcionCondicion(), "descripcion", entity.getDescripcion(), cb, root);
        /* Obtener valores de Lista */
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "id", bean.getIdList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "descripcion", bean.getDescripcionList(), cb, root);
        return predicates;
    }

    /****************/
    /* METODOS CRUD */
    /****************/

    protected final String validacionesPrevias(MtrTipoContrato bean) throws Exception {
        String mensaje = "";
        if (!Optional.of(bean.getDescripcion()).isPresent()) {
            String msg = this.messageSource.getMessage("message.mtrTipoContrato.descripcion.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        String msg = this.setValidacionesPrevias(bean);
        if (StringUtils.isNotBlank(msg)) {
            mensaje += "* " + msg + "<br/>";
        }
        return mensaje;
    }

    protected String setValidacionesPrevias(MtrTipoContrato bean) throws Exception {
        String mensaje = "";
        return mensaje;
    }

    protected String validacionesPreviasCreate(MtrTipoContrato bean) throws Exception {
        String msg = null;
        MtrTipoContrato validar = null;
        validar = this.mtrTipoContratoDeltaRepository.getByDescripcion(bean.getDescripcion());
        if (Optional.ofNullable(validar).isPresent()) {
            msg = this.messageSource.getMessage("message.mtrTipoContrato.descripcion.duplicado", null, LocaleContextHolder.getLocale());
            return msg;
        }
        return msg;
    }

    protected String validacionesPreviasSave(MtrTipoContrato bean) throws Exception {
        String msg = null;
        MtrTipoContrato validar = null;
        validar = this.mtrTipoContratoDeltaRepository.getByDescripcion(bean.getDescripcion());
        if (Optional.ofNullable(validar).isPresent()) {
            if (bean.getId().intValue() != validar.getId().intValue()) {
                msg = this.messageSource.getMessage("message.mtrTipoContrato.descripcion.duplicado", null, LocaleContextHolder.getLocale());
                return msg;
            }
        }
        return msg;
    }

    protected MtrTipoContrato completarDatosBean(MtrTipoContrato bean) throws Exception {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected MtrTipoContrato setCompletarDatosBean(MtrTipoContrato bean) throws Exception {
        return bean;
    }

    protected final MtrTipoContrato setAbstractCreate(MtrTipoContrato dto) throws Exception {
        MtrTipoContrato bean = new MtrTipoContrato();
        bean = (MtrTipoContrato) BeanUtils.cloneBean(dto);
        return bean;
    }

    /************************/
    /* METODOS CRUD Masivos */
    /************************/

    protected MtrTipoContrato setUploadExcel(Cell currentCell, MtrTipoContrato mtrTipoContrato, int contador) throws Exception {
        Double valorDouble = new Double(0);
        BigDecimal valorDecimal = new BigDecimal(0);
        Boolean valorBoolean = new Boolean(true);
        String valorCadena = "";
        switch (contador) {
        case 1:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 2147483647) {
                    throw new ServiceException("Valor Campo descripcion contiene mas de 2147483647 caracter(es)");
                }
                mtrTipoContrato.setDescripcion(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo descripcion está en formato incorrecto");
            }
            break;
        default:
            break;
        }
        return mtrTipoContrato;
    }

    protected AppParametria setObtenerRegistroConfiguracionUploadExcel() {
        AppParametria appParametriaData = this.appParametriaDeltaRepository.getByModuloAndLabelAndStatus(AppParametriaModuloEnum.CARGA_EXCEL.getEstado(),
                AppParametriaLabelEnum.INICIO_REGISTRO_DATA.getEstado(), Constants.ESTADO_ACTIVO);
        return appParametriaData;
    }

    protected MtrTipoContrato setInicializarBeanUpdateExcel() {
        MtrTipoContrato bean = new MtrTipoContrato();
        bean.setId(null);
        return bean;
    }

    protected final Integer setObtenerId(MtrTipoContrato bean) {
        return bean.getId();
    }

    /************************/
    /* Instancia de Bean    */
    /************************/

    protected final MtrTipoContrato createInstance() {
        MtrTipoContrato mtrTipoContrato = new MtrTipoContrato();
        return mtrTipoContrato;
    }

    protected final BeanCargaMasivoDTO<MtrTipoContrato> createInstanceMasivoDTO() {
        BeanCargaMasivoDTO<MtrTipoContrato> beanCargaMasivoDTO = new BeanCargaMasivoDTO<MtrTipoContrato>();
        return beanCargaMasivoDTO;

    }

    protected final BeanListaMasivoDTO<BeanCargaMasivoDTO<MtrTipoContrato>> createInstanceListaMasivoDTO() {
        BeanListaMasivoDTO<BeanCargaMasivoDTO<MtrTipoContrato>> beanCargaMasivoDTOBeanListaMasivoDTO = new BeanListaMasivoDTO<BeanCargaMasivoDTO<MtrTipoContrato>>();
        return beanCargaMasivoDTOBeanListaMasivoDTO;
    }

    /*****************************/
    /* Metodos que generan Excel */
    /*****************************/

    protected String devuelveNombreSheet() {
        return this.NAME_SHEET;
    }

    protected String devuelveListaHeaderExcelXML() {
        return this.CONFIG_TITLE;
    }

    protected int setAbstractDownloadExcel(MtrTipoContrato bean, HSSFRow dataRow) {
        int contador = 0;
        ExcelDefault.setValueCell(bean.getId(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getDescripcion(), dataRow.createCell(contador));
        contador++;
        return contador;
    }

    protected int setAbstractDownloadExcelSXLSX(MtrTipoContrato bean, Row dataRow, List<CellStyle> cellStyleList) {
        int contador = 0;
        ExcelDefault.setValueCell(bean.getId(), dataRow.createCell(contador), "I", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getDescripcion(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        return contador;
    }

    protected String setAbstractGenerateInsertExcelSXLSX(MtrTipoContrato bean) {
        String fechaS = "";
        String sqlInsert = "INSERT INTO mtr_tipo_contrato(";
        sqlInsert = sqlInsert + "mtr_tipo_contrato_id" + ", ";
        sqlInsert = sqlInsert + "descripcion" + ")";
        sqlInsert = sqlInsert + " VALUES (";
        sqlInsert = sqlInsert + bean.getId() + ", ";
        if (StringUtils.isBlank(bean.getDescripcion())) {
            sqlInsert = sqlInsert + "null";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getDescripcion() + "'";
        }
        sqlInsert = sqlInsert + " );";
        return sqlInsert;
    }

    /*****************/
    /* Otros Metodos */
    /*****************/

}
