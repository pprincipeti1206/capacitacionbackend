/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/dto/EntityDTOService.java.e.vm
 */
package com.incloud.hcp.service._gproveedor.impl;

import com.incloud.hcp.common.excel.ExcelDefault;
import com.incloud.hcp.domain._gproveedor.domain.ProveedorValidacionInfoDetalle;
import com.incloud.hcp.domain._gproveedor.domain.ProveedorValidacionInfoDetalle_;
import com.incloud.hcp.repository._gproveedor.ProveedorValidacionInfoDetalleRepository;
import com.incloud.hcp.repository._gproveedor.ProveedorValidacionInfoRepository;
import com.incloud.hcp.service._gproveedor.ProveedorValidacionInfoDetalleService;
import com.incloud.hcp.service._gproveedor.ProveedorValidacionInfoService;
import com.incloud.hcp.service.support.PageRequestByExample;
import com.incloud.hcp.service.support.PageResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



/**
 * A simple DTO Facility for ProveedorValidacionInfoDetalle.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public abstract class ProveedorValidacionInfoDetalleServiceImpl implements ProveedorValidacionInfoDetalleService {

    protected final Logger log = LoggerFactory.getLogger(ProveedorValidacionInfoDetalleServiceImpl.class);

    protected final String NAME_SHEET = "ProveedorValidacionInfoDetalle";
    protected final String CONFIG_TITLE = "com/incloud/hcp/excel/ProveedorValidacionInfoDetalleExcel.xml";

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected ProveedorValidacionInfoDetalleRepository proveedorValidacionInfoDetalleRepository;

    @Autowired
    protected ProveedorValidacionInfoService proveedorValidacionInfoService;

    @Autowired
    protected ProveedorValidacionInfoRepository proveedorValidacionInfoRepository;

    @Transactional(readOnly = true)
    public Optional<ProveedorValidacionInfoDetalle> findOne(Integer id) {
        log.debug("Ingresando findOne: ", id);
        return this.proveedorValidacionInfoDetalleRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ProveedorValidacionInfoDetalle> findAll() {
        log.debug("Ingresando findAll");
        Sort sort = Sort.by("id");
        sort = this.setFindAll(sort);
        List<ProveedorValidacionInfoDetalle> lista = this.proveedorValidacionInfoDetalleRepository.findAll(sort);
        return lista;
    }

    protected abstract Sort setFindAll(Sort sort);

    @Transactional(readOnly = true)
    public List<ProveedorValidacionInfoDetalle> find(ProveedorValidacionInfoDetalle req) {
        log.debug("Ingresando find: ", req);
        Example<ProveedorValidacionInfoDetalle> example = null;
        ProveedorValidacionInfoDetalle proveedorValidacionInfoDetalle = req;
        ExampleMatcher matcher = null;
        if (proveedorValidacionInfoDetalle != null) {
            matcher = ExampleMatcher.matching() //
                    .withMatcher(ProveedorValidacionInfoDetalle_.nombreCampoBd.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorValidacionInfoDetalle_.indEsLista.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorValidacionInfoDetalle_.valorActual.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorValidacionInfoDetalle_.valorNuevo.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorValidacionInfoDetalle_.valorListaActual.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorValidacionInfoDetalle_.valorListaNuevo.getName(), match -> match.ignoreCase().startsWith());
            example = Example.of(proveedorValidacionInfoDetalle, matcher);
        }
        Sort sort = Sort.by("id");
        sort = this.setFind(req, matcher, example, sort);
        List<ProveedorValidacionInfoDetalle> lista = this.proveedorValidacionInfoDetalleRepository.findAll(example, sort);
        return lista;
    }

    protected abstract Sort setFind(ProveedorValidacionInfoDetalle req, ExampleMatcher matcher, Example<ProveedorValidacionInfoDetalle> example, Sort sort);

    @Transactional(readOnly = true)
    public PageResponse<ProveedorValidacionInfoDetalle> findPaginated(PageRequestByExample<ProveedorValidacionInfoDetalle> req) {
        log.debug("Ingresando findPaginated: ", req);
        Example<ProveedorValidacionInfoDetalle> example = null;
        ProveedorValidacionInfoDetalle proveedorValidacionInfoDetalle = toEntity(req.bean);
        ExampleMatcher matcher = null;
        if (proveedorValidacionInfoDetalle != null) {
            matcher = ExampleMatcher.matching() //
                    .withMatcher(ProveedorValidacionInfoDetalle_.nombreCampoBd.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorValidacionInfoDetalle_.indEsLista.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorValidacionInfoDetalle_.valorActual.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorValidacionInfoDetalle_.valorNuevo.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorValidacionInfoDetalle_.valorListaActual.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorValidacionInfoDetalle_.valorListaNuevo.getName(), match -> match.ignoreCase().startsWith());
            example = Example.of(proveedorValidacionInfoDetalle, matcher);
        }

        Page<ProveedorValidacionInfoDetalle> page;
        Sort sort = Sort.by("id");
        sort = this.setFind(proveedorValidacionInfoDetalle, matcher, example, sort);
        req.generarLazyDefecto();
        this.setFindPaginated(req, matcher, example);
        if (example != null) {
            page = this.proveedorValidacionInfoDetalleRepository.findAll(example, req.toPageable(sort));
        } else {
            page = this.proveedorValidacionInfoDetalleRepository.findAll(req.toPageable(sort));
        }

        List<ProveedorValidacionInfoDetalle> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    protected abstract void setFindPaginated(PageRequestByExample<ProveedorValidacionInfoDetalle> req, ExampleMatcher matcher,
            Example<ProveedorValidacionInfoDetalle> example);

    @Transactional(readOnly = true)
    public XSSFWorkbook downloadExcelXLSX(ProveedorValidacionInfoDetalle req) {
        log.debug("Ingresando downloadExcelXLSX: ", req);
        List<ProveedorValidacionInfoDetalle> lista = this.find(req);
        Optional<List<ProveedorValidacionInfoDetalle>> oList = Optional.ofNullable(lista);
        if (!oList.isPresent()) {
            return null;
        }

        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet();
        int numberOfSheets = book.getNumberOfSheets();
        book.setSheetName(numberOfSheets - 1, NAME_SHEET);
        ExcelDefault.createTitle(sheet, CONFIG_TITLE, book.createCellStyle(), book.createFont());
        CellStyle cellStyle = book.createCellStyle();
        DataFormat dataFormat = book.createDataFormat();

        lista.forEach(proveedorValidacionInfoDetalle -> {
            int lastRow = sheet.getLastRowNum();
            int i = lastRow < 0 ? 0 : lastRow;
            XSSFRow dataRow = sheet.createRow(i + 1);
            int contador = 0;
            ExcelDefault.setValueCell(proveedorValidacionInfoDetalle.getId(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfoDetalle.getNombreCampoBd(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfoDetalle.getIndEsLista(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfoDetalle.getValorActual(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfoDetalle.getValorNuevo(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfoDetalle.getValorListaActual(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfoDetalle.getValorListaNuevo(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            /* Agregar aqui si desean colocar mas campos del BEAN */
            this.setDownloadExcelItem(proveedorValidacionInfoDetalle, dataRow);

        });
        this.setDownloadExcel(sheet);
        int totalColumn = sheet.getRow(0).getLastCellNum();
        for (int i = 0; i < totalColumn; i++) {
            sheet.autoSizeColumn(i, true);
        }
        return book;
    }

    protected void setDownloadExcelItem(ProveedorValidacionInfoDetalle bean, XSSFRow dataRow) {

    }

    protected void setDownloadExcel(XSSFSheet sheet) {

    }

    @Transactional(readOnly = true)
    public SXSSFWorkbook downloadExcelSXLSX(ProveedorValidacionInfoDetalle req) {
        log.debug("Ingresando downloadExcelSXLSX: ", req);
        List<ProveedorValidacionInfoDetalle> lista = this.find(req);
        Optional<List<ProveedorValidacionInfoDetalle>> oList = Optional.ofNullable(lista);
        if (!oList.isPresent()) {
            return null;
        }

        SXSSFWorkbook book = new SXSSFWorkbook(100);
        XSSFWorkbook xbook = book.getXSSFWorkbook();
        SXSSFSheet sheet = book.createSheet();
        sheet.trackAllColumnsForAutoSizing();
        int numberOfSheets = book.getNumberOfSheets();
        book.setSheetName(numberOfSheets - 1, NAME_SHEET);
        int nroColumnas = ExcelDefault.createTitle(xbook, sheet, CONFIG_TITLE);
        for (int i = 0; i < nroColumnas; i++) {
            sheet.autoSizeColumn(i, true);
        }
        sheet.untrackAllColumnsForAutoSizing();

        XSSFCellStyle cellStyle01 = ExcelDefault.devuelveCellStyle(xbook, new Color(0, 0, 1), new Color(226, 239, 218), false, (short) 10);
        XSSFCellStyle cellStyle02 = ExcelDefault.devuelveCellStyle(xbook, new Color(0, 0, 192), new Color(255, 255, 255), false, (short) 10);
        List<CellStyle> cellStyleList = null;
        List<CellStyle> cellStyleList01 = ExcelDefault.generarCellStyle(xbook, cellStyle01);
        List<CellStyle> cellStyleList02 = ExcelDefault.generarCellStyle(xbook, cellStyle02);
        boolean filaImpar = true;

        for (ProveedorValidacionInfoDetalle proveedorValidacionInfoDetalle : lista) {
            int lastRow = sheet.getLastRowNum();
            int i = lastRow < 0 ? 0 : lastRow;
            Row dataRow = sheet.createRow(i + 1);
            int contador = 0;
            if (filaImpar) {
                cellStyleList = cellStyleList01;
            } else {
                cellStyleList = cellStyleList02;
            }
            filaImpar = !filaImpar;

            ExcelDefault.setValueCell(proveedorValidacionInfoDetalle.getId(), dataRow.createCell(contador), "I", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfoDetalle.getNombreCampoBd(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfoDetalle.getIndEsLista(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfoDetalle.getValorActual(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfoDetalle.getValorNuevo(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfoDetalle.getValorListaActual(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfoDetalle.getValorListaNuevo(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            /* Agregar aqui si desean colocar mas campos del BEAN */
            this.setDownloadExcelItem(proveedorValidacionInfoDetalle, dataRow);

        }
        this.setDownloadExcel(sheet);
        return book;
    }

    protected void setDownloadExcelItem(ProveedorValidacionInfoDetalle bean, Row dataRow) {

    }

    protected void setDownloadExcel(Sheet sheet) {

    }

    @Transactional(readOnly = true)
    public SXSSFWorkbook generateInsertExcelSXLSX(ProveedorValidacionInfoDetalle req) {
        log.debug("Ingresando generateInsertExcelSXLSX: ", req);
        List<ProveedorValidacionInfoDetalle> lista = this.find(req);
        Optional<List<ProveedorValidacionInfoDetalle>> oList = Optional.ofNullable(lista);
        if (!oList.isPresent()) {
            return null;
        }

        SXSSFWorkbook book = new SXSSFWorkbook(100);
        XSSFWorkbook xbook = book.getXSSFWorkbook();
        SXSSFSheet sheet = book.createSheet();
        sheet.trackAllColumnsForAutoSizing();
        int numberOfSheets = book.getNumberOfSheets();
        book.setSheetName(numberOfSheets - 1, NAME_SHEET);
        int nroColumnas = 1;
        for (int i = 0; i < nroColumnas; i++) {
            sheet.autoSizeColumn(i, true);
        }
        sheet.untrackAllColumnsForAutoSizing();
        XSSFCellStyle cellStyle01 = ExcelDefault.devuelveCellStyle(xbook, new Color(0, 0, 1), new Color(226, 239, 218), false, (short) 10);
        XSSFCellStyle cellStyle02 = ExcelDefault.devuelveCellStyle(xbook, new Color(0, 0, 192), new Color(255, 255, 255), false, (short) 10);
        List<CellStyle> cellStyleList = null;
        List<CellStyle> cellStyleList01 = ExcelDefault.generarCellStyle(xbook, cellStyle01);
        List<CellStyle> cellStyleList02 = ExcelDefault.generarCellStyle(xbook, cellStyle02);
        boolean filaImpar = true;

        for (ProveedorValidacionInfoDetalle proveedorValidacionInfoDetalle : lista) {
            int lastRow = sheet.getLastRowNum();
            int i = lastRow < 0 ? 0 : lastRow;
            Row dataRow = sheet.createRow(i + 1);
            int contador = 0;
            if (filaImpar) {
                cellStyleList = cellStyleList01;
            } else {
                cellStyleList = cellStyleList02;
            }
            filaImpar = !filaImpar;
            String sqlInsert = "INSERT INTO proveedor_validacion_info_detalle(";
            sqlInsert = sqlInsert + "id_proveedor_por_validar_info_detalle" + ", ";
            sqlInsert = sqlInsert + "nombre_campo_bd" + ", ";
            sqlInsert = sqlInsert + "ind_es_lista" + ", ";
            sqlInsert = sqlInsert + "valor_actual" + ", ";
            sqlInsert = sqlInsert + "valor_nuevo" + ", ";
            sqlInsert = sqlInsert + "valor_lista_actual" + ", ";
            sqlInsert = sqlInsert + "valor_lista_nuevo" + ")";
            sqlInsert = sqlInsert + " VALUES (";
            sqlInsert = sqlInsert + proveedorValidacionInfoDetalle.getId() + ", ";
            if (StringUtils.isBlank(proveedorValidacionInfoDetalle.getNombreCampoBd())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorValidacionInfoDetalle.getNombreCampoBd() + "'" + ", ";
            }
            if (StringUtils.isBlank(proveedorValidacionInfoDetalle.getIndEsLista())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorValidacionInfoDetalle.getIndEsLista() + "'" + ", ";
            }
            if (StringUtils.isBlank(proveedorValidacionInfoDetalle.getValorActual())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorValidacionInfoDetalle.getValorActual() + "'" + ", ";
            }
            if (StringUtils.isBlank(proveedorValidacionInfoDetalle.getValorNuevo())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorValidacionInfoDetalle.getValorNuevo() + "'" + ", ";
            }
            if (StringUtils.isBlank(proveedorValidacionInfoDetalle.getValorListaActual())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorValidacionInfoDetalle.getValorListaActual() + "'" + ", ";
            }
            if (StringUtils.isBlank(proveedorValidacionInfoDetalle.getValorListaNuevo())) {
                sqlInsert = sqlInsert + "null";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorValidacionInfoDetalle.getValorListaNuevo() + "'";
            }
            sqlInsert = sqlInsert + " );";
            ExcelDefault.setValueCell(sqlInsert, dataRow.createCell(contador), "S", cellStyleList);
            contador++;
        }
        return book;
    }

    protected ProveedorValidacionInfoDetalle completarDatosBean(ProveedorValidacionInfoDetalle bean) {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected ProveedorValidacionInfoDetalle setCompletarDatosBean(ProveedorValidacionInfoDetalle bean) {
        return bean;
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    public ProveedorValidacionInfoDetalle save(ProveedorValidacionInfoDetalle dto) {
        log.debug("Ingresando save: ", dto);
        if (dto == null) {
            return null;
        }
        dto = this.completarDatosBean(dto);
        dto = this.setBeforeSave(dto);
        this.setSave(dto);
        dto = this.proveedorValidacionInfoDetalleRepository.save(dto);
        dto = this.setAfterSave(dto);
        return dto;
    }

    protected abstract void setSave(ProveedorValidacionInfoDetalle dto);

    protected ProveedorValidacionInfoDetalle setBeforeSave(ProveedorValidacionInfoDetalle dto) {
        return dto;
    }

    protected ProveedorValidacionInfoDetalle setAfterSave(ProveedorValidacionInfoDetalle dto) {
        return dto;
    }

    /**
    * Save new entity or update the corresponding entity if any.
    */
    public ProveedorValidacionInfoDetalle create(ProveedorValidacionInfoDetalle dto) {
        log.debug("Ingresando create: ", dto);
        if (dto == null) {
            return null;
        }

        ProveedorValidacionInfoDetalle proveedorValidacionInfoDetalle;
        proveedorValidacionInfoDetalle = new ProveedorValidacionInfoDetalle();

        proveedorValidacionInfoDetalle.setNombreCampoBd(dto.getNombreCampoBd());
        proveedorValidacionInfoDetalle.setIndEsLista(dto.getIndEsLista());
        proveedorValidacionInfoDetalle.setValorActual(dto.getValorActual());
        proveedorValidacionInfoDetalle.setValorNuevo(dto.getValorNuevo());
        proveedorValidacionInfoDetalle.setValorListaActual(dto.getValorListaActual());
        proveedorValidacionInfoDetalle.setValorListaNuevo(dto.getValorListaNuevo());
        proveedorValidacionInfoDetalle = this.completarDatosBean(proveedorValidacionInfoDetalle);
        this.setCreate(proveedorValidacionInfoDetalle);
        return this.proveedorValidacionInfoDetalleRepository.save(proveedorValidacionInfoDetalle);
    }

    protected abstract void setCreate(ProveedorValidacionInfoDetalle dto);

    protected String validacionesPrevias(ProveedorValidacionInfoDetalle dto) {
        return "";
    }

    /**
     * Delete the passed dto as a new entity or update the corresponding entity if any.
     */
    public void delete(Integer id) {
        log.debug("Ingresando delete: ", id);
        if (id == null) {
            return;
        }
        this.setDelete(id);
        this.proveedorValidacionInfoDetalleRepository.deleteById(id);
    }

    protected abstract void setDelete(Integer id);

    public void deleteAll() {
        log.debug("Ingresando deleteAll");
        this.setDeleteAll();
        this.proveedorValidacionInfoDetalleRepository.deleteAll();
    }

    protected abstract void setDeleteAll();

    /**
     * Converts the passed proveedorValidacionInfoDetalle to a DTO.
     */
    protected ProveedorValidacionInfoDetalle toDTO(ProveedorValidacionInfoDetalle proveedorValidacionInfoDetalle) {
        return proveedorValidacionInfoDetalle;
    }

    /**
     * Converts the passed dto to a ProveedorValidacionInfoDetalle.
     * Convenient for query by example.
     */
    protected ProveedorValidacionInfoDetalle toEntity(ProveedorValidacionInfoDetalle dto) {
        return dto;
    }

}