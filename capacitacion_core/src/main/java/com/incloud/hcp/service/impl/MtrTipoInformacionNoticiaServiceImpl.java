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
import com.incloud.hcp.domain.MtrTipoInformacionNoticia;
import com.incloud.hcp.domain.MtrTipoInformacionNoticia_;
import com.incloud.hcp.domain.response.MtrTipoInformacionNoticiaResponse;
import com.incloud.hcp.exception.ServiceException;
import com.incloud.hcp.repository.delta.AppParametriaDeltaRepository;
import com.incloud.hcp.repository.delta.MtrTipoInformacionNoticiaDeltaRepository;
import com.incloud.hcp.service.MtrTipoInformacionNoticiaService;
import com.incloud.hcp.service._framework.bean.BeanCargaMasivoDTO;
import com.incloud.hcp.service._framework.bean.BeanListaMasivoDTO;
import com.incloud.hcp.service._framework.impl.JPACustomServiceImpl;
import com.incloud.hcp.service.requireNew.MtrTipoInformacionNoticiaRequireNewService;
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
 * A simple DTO Facility for MtrTipoInformacionNoticia.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public abstract class MtrTipoInformacionNoticiaServiceImpl extends JPACustomServiceImpl<MtrTipoInformacionNoticiaResponse, MtrTipoInformacionNoticia, Integer>
        implements MtrTipoInformacionNoticiaService {

    protected final String NAME_SHEET = "MtrTipoInformacionNoticia";
    protected final String CONFIG_TITLE = "com/incloud/hcp/excel/MtrTipoInformacionNoticiaExcel.xml";
    private final Integer REGISTROS_COLOR = 10;

    @Autowired
    protected AppParametriaDeltaRepository appParametriaDeltaRepository;

    @Autowired
    protected MtrTipoInformacionNoticiaDeltaRepository mtrTipoInformacionNoticiaDeltaRepository;

    @Autowired
    protected MtrTipoInformacionNoticiaRequireNewService mtrTipoInformacionNoticiaRequireNewService;

    /*****************************/
    /* Metodos de Busqueda       */
    /*****************************/

    protected final ExampleMatcher setAbstractFind(ExampleMatcher matcher) {
        matcher = ExampleMatcher.matching() //
                .withMatcher(MtrTipoInformacionNoticia_.carpetaId.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MtrTipoInformacionNoticia_.descripcion.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MtrTipoInformacionNoticia_.iconText.getName(), match -> match.ignoreCase().startsWith());
        return matcher;
    }

    protected abstract Sort setFindAll(Sort sort);

    protected abstract Sort setFind(MtrTipoInformacionNoticia req, ExampleMatcher matcher, Example<MtrTipoInformacionNoticia> example, Sort sort);

    protected abstract void setFindPaginated(PageRequestByExample<MtrTipoInformacionNoticia> req, ExampleMatcher matcher,
            Example<MtrTipoInformacionNoticia> example);

    protected final MtrTipoInformacionNoticia setObtenerBeanResponse(MtrTipoInformacionNoticiaResponse bean) {
        return bean.getBean();
    }

    protected final Class<MtrTipoInformacionNoticia> setObtenerClassBean() {
        return MtrTipoInformacionNoticia.class;
    }

    protected List<Predicate> setAbstractPredicate(MtrTipoInformacionNoticiaResponse bean, CriteriaBuilder cb, Root<MtrTipoInformacionNoticia> root) {
        List<Predicate> predicates = new ArrayList<>();
        MtrTipoInformacionNoticia entity = bean.getBean();
        /* Obtener condiciones */
        PredicateUtils.addPredicates(predicates, bean.getIdCondicion(), "id", entity.getId(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getCarpetaIdCondicion(), "carpetaId", entity.getCarpetaId(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getDescripcionCondicion(), "descripcion", entity.getDescripcion(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getIconTextCondicion(), "iconText", entity.getIconText(), cb, root);
        /* Obtener valores de Lista */
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "id", bean.getIdList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "carpetaId", bean.getCarpetaIdList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "descripcion", bean.getDescripcionList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "iconText", bean.getIconTextList(), cb, root);
        return predicates;
    }

    /****************/
    /* METODOS CRUD */
    /****************/

    protected final String validacionesPrevias(MtrTipoInformacionNoticia bean) throws Exception {
        String mensaje = "";
        if (!Optional.of(bean.getDescripcion()).isPresent()) {
            String msg = this.messageSource.getMessage("message.mtrTipoInformacionNoticia.descripcion.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        String msg = this.setValidacionesPrevias(bean);
        if (StringUtils.isNotBlank(msg)) {
            mensaje += "* " + msg + "<br/>";
        }
        return mensaje;
    }

    protected String setValidacionesPrevias(MtrTipoInformacionNoticia bean) throws Exception {
        String mensaje = "";
        return mensaje;
    }

    protected String validacionesPreviasCreate(MtrTipoInformacionNoticia bean) throws Exception {
        String msg = null;
        MtrTipoInformacionNoticia validar = null;
        validar = this.mtrTipoInformacionNoticiaDeltaRepository.getByDescripcion(bean.getDescripcion());
        if (Optional.ofNullable(validar).isPresent()) {
            msg = this.messageSource.getMessage("message.mtrTipoInformacionNoticia.descripcion.duplicado", null, LocaleContextHolder.getLocale());
            return msg;
        }
        return msg;
    }

    protected String validacionesPreviasSave(MtrTipoInformacionNoticia bean) throws Exception {
        String msg = null;
        MtrTipoInformacionNoticia validar = null;
        validar = this.mtrTipoInformacionNoticiaDeltaRepository.getByDescripcion(bean.getDescripcion());
        if (Optional.ofNullable(validar).isPresent()) {
            if (bean.getId().intValue() != validar.getId().intValue()) {
                msg = this.messageSource.getMessage("message.mtrTipoInformacionNoticia.descripcion.duplicado", null, LocaleContextHolder.getLocale());
                return msg;
            }
        }
        return msg;
    }

    protected MtrTipoInformacionNoticia completarDatosBean(MtrTipoInformacionNoticia bean) throws Exception {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected MtrTipoInformacionNoticia setCompletarDatosBean(MtrTipoInformacionNoticia bean) throws Exception {
        return bean;
    }

    protected final MtrTipoInformacionNoticia setAbstractCreate(MtrTipoInformacionNoticia dto) throws Exception {
        MtrTipoInformacionNoticia bean = new MtrTipoInformacionNoticia();
        bean = (MtrTipoInformacionNoticia) BeanUtils.cloneBean(dto);
        return bean;
    }

    /************************/
    /* METODOS CRUD Masivos */
    /************************/

    protected MtrTipoInformacionNoticia setUploadExcel(Cell currentCell, MtrTipoInformacionNoticia mtrTipoInformacionNoticia, int contador) throws Exception {
        Double valorDouble = new Double(0);
        BigDecimal valorDecimal = new BigDecimal(0);
        Boolean valorBoolean = new Boolean(true);
        String valorCadena = "";
        switch (contador) {
        case 1:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 60) {
                    throw new ServiceException("Valor Campo carpetaId contiene mas de 60 caracter(es)");
                }
                mtrTipoInformacionNoticia.setCarpetaId(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo carpetaId está en formato incorrecto");
            }
            break;
        case 2:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 60) {
                    throw new ServiceException("Valor Campo descripcion contiene mas de 60 caracter(es)");
                }
                mtrTipoInformacionNoticia.setDescripcion(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo descripcion está en formato incorrecto");
            }
            break;
        case 3:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 60) {
                    throw new ServiceException("Valor Campo iconText contiene mas de 60 caracter(es)");
                }
                mtrTipoInformacionNoticia.setIconText(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo iconText está en formato incorrecto");
            }
            break;
        default:
            break;
        }
        return mtrTipoInformacionNoticia;
    }

    protected AppParametria setObtenerRegistroConfiguracionUploadExcel() {
        AppParametria appParametriaData = this.appParametriaDeltaRepository.getByModuloAndLabelAndStatus(AppParametriaModuloEnum.CARGA_EXCEL.getEstado(),
                AppParametriaLabelEnum.INICIO_REGISTRO_DATA.getEstado(), Constants.ESTADO_ACTIVO);
        return appParametriaData;
    }

    protected MtrTipoInformacionNoticia setInicializarBeanUpdateExcel() {
        MtrTipoInformacionNoticia bean = new MtrTipoInformacionNoticia();
        bean.setId(null);
        return bean;
    }

    protected final Integer setObtenerId(MtrTipoInformacionNoticia bean) {
        return bean.getId();
    }

    /************************/
    /* Instancia de Bean    */
    /************************/

    protected final MtrTipoInformacionNoticia createInstance() {
        MtrTipoInformacionNoticia mtrTipoInformacionNoticia = new MtrTipoInformacionNoticia();
        return mtrTipoInformacionNoticia;
    }

    protected final BeanCargaMasivoDTO<MtrTipoInformacionNoticia> createInstanceMasivoDTO() {
        BeanCargaMasivoDTO<MtrTipoInformacionNoticia> beanCargaMasivoDTO = new BeanCargaMasivoDTO<MtrTipoInformacionNoticia>();
        return beanCargaMasivoDTO;

    }

    protected final BeanListaMasivoDTO<BeanCargaMasivoDTO<MtrTipoInformacionNoticia>> createInstanceListaMasivoDTO() {
        BeanListaMasivoDTO<BeanCargaMasivoDTO<MtrTipoInformacionNoticia>> beanCargaMasivoDTOBeanListaMasivoDTO = new BeanListaMasivoDTO<BeanCargaMasivoDTO<MtrTipoInformacionNoticia>>();
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

    protected int setAbstractDownloadExcel(MtrTipoInformacionNoticia bean, HSSFRow dataRow) {
        int contador = 0;
        ExcelDefault.setValueCell(bean.getId(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getCarpetaId(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getDescripcion(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getIconText(), dataRow.createCell(contador));
        contador++;
        return contador;
    }

    protected int setAbstractDownloadExcelSXLSX(MtrTipoInformacionNoticia bean, Row dataRow, List<CellStyle> cellStyleList) {
        int contador = 0;
        ExcelDefault.setValueCell(bean.getId(), dataRow.createCell(contador), "I", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getCarpetaId(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getDescripcion(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getIconText(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        return contador;
    }

    protected String setAbstractGenerateInsertExcelSXLSX(MtrTipoInformacionNoticia bean) {
        String fechaS = "";
        String sqlInsert = "INSERT INTO mtr_tipo_informacion_noticia(";
        sqlInsert = sqlInsert + "mtr_tipo_informacion_noticia_id" + ", ";
        sqlInsert = sqlInsert + "carpeta_id" + ", ";
        sqlInsert = sqlInsert + "descripcion" + ", ";
        sqlInsert = sqlInsert + "icon_text" + ")";
        sqlInsert = sqlInsert + " VALUES (";
        sqlInsert = sqlInsert + bean.getId() + ", ";
        if (StringUtils.isBlank(bean.getCarpetaId())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getCarpetaId() + "'" + ", ";
        }
        if (StringUtils.isBlank(bean.getDescripcion())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getDescripcion() + "'" + ", ";
        }
        if (StringUtils.isBlank(bean.getIconText())) {
            sqlInsert = sqlInsert + "null";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getIconText() + "'";
        }
        sqlInsert = sqlInsert + " );";
        return sqlInsert;
    }

    /*****************/
    /* Otros Metodos */
    /*****************/

}
