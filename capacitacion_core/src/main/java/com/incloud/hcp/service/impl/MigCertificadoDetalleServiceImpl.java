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
import com.incloud.hcp.domain.MigCertificado;
import com.incloud.hcp.domain.MigCertificadoDetalle;
import com.incloud.hcp.domain.MigCertificadoDetalle_;
import com.incloud.hcp.domain.response.MigCertificadoDetalleResponse;
import com.incloud.hcp.exception.ServiceException;
import com.incloud.hcp.repository.delta.AppParametriaDeltaRepository;
import com.incloud.hcp.repository.delta.MigCertificadoDeltaRepository;
import com.incloud.hcp.repository.delta.MigCertificadoDetalleDeltaRepository;
import com.incloud.hcp.service.MigCertificadoDetalleService;
import com.incloud.hcp.service._framework.bean.BeanCargaMasivoDTO;
import com.incloud.hcp.service._framework.bean.BeanListaMasivoDTO;
import com.incloud.hcp.service._framework.impl.JPACustomServiceImpl;
import com.incloud.hcp.service.delta.MigCertificadoDeltaService;
import com.incloud.hcp.service.requireNew.MigCertificadoDetalleRequireNewService;
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
 * A simple DTO Facility for MigCertificadoDetalle.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public abstract class MigCertificadoDetalleServiceImpl extends JPACustomServiceImpl<MigCertificadoDetalleResponse, MigCertificadoDetalle, Integer>
        implements MigCertificadoDetalleService {

    protected final String NAME_SHEET = "MigCertificadoDetalle";
    protected final String CONFIG_TITLE = "com/incloud/hcp/excel/MigCertificadoDetalleExcel.xml";
    private final Integer REGISTROS_COLOR = 10;

    @Autowired
    protected AppParametriaDeltaRepository appParametriaDeltaRepository;

    @Autowired
    protected MigCertificadoDetalleDeltaRepository migCertificadoDetalleDeltaRepository;

    @Autowired
    protected MigCertificadoDetalleRequireNewService migCertificadoDetalleRequireNewService;

    @Autowired
    protected MigCertificadoDeltaService migCertificadoDeltaService;

    @Autowired
    protected MigCertificadoDeltaRepository migCertificadoDeltaRepository;

    /*****************************/
    /* Metodos de Busqueda       */
    /*****************************/

    protected final ExampleMatcher setAbstractFind(ExampleMatcher matcher) {
        matcher = ExampleMatcher.matching() //
                .withMatcher(MigCertificadoDetalle_.moneda.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MigCertificadoDetalle_.descripcion.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MigCertificadoDetalle_.unidadMedida.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MigCertificadoDetalle_.cuentaContable.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MigCertificadoDetalle_.ccAfeOrden.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MigCertificadoDetalle_.hojaServicio.getName(), match -> match.ignoreCase().startsWith());
        return matcher;
    }

    protected abstract Sort setFindAll(Sort sort);

    protected abstract Sort setFind(MigCertificadoDetalle req, ExampleMatcher matcher, Example<MigCertificadoDetalle> example, Sort sort);

    protected abstract void setFindPaginated(PageRequestByExample<MigCertificadoDetalle> req, ExampleMatcher matcher, Example<MigCertificadoDetalle> example);

    protected final MigCertificadoDetalle setObtenerBeanResponse(MigCertificadoDetalleResponse bean) {
        return bean.getBean();
    }

    protected final Class<MigCertificadoDetalle> setObtenerClassBean() {
        return MigCertificadoDetalle.class;
    }

    protected List<Predicate> setAbstractPredicate(MigCertificadoDetalleResponse bean, CriteriaBuilder cb, Root<MigCertificadoDetalle> root) {
        List<Predicate> predicates = new ArrayList<>();
        MigCertificadoDetalle entity = bean.getBean();
        /* Obtener condiciones */
        PredicateUtils.addPredicates(predicates, bean.getIdCondicion(), "id", entity.getId(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getPosicionCondicion(), "posicion", entity.getPosicion(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getMonedaCondicion(), "moneda", entity.getMoneda(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getDescripcionCondicion(), "descripcion", entity.getDescripcion(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getUnidadMedidaCondicion(), "unidadMedida", entity.getUnidadMedida(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getCuentaContableCondicion(), "cuentaContable", entity.getCuentaContable(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getCcAfeOrdenCondicion(), "ccAfeOrden", entity.getCcAfeOrden(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getPrecioUnitarioCondicion(), "precioUnitario", entity.getPrecioUnitario(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getCantidadEntregadaCondicion(), "cantidadEntregada", entity.getCantidadEntregada(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getTotalLineaCondicion(), "totalLinea", entity.getTotalLinea(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getHojaServicioCondicion(), "hojaServicio", entity.getHojaServicio(), cb, root);
        //PredicateUtils.addPredicates(predicates, bean.getMigCertificadoCondicion(), "migCertificado", entity.getMigCertificado(), cb, root);
        /* Obtener valores de Lista */
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "id", bean.getIdList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "posicion", bean.getPosicionList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "moneda", bean.getMonedaList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "descripcion", bean.getDescripcionList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "unidadMedida", bean.getUnidadMedidaList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "cuentaContable", bean.getCuentaContableList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "ccAfeOrden", bean.getCcAfeOrdenList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "precioUnitario", bean.getPrecioUnitarioList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "cantidadEntregada", bean.getCantidadEntregadaList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "totalLinea", bean.getTotalLineaList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "hojaServicio", bean.getHojaServicioList(), cb, root);
        //PredicateUtils.addPredicatesListValorBean(predicates, "migCertificado", bean.getMigCertificadoList(), cb, root);
        return predicates;
    }

    /****************/
    /* METODOS CRUD */
    /****************/

    protected final String validacionesPrevias(MigCertificadoDetalle bean) throws Exception {
        String mensaje = "";
        if (!Optional.of(bean.getPosicion()).isPresent()) {
            String msg = this.messageSource.getMessage("message.migCertificadoDetalle.posicion.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        if (!Optional.of(bean.getMoneda()).isPresent()) {
            String msg = this.messageSource.getMessage("message.migCertificadoDetalle.moneda.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        if (!Optional.of(bean.getDescripcion()).isPresent()) {
            String msg = this.messageSource.getMessage("message.migCertificadoDetalle.descripcion.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        if (!Optional.of(bean.getUnidadMedida()).isPresent()) {
            String msg = this.messageSource.getMessage("message.migCertificadoDetalle.unidadMedida.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        if (!Optional.of(bean.getPrecioUnitario()).isPresent()) {
            String msg = this.messageSource.getMessage("message.migCertificadoDetalle.precioUnitario.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        if (!Optional.of(bean.getCantidadEntregada()).isPresent()) {
            String msg = this.messageSource.getMessage("message.migCertificadoDetalle.cantidadEntregada.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        if (!Optional.of(bean.getTotalLinea()).isPresent()) {
            String msg = this.messageSource.getMessage("message.migCertificadoDetalle.totalLinea.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        String msg = this.setValidacionesPrevias(bean);
        if (StringUtils.isNotBlank(msg)) {
            mensaje += "* " + msg + "<br/>";
        }
        return mensaje;
    }

    protected String setValidacionesPrevias(MigCertificadoDetalle bean) throws Exception {
        String mensaje = "";
        return mensaje;
    }

    protected String validacionesPreviasCreate(MigCertificadoDetalle bean) throws Exception {
        String msg = null;
        MigCertificadoDetalle validar = null;
        return msg;
    }

    protected String validacionesPreviasSave(MigCertificadoDetalle bean) throws Exception {
        String msg = null;
        MigCertificadoDetalle validar = null;
        return msg;
    }

    protected MigCertificadoDetalle completarDatosBean(MigCertificadoDetalle bean) throws Exception {
        BigDecimal data = new BigDecimal(0.00);
        if (Optional.ofNullable(bean.getPrecioUnitario()).isPresent()) {
            bean.setPrecioUnitario(bean.getPrecioUnitario().setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        if (Optional.ofNullable(bean.getCantidadEntregada()).isPresent()) {
            bean.setCantidadEntregada(bean.getCantidadEntregada().setScale(4, BigDecimal.ROUND_HALF_UP));
        }
        if (Optional.ofNullable(bean.getTotalLinea()).isPresent()) {
            bean.setTotalLinea(bean.getTotalLinea().setScale(4, BigDecimal.ROUND_HALF_UP));
        }
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected MigCertificadoDetalle setCompletarDatosBean(MigCertificadoDetalle bean) throws Exception {
        return bean;
    }

    protected final MigCertificadoDetalle setAbstractCreate(MigCertificadoDetalle dto) throws Exception {
        MigCertificadoDetalle bean = new MigCertificadoDetalle();
        bean = (MigCertificadoDetalle) BeanUtils.cloneBean(dto);
        return bean;
    }

    /************************/
    /* METODOS CRUD Masivos */
    /************************/

    protected MigCertificadoDetalle setUploadExcel(Cell currentCell, MigCertificadoDetalle migCertificadoDetalle, int contador) throws Exception {
        Double valorDouble = new Double(0);
        BigDecimal valorDecimal = new BigDecimal(0);
        Boolean valorBoolean = new Boolean(true);
        String valorCadena = "";
        switch (contador) {
        case 1:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 10) {
                    throw new ServiceException("Valor Campo moneda contiene mas de 20 caracter(es)");
                }
                migCertificadoDetalle.setPosicion(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo posicion está en formato incorrecto");
            }
            break;
        case 2:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 20) {
                    throw new ServiceException("Valor Campo moneda contiene mas de 20 caracter(es)");
                }
                migCertificadoDetalle.setMoneda(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo moneda está en formato incorrecto");
            }
            break;
        case 3:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 200) {
                    throw new ServiceException("Valor Campo descripcion contiene mas de 200 caracter(es)");
                }
                migCertificadoDetalle.setDescripcion(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo descripcion está en formato incorrecto");
            }
            break;
        case 4:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 10) {
                    throw new ServiceException("Valor Campo unidadMedida contiene mas de 10 caracter(es)");
                }
                migCertificadoDetalle.setUnidadMedida(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo unidadMedida está en formato incorrecto");
            }
            break;
        case 5:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 10) {
                    throw new ServiceException("Valor Campo cuentaContable contiene mas de 10 caracter(es)");
                }
                migCertificadoDetalle.setCuentaContable(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo cuentaContable está en formato incorrecto");
            }
            break;
        case 6:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 10) {
                    throw new ServiceException("Valor Campo ccAfeOrden contiene mas de 10 caracter(es)");
                }
                migCertificadoDetalle.setCcAfeOrden(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo ccAfeOrden está en formato incorrecto");
            }
            break;
        case 7:
            try {
                valorDecimal = new BigDecimal(currentCell.getNumericCellValue());
                migCertificadoDetalle.setPrecioUnitario(valorDecimal);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo precioUnitario está en formato incorrecto");
            }
            break;
        case 8:
            try {
                valorDecimal = new BigDecimal(currentCell.getNumericCellValue());
                migCertificadoDetalle.setCantidadEntregada(valorDecimal);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo cantidadEntregada está en formato incorrecto");
            }
            break;
        case 9:
            try {
                valorDecimal = new BigDecimal(currentCell.getNumericCellValue());
                migCertificadoDetalle.setTotalLinea(valorDecimal);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo totalLinea está en formato incorrecto");
            }
            break;
        case 10:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 100) {
                    throw new ServiceException("Valor Campo hojaServicio contiene mas de 100 caracter(es)");
                }
                migCertificadoDetalle.setHojaServicio(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo hojaServicio está en formato incorrecto");
            }
            break;
        default:
            break;
        }
        return migCertificadoDetalle;
    }

    protected AppParametria setObtenerRegistroConfiguracionUploadExcel() {
        AppParametria appParametriaData = this.appParametriaDeltaRepository.getByModuloAndLabelAndStatus(AppParametriaModuloEnum.CARGA_EXCEL.getEstado(),
                AppParametriaLabelEnum.INICIO_REGISTRO_DATA.getEstado(), Constants.ESTADO_ACTIVO);
        return appParametriaData;
    }

    protected MigCertificadoDetalle setInicializarBeanUpdateExcel() {
        MigCertificadoDetalle bean = new MigCertificadoDetalle();
        bean.setId(null);
        return bean;
    }

    protected final Integer setObtenerId(MigCertificadoDetalle bean) {
        return bean.getId();
    }

    /************************/
    /* Instancia de Bean    */
    /************************/

    protected final MigCertificadoDetalle createInstance() {
        MigCertificadoDetalle migCertificadoDetalle = new MigCertificadoDetalle();
        return migCertificadoDetalle;
    }

    protected final BeanCargaMasivoDTO<MigCertificadoDetalle> createInstanceMasivoDTO() {
        BeanCargaMasivoDTO<MigCertificadoDetalle> beanCargaMasivoDTO = new BeanCargaMasivoDTO<MigCertificadoDetalle>();
        return beanCargaMasivoDTO;

    }

    protected final BeanListaMasivoDTO<BeanCargaMasivoDTO<MigCertificadoDetalle>> createInstanceListaMasivoDTO() {
        BeanListaMasivoDTO<BeanCargaMasivoDTO<MigCertificadoDetalle>> beanCargaMasivoDTOBeanListaMasivoDTO = new BeanListaMasivoDTO<BeanCargaMasivoDTO<MigCertificadoDetalle>>();
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

    protected int setAbstractDownloadExcel(MigCertificadoDetalle bean, HSSFRow dataRow) {
        int contador = 0;
        ExcelDefault.setValueCell(bean.getId(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getPosicion(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getMoneda(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getDescripcion(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getUnidadMedida(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getCuentaContable(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getCcAfeOrden(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getPrecioUnitario(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getCantidadEntregada(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getTotalLinea(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getHojaServicio(), dataRow.createCell(contador));
        contador++;
        return contador;
    }

    protected int setAbstractDownloadExcelSXLSX(MigCertificadoDetalle bean, Row dataRow, List<CellStyle> cellStyleList) {
        int contador = 0;
        ExcelDefault.setValueCell(bean.getId(), dataRow.createCell(contador), "I", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getPosicion(), dataRow.createCell(contador), "I", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getMoneda(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getDescripcion(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getUnidadMedida(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getCuentaContable(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getCcAfeOrden(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getPrecioUnitario(), dataRow.createCell(contador), "N", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getCantidadEntregada(), dataRow.createCell(contador), "N", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getTotalLinea(), dataRow.createCell(contador), "N", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getHojaServicio(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        return contador;
    }

    protected String setAbstractGenerateInsertExcelSXLSX(MigCertificadoDetalle bean) {
        String fechaS = "";
        String sqlInsert = "INSERT INTO mig_certificado_detalle(";
        sqlInsert = sqlInsert + "mig_certificado_detalle_id" + ", ";
        sqlInsert = sqlInsert + "posicion" + ", ";
        sqlInsert = sqlInsert + "moneda" + ", ";
        sqlInsert = sqlInsert + "descripcion" + ", ";
        sqlInsert = sqlInsert + "unidad_medida" + ", ";
        sqlInsert = sqlInsert + "cuenta_contable" + ", ";
        sqlInsert = sqlInsert + "cc_afe_orden" + ", ";
        sqlInsert = sqlInsert + "precio_unitario" + ", ";
        sqlInsert = sqlInsert + "cantidad_entregada" + ", ";
        sqlInsert = sqlInsert + "total_linea" + ", ";
        sqlInsert = sqlInsert + "hoja_servicio" + ", ";
        sqlInsert = sqlInsert + "mig_certificado_id" + ")";
        sqlInsert = sqlInsert + " VALUES (";
        sqlInsert = sqlInsert + bean.getId() + ", ";
        sqlInsert = sqlInsert + bean.getPosicion() + ", ";
        if (StringUtils.isBlank(bean.getMoneda())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getMoneda() + "'" + ", ";
        }
        if (StringUtils.isBlank(bean.getDescripcion())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getDescripcion() + "'" + ", ";
        }
        if (StringUtils.isBlank(bean.getUnidadMedida())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getUnidadMedida() + "'" + ", ";
        }
        if (StringUtils.isBlank(bean.getCuentaContable())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getCuentaContable() + "'" + ", ";
        }
        if (StringUtils.isBlank(bean.getCcAfeOrden())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getCcAfeOrden() + "'" + ", ";
        }
        sqlInsert = sqlInsert + bean.getPrecioUnitario() + ", ";
        sqlInsert = sqlInsert + bean.getCantidadEntregada() + ", ";
        sqlInsert = sqlInsert + bean.getTotalLinea() + ", ";
        if (StringUtils.isBlank(bean.getHojaServicio())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getHojaServicio() + "'" + ", ";
        }
        sqlInsert = sqlInsert + bean.getMigCertificado().getId();
        sqlInsert = sqlInsert + " );";
        return sqlInsert;
    }

    /*****************/
    /* Otros Metodos */
    /*****************/

    public Long countByMigCertificado(MigCertificado migCertificado) {
        return this.migCertificadoDetalleDeltaRepository.countByMigCertificado(migCertificado);
    }

    public GraphBean graphByMigCertificado() {
        List<MigCertificado> lista = this.migCertificadoDeltaRepository.findAll();
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
        for (MigCertificado bean : lista) {
            Long valor = this.migCertificadoDetalleDeltaRepository.countByMigCertificado(bean);
            String descripcion = this.setGraphDescripcionByMigCertificado(bean);
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
        String titulo = this.setGraphPieChartTituloByMigCertificado();
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
        labelsBar[0] = this.setGraphBarChartTituloByMigCertificado();
        contador = 0;
        contadorColor = 0;
        for (MigCertificado bean : lista) {
            Long valor = this.migCertificadoDetalleDeltaRepository.countByMigCertificado(bean);
            String descripcion = this.setGraphDescripcionByMigCertificado(bean);
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

    protected String setGraphDescripcionByMigCertificado(MigCertificado migCertificado) {
        return migCertificado.getId().toString();
    }

    protected String setGraphPieChartTituloByMigCertificado() {
        return "MigCertificado";
    }

    protected String setGraphBarChartTituloByMigCertificado() {
        return "MigCertificado";
    }

}