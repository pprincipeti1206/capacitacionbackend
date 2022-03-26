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
import com.incloud.hcp.common.graph.GraphBarChart;
import com.incloud.hcp.common.graph.GraphBean;
import com.incloud.hcp.common.graph.GraphDataset;
import com.incloud.hcp.common.graph.GraphPieChart;
import com.incloud.hcp.domain.AppParametria;
import com.incloud.hcp.domain.MtrInformacionNoticia;
import com.incloud.hcp.domain.MtrInformacionNoticia_;
import com.incloud.hcp.domain.MtrTipoInformacionNoticia;
import com.incloud.hcp.domain.response.MtrInformacionNoticiaResponse;
import com.incloud.hcp.exception.ServiceException;
import com.incloud.hcp.repository.delta.AppParametriaDeltaRepository;
import com.incloud.hcp.repository.delta.MtrInformacionNoticiaDeltaRepository;
import com.incloud.hcp.repository.delta.MtrTipoInformacionNoticiaDeltaRepository;
import com.incloud.hcp.service.MtrInformacionNoticiaService;
import com.incloud.hcp.service._framework.bean.BeanCargaMasivoDTO;
import com.incloud.hcp.service._framework.bean.BeanListaMasivoDTO;
import com.incloud.hcp.service._framework.impl.JPACustomServiceImpl;
import com.incloud.hcp.service.delta.MtrTipoInformacionNoticiaDeltaService;
import com.incloud.hcp.service.requireNew.MtrInformacionNoticiaRequireNewService;
import com.incloud.hcp.service.support.PageRequestByExample;
import com.incloud.hcp.utils.Constants;
import com.incloud.hcp.utils.DateUtils;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * A simple DTO Facility for MtrInformacionNoticia.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public abstract class MtrInformacionNoticiaServiceImpl extends JPACustomServiceImpl<MtrInformacionNoticiaResponse, MtrInformacionNoticia, Integer>
        implements MtrInformacionNoticiaService {

    protected final String NAME_SHEET = "MtrInformacionNoticia";
    protected final String CONFIG_TITLE = "com/incloud/hcp/excel/MtrInformacionNoticiaExcel.xml";
    private final Integer REGISTROS_COLOR = 10;

    @Autowired
    protected AppParametriaDeltaRepository appParametriaDeltaRepository;

    @Autowired
    protected MtrInformacionNoticiaDeltaRepository mtrInformacionNoticiaDeltaRepository;

    @Autowired
    protected MtrInformacionNoticiaRequireNewService mtrInformacionNoticiaRequireNewService;

    @Autowired
    protected MtrTipoInformacionNoticiaDeltaService mtrTipoInformacionNoticiaDeltaService;

    @Autowired
    protected MtrTipoInformacionNoticiaDeltaRepository mtrTipoInformacionNoticiaDeltaRepository;

    /*****************************/
    /* Metodos de Busqueda       */
    /*****************************/

    protected final ExampleMatcher setAbstractFind(ExampleMatcher matcher) {
        matcher = ExampleMatcher.matching() //
                .withMatcher(MtrInformacionNoticia_.archivoId.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MtrInformacionNoticia_.archivoNombre.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MtrInformacionNoticia_.contenido.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MtrInformacionNoticia_.iconText.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MtrInformacionNoticia_.indActivo.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MtrInformacionNoticia_.indNoticiaNuevoProveedor.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MtrInformacionNoticia_.rutaAdjunto.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MtrInformacionNoticia_.textoInfo.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MtrInformacionNoticia_.titulo.getName(), match -> match.ignoreCase().startsWith());
        return matcher;
    }

    protected abstract Sort setFindAll(Sort sort);

    protected abstract Sort setFind(MtrInformacionNoticia req, ExampleMatcher matcher, Example<MtrInformacionNoticia> example, Sort sort);

    protected abstract void setFindPaginated(PageRequestByExample<MtrInformacionNoticia> req, ExampleMatcher matcher, Example<MtrInformacionNoticia> example);

    protected final MtrInformacionNoticia setObtenerBeanResponse(MtrInformacionNoticiaResponse bean) {
        return bean.getBean();
    }

    protected final Class<MtrInformacionNoticia> setObtenerClassBean() {
        return MtrInformacionNoticia.class;
    }

    protected List<Predicate> setAbstractPredicate(MtrInformacionNoticiaResponse bean, CriteriaBuilder cb, Root<MtrInformacionNoticia> root) {
        List<Predicate> predicates = new ArrayList<>();
        MtrInformacionNoticia entity = bean.getBean();
        /* Obtener condiciones */
        PredicateUtils.addPredicates(predicates, bean.getIdCondicion(), "id", entity.getId(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getArchivoIdCondicion(), "archivoId", entity.getArchivoId(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getArchivoNombreCondicion(), "archivoNombre", entity.getArchivoNombre(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getContenidoCondicion(), "contenido", entity.getContenido(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getFechaCaducidadCondicion(), "fechaCaducidad", entity.getFechaCaducidad(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getFechaCreacionCondicion(), "fechaCreacion", entity.getFechaCreacion(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getFechaModificacionCondicion(), "fechaModificacion", entity.getFechaModificacion(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getFechaPublicacionCondicion(), "fechaPublicacion", entity.getFechaPublicacion(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getIconTextCondicion(), "iconText", entity.getIconText(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getIndActivoCondicion(), "indActivo", entity.getIndActivo(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getIndNoticiaNuevoProveedorCondicion(), "indNoticiaNuevoProveedor", entity.getIndNoticiaNuevoProveedor(),
                cb, root);
        PredicateUtils.addPredicates(predicates, bean.getRutaAdjuntoCondicion(), "rutaAdjunto", entity.getRutaAdjunto(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getTextoInfoCondicion(), "textoInfo", entity.getTextoInfo(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getTituloCondicion(), "titulo", entity.getTitulo(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getUsuarioCreacionCondicion(), "usuarioCreacion", entity.getUsuarioCreacion(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getUsuarioModificacionCondicion(), "usuarioModificacion", entity.getUsuarioModificacion(), cb, root);
        //PredicateUtils.addPredicates(predicates, bean.getMtrTipoInformacionNoticiaCondicion(), "mtrTipoInformacionNoticia", entity.getMtrTipoInformacionNoticia(), cb, root);
        /* Obtener valores de Lista */
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "id", bean.getIdList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "archivoId", bean.getArchivoIdList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "archivoNombre", bean.getArchivoNombreList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "contenido", bean.getContenidoList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "fechaCaducidad", bean.getFechaCaducidadList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "fechaCreacion", bean.getFechaCreacionList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "fechaModificacion", bean.getFechaModificacionList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "fechaPublicacion", bean.getFechaPublicacionList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "iconText", bean.getIconTextList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "indActivo", bean.getIndActivoList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "indNoticiaNuevoProveedor", bean.getIndNoticiaNuevoProveedorList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "rutaAdjunto", bean.getRutaAdjuntoList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "textoInfo", bean.getTextoInfoList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "titulo", bean.getTituloList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "usuarioCreacion", bean.getUsuarioCreacionList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "usuarioModificacion", bean.getUsuarioModificacionList(), cb, root);
        //PredicateUtils.addPredicatesListValorBean(predicates, "mtrTipoInformacionNoticia", bean.getMtrTipoInformacionNoticiaList(), cb, root);
        return predicates;
    }

    /****************/
    /* METODOS CRUD */
    /****************/

    protected final String validacionesPrevias(MtrInformacionNoticia bean) throws Exception {
        String mensaje = "";
        if (!Optional.of(bean.getContenido()).isPresent()) {
            String msg = this.messageSource.getMessage("message.mtrInformacionNoticia.contenido.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        if (!Optional.of(bean.getFechaCreacion()).isPresent()) {
            String msg = this.messageSource.getMessage("message.mtrInformacionNoticia.fechaCreacion.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        if (!Optional.of(bean.getFechaPublicacion()).isPresent()) {
            String msg = this.messageSource.getMessage("message.mtrInformacionNoticia.fechaPublicacion.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        if (!Optional.of(bean.getIndActivo()).isPresent()) {
            String msg = this.messageSource.getMessage("message.mtrInformacionNoticia.indActivo.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        if (!Optional.of(bean.getIndNoticiaNuevoProveedor()).isPresent()) {
            String msg = this.messageSource.getMessage("message.mtrInformacionNoticia.indNoticiaNuevoProveedor.requerido", null,
                    LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        if (!Optional.of(bean.getUsuarioCreacion()).isPresent()) {
            String msg = this.messageSource.getMessage("message.mtrInformacionNoticia.usuarioCreacion.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        String msg = this.setValidacionesPrevias(bean);
        if (StringUtils.isNotBlank(msg)) {
            mensaje += "* " + msg + "<br/>";
        }
        return mensaje;
    }

    protected String setValidacionesPrevias(MtrInformacionNoticia bean) throws Exception {
        String mensaje = "";
        return mensaje;
    }

    protected String validacionesPreviasCreate(MtrInformacionNoticia bean) throws Exception {
        String msg = null;
        MtrInformacionNoticia validar = null;
        return msg;
    }

    protected String validacionesPreviasSave(MtrInformacionNoticia bean) throws Exception {
        String msg = null;
        MtrInformacionNoticia validar = null;
        return msg;
    }

    protected MtrInformacionNoticia completarDatosBean(MtrInformacionNoticia bean) throws Exception {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected MtrInformacionNoticia setCompletarDatosBean(MtrInformacionNoticia bean) throws Exception {
        return bean;
    }

    protected final MtrInformacionNoticia setAbstractCreate(MtrInformacionNoticia dto) throws Exception {
        MtrInformacionNoticia bean = new MtrInformacionNoticia();
        bean = (MtrInformacionNoticia) BeanUtils.cloneBean(dto);
        return bean;
    }

    /************************/
    /* METODOS CRUD Masivos */
    /************************/

    protected MtrInformacionNoticia setUploadExcel(Cell currentCell, MtrInformacionNoticia mtrInformacionNoticia, int contador) throws Exception {
        Double valorDouble = new Double(0);
        BigDecimal valorDecimal = new BigDecimal(0);
        Boolean valorBoolean = new Boolean(true);
        String valorCadena = "";
        switch (contador) {
        case 1:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 255) {
                    throw new ServiceException("Valor Campo archivoId contiene mas de 255 caracter(es)");
                }
                mtrInformacionNoticia.setArchivoId(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo archivoId está en formato incorrecto");
            }
            break;
        case 2:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 255) {
                    throw new ServiceException("Valor Campo archivoNombre contiene mas de 255 caracter(es)");
                }
                mtrInformacionNoticia.setArchivoNombre(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo archivoNombre está en formato incorrecto");
            }
            break;
        case 3:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 64000) {
                    throw new ServiceException("Valor Campo contenido contiene mas de 64000 caracter(es)");
                }
                mtrInformacionNoticia.setContenido(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo contenido está en formato incorrecto");
            }
            break;
        case 4:
            try {
                valorCadena = currentCell.getStringCellValue();
                Date valorFechaD = DateUtils.convertStringToDate("dd/MM/yyyy", valorCadena);
                mtrInformacionNoticia.setFechaCaducidad(valorFechaD);
            } catch (Exception ex) {
                throw new ServiceException("Valor Campo fechaCaducidad está en formato incorrecto");
            }
            break;
        case 5:
            try {
                valorCadena = currentCell.getStringCellValue();
                Date valorFechaD = DateUtils.convertStringToDate("dd/MM/yyyy", valorCadena);
                mtrInformacionNoticia.setFechaCreacion(valorFechaD);
            } catch (Exception ex) {
                throw new ServiceException("Valor Campo fechaCreacion está en formato incorrecto");
            }
            break;
        case 6:
            try {
                valorCadena = currentCell.getStringCellValue();
                Date valorFechaD = DateUtils.convertStringToDate("dd/MM/yyyy", valorCadena);
                mtrInformacionNoticia.setFechaModificacion(valorFechaD);
            } catch (Exception ex) {
                throw new ServiceException("Valor Campo fechaModificacion está en formato incorrecto");
            }
            break;
        case 7:
            try {
                valorCadena = currentCell.getStringCellValue();
                Date valorFechaD = DateUtils.convertStringToDate("dd/MM/yyyy", valorCadena);
                mtrInformacionNoticia.setFechaPublicacion(valorFechaD);
            } catch (Exception ex) {
                throw new ServiceException("Valor Campo fechaPublicacion está en formato incorrecto");
            }
            break;
        case 8:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 255) {
                    throw new ServiceException("Valor Campo iconText contiene mas de 255 caracter(es)");
                }
                mtrInformacionNoticia.setIconText(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo iconText está en formato incorrecto");
            }
            break;
        case 9:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 1) {
                    throw new ServiceException("Valor Campo indActivo contiene mas de 1 caracter(es)");
                }
                mtrInformacionNoticia.setIndActivo(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo indActivo está en formato incorrecto");
            }
            break;
        case 10:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 1) {
                    throw new ServiceException("Valor Campo indNoticiaNuevoProveedor contiene mas de 1 caracter(es)");
                }
                mtrInformacionNoticia.setIndNoticiaNuevoProveedor(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo indNoticiaNuevoProveedor está en formato incorrecto");
            }
            break;
        case 11:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 1000) {
                    throw new ServiceException("Valor Campo rutaAdjunto contiene mas de 1000 caracter(es)");
                }
                mtrInformacionNoticia.setRutaAdjunto(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo rutaAdjunto está en formato incorrecto");
            }
            break;
        case 12:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 255) {
                    throw new ServiceException("Valor Campo textoInfo contiene mas de 255 caracter(es)");
                }
                mtrInformacionNoticia.setTextoInfo(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo textoInfo está en formato incorrecto");
            }
            break;
        case 13:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 255) {
                    throw new ServiceException("Valor Campo titulo contiene mas de 255 caracter(es)");
                }
                mtrInformacionNoticia.setTitulo(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo titulo está en formato incorrecto");
            }
            break;
        case 14:
            try {
                valorDouble = new Double(currentCell.getNumericCellValue());
                mtrInformacionNoticia.setUsuarioCreacion(valorDouble.intValue());
            } catch (Exception e) {
                throw new ServiceException("Valor Campo usuarioCreacion está en formato incorrecto");
            }
            break;
        case 15:
            try {
                valorDouble = new Double(currentCell.getNumericCellValue());
                mtrInformacionNoticia.setUsuarioModificacion(valorDouble.intValue());
            } catch (Exception e) {
                throw new ServiceException("Valor Campo usuarioModificacion está en formato incorrecto");
            }
            break;
        default:
            break;
        }
        return mtrInformacionNoticia;
    }

    protected AppParametria setObtenerRegistroConfiguracionUploadExcel() {
        AppParametria appParametriaData = this.appParametriaDeltaRepository.getByModuloAndLabelAndStatus(AppParametriaModuloEnum.CARGA_EXCEL.getEstado(),
                AppParametriaLabelEnum.INICIO_REGISTRO_DATA.getEstado(), Constants.ESTADO_ACTIVO);
        return appParametriaData;
    }

    protected MtrInformacionNoticia setInicializarBeanUpdateExcel() {
        MtrInformacionNoticia bean = new MtrInformacionNoticia();
        bean.setId(null);
        return bean;
    }

    protected final Integer setObtenerId(MtrInformacionNoticia bean) {
        return bean.getId();
    }

    /************************/
    /* Instancia de Bean    */
    /************************/

    protected final MtrInformacionNoticia createInstance() {
        MtrInformacionNoticia mtrInformacionNoticia = new MtrInformacionNoticia();
        return mtrInformacionNoticia;
    }

    protected final BeanCargaMasivoDTO<MtrInformacionNoticia> createInstanceMasivoDTO() {
        BeanCargaMasivoDTO<MtrInformacionNoticia> beanCargaMasivoDTO = new BeanCargaMasivoDTO<MtrInformacionNoticia>();
        return beanCargaMasivoDTO;

    }

    protected final BeanListaMasivoDTO<BeanCargaMasivoDTO<MtrInformacionNoticia>> createInstanceListaMasivoDTO() {
        BeanListaMasivoDTO<BeanCargaMasivoDTO<MtrInformacionNoticia>> beanCargaMasivoDTOBeanListaMasivoDTO = new BeanListaMasivoDTO<BeanCargaMasivoDTO<MtrInformacionNoticia>>();
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

    protected int setAbstractDownloadExcel(MtrInformacionNoticia bean, HSSFRow dataRow) {
        int contador = 0;
        ExcelDefault.setValueCell(bean.getId(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getArchivoId(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getArchivoNombre(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getContenido(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getFechaCaducidad(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getFechaCreacion(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getFechaModificacion(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getFechaPublicacion(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getIconText(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getIndActivo(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getIndNoticiaNuevoProveedor(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getRutaAdjunto(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getTextoInfo(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getTitulo(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getUsuarioCreacion(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getUsuarioModificacion(), dataRow.createCell(contador));
        contador++;
        return contador;
    }

    protected int setAbstractDownloadExcelSXLSX(MtrInformacionNoticia bean, Row dataRow, List<CellStyle> cellStyleList) {
        int contador = 0;
        ExcelDefault.setValueCell(bean.getId(), dataRow.createCell(contador), "I", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getArchivoId(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getArchivoNombre(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getContenido(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getFechaCaducidad(), dataRow.createCell(contador), "DT", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getFechaCreacion(), dataRow.createCell(contador), "DT", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getFechaModificacion(), dataRow.createCell(contador), "DT", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getFechaPublicacion(), dataRow.createCell(contador), "DT", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getIconText(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getIndActivo(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getIndNoticiaNuevoProveedor(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getRutaAdjunto(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getTextoInfo(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getTitulo(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getUsuarioCreacion(), dataRow.createCell(contador), "I", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getUsuarioModificacion(), dataRow.createCell(contador), "I", cellStyleList);
        contador++;
        return contador;
    }

    protected String setAbstractGenerateInsertExcelSXLSX(MtrInformacionNoticia bean) {
        String fechaS = "";
        String sqlInsert = "INSERT INTO mtr_informacion_noticia(";
        sqlInsert = sqlInsert + "mtr_informacion_noticia_id" + ", ";
        sqlInsert = sqlInsert + "archivo_id" + ", ";
        sqlInsert = sqlInsert + "archivo_nombre" + ", ";
        sqlInsert = sqlInsert + "contenido" + ", ";
        sqlInsert = sqlInsert + "fecha_caducidad" + ", ";
        sqlInsert = sqlInsert + "fecha_creacion" + ", ";
        sqlInsert = sqlInsert + "fecha_modificacion" + ", ";
        sqlInsert = sqlInsert + "fecha_publicacion" + ", ";
        sqlInsert = sqlInsert + "icon_text" + ", ";
        sqlInsert = sqlInsert + "ind_activo" + ", ";
        sqlInsert = sqlInsert + "ind_noticia_nuevo_proveedor" + ", ";
        sqlInsert = sqlInsert + "ruta_adjunto" + ", ";
        sqlInsert = sqlInsert + "texto_info" + ", ";
        sqlInsert = sqlInsert + "titulo" + ", ";
        sqlInsert = sqlInsert + "usuario_creacion" + ", ";
        sqlInsert = sqlInsert + "usuario_modificacion" + ", ";
        sqlInsert = sqlInsert + "mtr_tipo_informacion_noticia_id" + ")";
        sqlInsert = sqlInsert + " VALUES (";
        sqlInsert = sqlInsert + bean.getId() + ", ";
        if (StringUtils.isBlank(bean.getArchivoId())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getArchivoId() + "'" + ", ";
        }
        if (StringUtils.isBlank(bean.getArchivoNombre())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getArchivoNombre() + "'" + ", ";
        }
        if (StringUtils.isBlank(bean.getContenido())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getContenido() + "'" + ", ";
        }
        if (bean.getFechaCaducidad() != null) {
            fechaS = DateUtils.convertDateToString("dd/MM/yyyy HH:mm:ss", bean.getFechaCaducidad());
            sqlInsert = sqlInsert + "to_date('" + fechaS + "','dd/MM/yyyy HH:mm:ss')" + ", ";
        } else {
            sqlInsert = sqlInsert + "null" + ", ";
        }
        if (bean.getFechaCreacion() != null) {
            fechaS = DateUtils.convertDateToString("dd/MM/yyyy HH:mm:ss", bean.getFechaCreacion());
            sqlInsert = sqlInsert + "to_date('" + fechaS + "','dd/MM/yyyy HH:mm:ss')" + ", ";
        } else {
            sqlInsert = sqlInsert + "null" + ", ";
        }
        if (bean.getFechaModificacion() != null) {
            fechaS = DateUtils.convertDateToString("dd/MM/yyyy HH:mm:ss", bean.getFechaModificacion());
            sqlInsert = sqlInsert + "to_date('" + fechaS + "','dd/MM/yyyy HH:mm:ss')" + ", ";
        } else {
            sqlInsert = sqlInsert + "null" + ", ";
        }
        if (bean.getFechaPublicacion() != null) {
            fechaS = DateUtils.convertDateToString("dd/MM/yyyy HH:mm:ss", bean.getFechaPublicacion());
            sqlInsert = sqlInsert + "to_date('" + fechaS + "','dd/MM/yyyy HH:mm:ss')" + ", ";
        } else {
            sqlInsert = sqlInsert + "null" + ", ";
        }
        if (StringUtils.isBlank(bean.getIconText())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getIconText() + "'" + ", ";
        }
        if (StringUtils.isBlank(bean.getIndActivo())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getIndActivo() + "'" + ", ";
        }
        if (StringUtils.isBlank(bean.getIndNoticiaNuevoProveedor())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getIndNoticiaNuevoProveedor() + "'" + ", ";
        }
        if (StringUtils.isBlank(bean.getRutaAdjunto())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getRutaAdjunto() + "'" + ", ";
        }
        if (StringUtils.isBlank(bean.getTextoInfo())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getTextoInfo() + "'" + ", ";
        }
        if (StringUtils.isBlank(bean.getTitulo())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getTitulo() + "'" + ", ";
        }
        sqlInsert = sqlInsert + bean.getUsuarioCreacion() + ", ";
        sqlInsert = sqlInsert + bean.getUsuarioModificacion() + ", ";
        sqlInsert = sqlInsert + bean.getMtrTipoInformacionNoticia().getId();
        sqlInsert = sqlInsert + " );";
        return sqlInsert;
    }

    /*****************/
    /* Otros Metodos */
    /*****************/

    public Long countByMtrTipoInformacionNoticia(MtrTipoInformacionNoticia mtrTipoInformacionNoticia) {
        return this.mtrInformacionNoticiaDeltaRepository.countByMtrTipoInformacionNoticia(mtrTipoInformacionNoticia);
    }

    public GraphBean graphByMtrTipoInformacionNoticia() {
        List<MtrTipoInformacionNoticia> lista = this.mtrTipoInformacionNoticiaDeltaRepository.findAll();
        if (lista == null || lista.size() <= 0) {
            return null;
        }
        GraphBean graphBean = new GraphBean();

        /* Obteniendo Pie Chart */
        GraphPieChart graphPieChart = new GraphPieChart();
        GraphDataset graphDataset = new GraphDataset();
        GraphDataset[] listaGraphDataset = new GraphDataset[1];
        Long[] data = new Long[lista.size()];
        String[] labels = new String[lista.size()];
        String[] backgroundColor = new String[lista.size()];
        String[] hoverBackgroundColor = new String[lista.size()];
        String[] borderColor = new String[lista.size()];
        String[] hoverBorderColor = new String[lista.size()];
        int contador = 0;
        int contadorColor = 0;
        for (MtrTipoInformacionNoticia bean : lista) {
            Long valor = this.mtrInformacionNoticiaDeltaRepository.countByMtrTipoInformacionNoticia(bean);
            String descripcion = this.setGraphDescripcionByMtrTipoInformacionNoticia(bean);
            data[contador] = valor;
            labels[contador] = descripcion;
            backgroundColor[contador] = Constants.BACKGROUND_COLOR_GRAPH[contadorColor];
            hoverBackgroundColor[contador] = Constants.HOVER_BACKGROUND_COLOR_GRAPH[contadorColor];
            borderColor[contador] = Constants.BACKGROUND_COLOR_GRAPH[contadorColor];
            hoverBorderColor[contador] = Constants.HOVER_BACKGROUND_COLOR_GRAPH[contadorColor];
            contador++;
            contadorColor++;
            if (contadorColor >= REGISTROS_COLOR) {
                contadorColor = 1;
            }
        }
        String titulo = this.setGraphPieChartTituloByMtrTipoInformacionNoticia();
        graphDataset.setLabel(titulo);
        graphDataset.setData(data);
        graphDataset.setBackgroundColor(backgroundColor);
        graphDataset.setHoverBackgroundColor(hoverBackgroundColor);
        listaGraphDataset[0] = graphDataset;
        graphPieChart.setDatasets(listaGraphDataset);
        graphPieChart.setLabels(labels);
        graphBean.setPiechart(graphPieChart);

        GraphBarChart graphBarChart = new GraphBarChart();
        GraphDataset[] listaGraphDatasetBar = new GraphDataset[lista.size()];
        String[] labelsBar = new String[1];
        labelsBar[0] = this.setGraphBarChartTituloByMtrTipoInformacionNoticia();
        contador = 0;
        contadorColor = 0;
        for (MtrTipoInformacionNoticia bean : lista) {
            Long valor = this.mtrInformacionNoticiaDeltaRepository.countByMtrTipoInformacionNoticia(bean);
            String descripcion = this.setGraphDescripcionByMtrTipoInformacionNoticia(bean);
            GraphDataset graphDatasetBar = new GraphDataset();
            Long[] dataBar = new Long[1];

            String[] backgroundColorBar = new String[1];
            String[] hoverBackgroundColorBar = new String[1];
            String[] borderColorBar = new String[1];
            String[] hoverBorderColorBar = new String[1];

            dataBar[0] = valor;
            backgroundColorBar[0] = Constants.BACKGROUND_COLOR_GRAPH[contadorColor];
            hoverBackgroundColorBar[0] = Constants.HOVER_BACKGROUND_COLOR_GRAPH[contadorColor];
            borderColorBar[0] = Constants.BACKGROUND_COLOR_GRAPH[contadorColor];
            hoverBorderColorBar[0] = Constants.HOVER_BACKGROUND_COLOR_GRAPH[contadorColor];

            graphDatasetBar.setLabel(descripcion);
            graphDatasetBar.setData(dataBar);
            graphDatasetBar.setBackgroundColor(backgroundColorBar);
            graphDatasetBar.setHoverBackgroundColor(hoverBackgroundColorBar);
            graphDatasetBar.setBorderColor(borderColorBar);
            graphDatasetBar.setHoverBorderColor(hoverBorderColorBar);
            listaGraphDatasetBar[contador] = graphDatasetBar;

            contador++;
            contadorColor++;
            if (contadorColor >= REGISTROS_COLOR) {
                contadorColor = 1;
            }

        }
        graphBarChart.setDatasets(listaGraphDatasetBar);
        graphBarChart.setLabels(labelsBar);
        graphBean.setBarchart(graphBarChart);
        return graphBean;
    }

    protected String setGraphDescripcionByMtrTipoInformacionNoticia(MtrTipoInformacionNoticia mtrTipoInformacionNoticia) {
        return mtrTipoInformacionNoticia.getId().toString();
    }

    protected String setGraphPieChartTituloByMtrTipoInformacionNoticia() {
        return "MtrTipoInformacionNoticia";
    }

    protected String setGraphBarChartTituloByMtrTipoInformacionNoticia() {
        return "MtrTipoInformacionNoticia";
    }

}
