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
import com.incloud.hcp.domain.MtrSector;
import com.incloud.hcp.domain.MtrSector_;
import com.incloud.hcp.domain.MtrSociedad;
import com.incloud.hcp.domain.response.MtrSectorResponse;
import com.incloud.hcp.exception.ServiceException;
import com.incloud.hcp.repository.delta.AppParametriaDeltaRepository;
import com.incloud.hcp.repository.delta.MtrSectorDeltaRepository;
import com.incloud.hcp.repository.delta.MtrSociedadDeltaRepository;
import com.incloud.hcp.service.MtrSectorService;
import com.incloud.hcp.service._framework.bean.BeanCargaMasivoDTO;
import com.incloud.hcp.service._framework.bean.BeanListaMasivoDTO;
import com.incloud.hcp.service._framework.impl.JPACustomServiceImpl;
import com.incloud.hcp.service.delta.MtrSociedadDeltaService;
import com.incloud.hcp.service.requireNew.MtrSectorRequireNewService;
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
 * A simple DTO Facility for MtrSector.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public abstract class MtrSectorServiceImpl extends JPACustomServiceImpl<MtrSectorResponse, MtrSector, Integer> implements MtrSectorService {

    protected final String NAME_SHEET = "MtrSector";
    protected final String CONFIG_TITLE = "com/incloud/hcp/excel/MtrSectorExcel.xml";
    private final Integer REGISTROS_COLOR = 10;

    @Autowired
    protected AppParametriaDeltaRepository appParametriaDeltaRepository;

    @Autowired
    protected MtrSectorDeltaRepository mtrSectorDeltaRepository;

    @Autowired
    protected MtrSectorRequireNewService mtrSectorRequireNewService;

    @Autowired
    protected MtrSociedadDeltaService mtrSociedadDeltaService;

    @Autowired
    protected MtrSociedadDeltaRepository mtrSociedadDeltaRepository;

    /*****************************/
    /* Metodos de Busqueda       */
    /*****************************/

    protected final ExampleMatcher setAbstractFind(ExampleMatcher matcher) {
        matcher = ExampleMatcher.matching() //
                .withMatcher(MtrSector_.codigoSector.getName(), match -> match.ignoreCase().startsWith())
                .withMatcher(MtrSector_.descripcion.getName(), match -> match.ignoreCase().startsWith());
        return matcher;
    }

    protected abstract Sort setFindAll(Sort sort);

    protected abstract Sort setFind(MtrSector req, ExampleMatcher matcher, Example<MtrSector> example, Sort sort);

    protected abstract void setFindPaginated(PageRequestByExample<MtrSector> req, ExampleMatcher matcher, Example<MtrSector> example);

    protected final MtrSector setObtenerBeanResponse(MtrSectorResponse bean) {
        return bean.getBean();
    }

    protected final Class<MtrSector> setObtenerClassBean() {
        return MtrSector.class;
    }

    protected List<Predicate> setAbstractPredicate(MtrSectorResponse bean, CriteriaBuilder cb, Root<MtrSector> root) {
        List<Predicate> predicates = new ArrayList<>();
        MtrSector entity = bean.getBean();
        /* Obtener condiciones */
        PredicateUtils.addPredicates(predicates, bean.getIdCondicion(), "id", entity.getId(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getCodigoSectorCondicion(), "codigoSector", entity.getCodigoSector(), cb, root);
        PredicateUtils.addPredicates(predicates, bean.getDescripcionCondicion(), "descripcion", entity.getDescripcion(), cb, root);
        //PredicateUtils.addPredicates(predicates, bean.getMtrSociedadCondicion(), "mtrSociedad", entity.getMtrSociedad(), cb, root);
        /* Obtener valores de Lista */
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "id", bean.getIdList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "codigoSector", bean.getCodigoSectorList(), cb, root);
        PredicateUtils.addPredicatesListValorPrimitivo(predicates, "descripcion", bean.getDescripcionList(), cb, root);
        //PredicateUtils.addPredicatesListValorBean(predicates, "mtrSociedad", bean.getMtrSociedadList(), cb, root);
        return predicates;
    }

    /****************/
    /* METODOS CRUD */
    /****************/

    protected final String validacionesPrevias(MtrSector bean) throws Exception {
        String mensaje = "";
        if (!Optional.of(bean.getCodigoSector()).isPresent()) {
            String msg = this.messageSource.getMessage("message.mtrSector.codigoSector.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        if (!Optional.of(bean.getDescripcion()).isPresent()) {
            String msg = this.messageSource.getMessage("message.mtrSector.descripcion.requerido", null, LocaleContextHolder.getLocale());
            mensaje += "* " + msg + "<br/>";
        }
        String msg = this.setValidacionesPrevias(bean);
        if (StringUtils.isNotBlank(msg)) {
            mensaje += "* " + msg + "<br/>";
        }
        return mensaje;
    }

    protected String setValidacionesPrevias(MtrSector bean) throws Exception {
        String mensaje = "";
        return mensaje;
    }

    protected String validacionesPreviasCreate(MtrSector bean) throws Exception {
        String msg = null;
        MtrSector validar = null;
        return msg;
    }

    protected String validacionesPreviasSave(MtrSector bean) throws Exception {
        String msg = null;
        MtrSector validar = null;
        return msg;
    }

    protected MtrSector completarDatosBean(MtrSector bean) throws Exception {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected MtrSector setCompletarDatosBean(MtrSector bean) throws Exception {
        return bean;
    }

    protected final MtrSector setAbstractCreate(MtrSector dto) throws Exception {
        MtrSector bean = new MtrSector();
        bean = (MtrSector) BeanUtils.cloneBean(dto);
        return bean;
    }

    /************************/
    /* METODOS CRUD Masivos */
    /************************/

    protected MtrSector setUploadExcel(Cell currentCell, MtrSector mtrSector, int contador) throws Exception {
        Double valorDouble = new Double(0);
        BigDecimal valorDecimal = new BigDecimal(0);
        Boolean valorBoolean = new Boolean(true);
        String valorCadena = "";
        switch (contador) {
        case 1:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 10) {
                    throw new ServiceException("Valor Campo codigoSector contiene mas de 10 caracter(es)");
                }
                mtrSector.setCodigoSector(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo codigoSector está en formato incorrecto");
            }
            break;
        case 2:
            try {
                valorCadena = currentCell.getStringCellValue();
                if (valorCadena.length() > 100) {
                    throw new ServiceException("Valor Campo descripcion contiene mas de 100 caracter(es)");
                }
                mtrSector.setDescripcion(valorCadena);
            } catch (Exception e) {
                throw new ServiceException("Valor Campo descripcion está en formato incorrecto");
            }
            break;
        default:
            break;
        }
        return mtrSector;
    }

    protected AppParametria setObtenerRegistroConfiguracionUploadExcel() {
        AppParametria appParametriaData = this.appParametriaDeltaRepository.getByModuloAndLabelAndStatus(AppParametriaModuloEnum.CARGA_EXCEL.getEstado(),
                AppParametriaLabelEnum.INICIO_REGISTRO_DATA.getEstado(), Constants.ESTADO_ACTIVO);
        return appParametriaData;
    }

    protected MtrSector setInicializarBeanUpdateExcel() {
        MtrSector bean = new MtrSector();
        bean.setId(null);
        return bean;
    }

    protected final Integer setObtenerId(MtrSector bean) {
        return bean.getId();
    }

    /************************/
    /* Instancia de Bean    */
    /************************/

    protected final MtrSector createInstance() {
        MtrSector mtrSector = new MtrSector();
        return mtrSector;
    }

    protected final BeanCargaMasivoDTO<MtrSector> createInstanceMasivoDTO() {
        BeanCargaMasivoDTO<MtrSector> beanCargaMasivoDTO = new BeanCargaMasivoDTO<MtrSector>();
        return beanCargaMasivoDTO;

    }

    protected final BeanListaMasivoDTO<BeanCargaMasivoDTO<MtrSector>> createInstanceListaMasivoDTO() {
        BeanListaMasivoDTO<BeanCargaMasivoDTO<MtrSector>> beanCargaMasivoDTOBeanListaMasivoDTO = new BeanListaMasivoDTO<BeanCargaMasivoDTO<MtrSector>>();
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

    protected int setAbstractDownloadExcel(MtrSector bean, HSSFRow dataRow) {
        int contador = 0;
        ExcelDefault.setValueCell(bean.getId(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getCodigoSector(), dataRow.createCell(contador));
        contador++;
        ExcelDefault.setValueCell(bean.getDescripcion(), dataRow.createCell(contador));
        contador++;
        return contador;
    }

    protected int setAbstractDownloadExcelSXLSX(MtrSector bean, Row dataRow, List<CellStyle> cellStyleList) {
        int contador = 0;
        ExcelDefault.setValueCell(bean.getId(), dataRow.createCell(contador), "I", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getCodigoSector(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        ExcelDefault.setValueCell(bean.getDescripcion(), dataRow.createCell(contador), "S", cellStyleList);
        contador++;
        return contador;
    }

    protected String setAbstractGenerateInsertExcelSXLSX(MtrSector bean) {
        String fechaS = "";
        String sqlInsert = "INSERT INTO mtr_sector(";
        sqlInsert = sqlInsert + "mtr_sector_id" + ", ";
        sqlInsert = sqlInsert + "codigo_sector" + ", ";
        sqlInsert = sqlInsert + "descripcion" + ", ";
        sqlInsert = sqlInsert + "mtr_sociedad_id" + ")";
        sqlInsert = sqlInsert + " VALUES (";
        sqlInsert = sqlInsert + bean.getId() + ", ";
        if (StringUtils.isBlank(bean.getCodigoSector())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getCodigoSector() + "'" + ", ";
        }
        if (StringUtils.isBlank(bean.getDescripcion())) {
            sqlInsert = sqlInsert + "null" + ", ";
        } else {
            sqlInsert = sqlInsert + "'" + bean.getDescripcion() + "'" + ", ";
        }
        sqlInsert = sqlInsert + bean.getMtrSociedad().getId();
        sqlInsert = sqlInsert + " );";
        return sqlInsert;
    }

    /*****************/
    /* Otros Metodos */
    /*****************/

    public Long countByMtrSociedad(MtrSociedad mtrSociedad) {
        return this.mtrSectorDeltaRepository.countByMtrSociedad(mtrSociedad);
    }

    public GraphBean graphByMtrSociedad() {
        List<MtrSociedad> lista = this.mtrSociedadDeltaRepository.findAll();
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
        for (MtrSociedad bean : lista) {
            Long valor = this.mtrSectorDeltaRepository.countByMtrSociedad(bean);
            String descripcion = this.setGraphDescripcionByMtrSociedad(bean);
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
        String titulo = this.setGraphPieChartTituloByMtrSociedad();
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
        labelsBar[0] = this.setGraphBarChartTituloByMtrSociedad();
        contador = 0;
        contadorColor = 0;
        for (MtrSociedad bean : lista) {
            Long valor = this.mtrSectorDeltaRepository.countByMtrSociedad(bean);
            String descripcion = this.setGraphDescripcionByMtrSociedad(bean);
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

    protected String setGraphDescripcionByMtrSociedad(MtrSociedad mtrSociedad) {
        return mtrSociedad.getId().toString();
    }

    protected String setGraphPieChartTituloByMtrSociedad() {
        return "MtrSociedad";
    }

    protected String setGraphBarChartTituloByMtrSociedad() {
        return "MtrSociedad";
    }

}