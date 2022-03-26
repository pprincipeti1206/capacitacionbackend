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
import com.incloud.hcp.domain._gproveedor.domain.EstadoProveedor;
import com.incloud.hcp.domain._gproveedor.domain.EstadoProveedor_;
import com.incloud.hcp.repository._gproveedor.EstadoProveedorRepository;
import com.incloud.hcp.service._gproveedor.EstadoProveedorService;
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
 * A simple DTO Facility for EstadoProveedor.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public abstract class EstadoProveedorServiceImpl implements EstadoProveedorService {

    protected final Logger log = LoggerFactory.getLogger(EstadoProveedorServiceImpl.class);

    protected final String NAME_SHEET = "EstadoProveedor";
    protected final String CONFIG_TITLE = "com/incloud/hcp/excel/EstadoProveedorExcel.xml";

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected EstadoProveedorRepository estadoProveedorRepository;

    @Transactional(readOnly = true)
    public Optional<EstadoProveedor> findOne(Integer id) {
        log.debug("Ingresando findOne: ", id);
        return this.estadoProveedorRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<EstadoProveedor> findAll() {
        log.debug("Ingresando findAll");
        Sort sort = Sort.by("id");
        sort = this.setFindAll(sort);
        List<EstadoProveedor> lista = this.estadoProveedorRepository.findAll(sort);
        return lista;
    }

    protected abstract Sort setFindAll(Sort sort);

    @Transactional(readOnly = true)
    public List<EstadoProveedor> find(EstadoProveedor req) {
        log.debug("Ingresando find: ", req);
        Example<EstadoProveedor> example = null;
        EstadoProveedor estadoProveedor = req;
        ExampleMatcher matcher = null;
        if (estadoProveedor != null) {
            matcher = ExampleMatcher.matching() //
                    .withMatcher(EstadoProveedor_.descripcionEstadoProveedor.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(EstadoProveedor_.codigoEstadoProveedor.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(EstadoProveedor_.indVerValidacion.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(EstadoProveedor_.indVerLicitacion.getName(), match -> match.ignoreCase().startsWith());
            example = Example.of(estadoProveedor, matcher);
        }
        Sort sort = Sort.by("id");
        sort = this.setFind(req, matcher, example, sort);
        List<EstadoProveedor> lista = this.estadoProveedorRepository.findAll(example, sort);
        return lista;
    }

    protected abstract Sort setFind(EstadoProveedor req, ExampleMatcher matcher, Example<EstadoProveedor> example, Sort sort);

    @Transactional(readOnly = true)
    public PageResponse<EstadoProveedor> findPaginated(PageRequestByExample<EstadoProveedor> req) {
        log.debug("Ingresando findPaginated: ", req);
        Example<EstadoProveedor> example = null;
        EstadoProveedor estadoProveedor = toEntity(req.bean);
        ExampleMatcher matcher = null;
        if (estadoProveedor != null) {
            matcher = ExampleMatcher.matching() //
                    .withMatcher(EstadoProveedor_.descripcionEstadoProveedor.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(EstadoProveedor_.codigoEstadoProveedor.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(EstadoProveedor_.indVerValidacion.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(EstadoProveedor_.indVerLicitacion.getName(), match -> match.ignoreCase().startsWith());
            example = Example.of(estadoProveedor, matcher);
        }

        Page<EstadoProveedor> page;
        Sort sort = Sort.by("id");
        sort = this.setFind(estadoProveedor, matcher, example, sort);
        req.generarLazyDefecto();
        this.setFindPaginated(req, matcher, example);
        if (example != null) {
            page = this.estadoProveedorRepository.findAll(example, req.toPageable(sort));
        } else {
            page = this.estadoProveedorRepository.findAll(req.toPageable(sort));
        }

        List<EstadoProveedor> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    protected abstract void setFindPaginated(PageRequestByExample<EstadoProveedor> req, ExampleMatcher matcher, Example<EstadoProveedor> example);

    @Transactional(readOnly = true)
    public XSSFWorkbook downloadExcelXLSX(EstadoProveedor req) {
        log.debug("Ingresando downloadExcelXLSX: ", req);
        List<EstadoProveedor> lista = this.find(req);
        Optional<List<EstadoProveedor>> oList = Optional.ofNullable(lista);
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

        lista.forEach(estadoProveedor -> {
            int lastRow = sheet.getLastRowNum();
            int i = lastRow < 0 ? 0 : lastRow;
            XSSFRow dataRow = sheet.createRow(i + 1);
            int contador = 0;
            ExcelDefault.setValueCell(estadoProveedor.getId(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(estadoProveedor.getDescripcionEstadoProveedor(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(estadoProveedor.getOrden(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(estadoProveedor.getCodigoEstadoProveedor(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(estadoProveedor.getIndVerValidacion(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(estadoProveedor.getIndVerLicitacion(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            /* Agregar aqui si desean colocar mas campos del BEAN */
            this.setDownloadExcelItem(estadoProveedor, dataRow);

        });
        this.setDownloadExcel(sheet);
        int totalColumn = sheet.getRow(0).getLastCellNum();
        for (int i = 0; i < totalColumn; i++) {
            sheet.autoSizeColumn(i, true);
        }
        return book;
    }

    protected void setDownloadExcelItem(EstadoProveedor bean, XSSFRow dataRow) {

    }

    protected void setDownloadExcel(XSSFSheet sheet) {

    }

    @Transactional(readOnly = true)
    public SXSSFWorkbook downloadExcelSXLSX(EstadoProveedor req) {
        log.debug("Ingresando downloadExcelSXLSX: ", req);
        List<EstadoProveedor> lista = this.find(req);
        Optional<List<EstadoProveedor>> oList = Optional.ofNullable(lista);
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

        for (EstadoProveedor estadoProveedor : lista) {
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

            ExcelDefault.setValueCell(estadoProveedor.getId(), dataRow.createCell(contador), "I", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(estadoProveedor.getDescripcionEstadoProveedor(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(estadoProveedor.getOrden(), dataRow.createCell(contador), "I", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(estadoProveedor.getCodigoEstadoProveedor(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(estadoProveedor.getIndVerValidacion(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(estadoProveedor.getIndVerLicitacion(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            /* Agregar aqui si desean colocar mas campos del BEAN */
            this.setDownloadExcelItem(estadoProveedor, dataRow);

        }
        this.setDownloadExcel(sheet);
        return book;
    }

    protected void setDownloadExcelItem(EstadoProveedor bean, Row dataRow) {

    }

    protected void setDownloadExcel(Sheet sheet) {

    }

    @Transactional(readOnly = true)
    public SXSSFWorkbook generateInsertExcelSXLSX(EstadoProveedor req) {
        log.debug("Ingresando generateInsertExcelSXLSX: ", req);
        List<EstadoProveedor> lista = this.find(req);
        Optional<List<EstadoProveedor>> oList = Optional.ofNullable(lista);
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

        for (EstadoProveedor estadoProveedor : lista) {
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
            String sqlInsert = "INSERT INTO estado_proveedor(";
            sqlInsert = sqlInsert + "id_estado_proveedor" + ", ";
            sqlInsert = sqlInsert + "descripcion_estado_proveedor" + ", ";
            sqlInsert = sqlInsert + "orden" + ", ";
            sqlInsert = sqlInsert + "codigo_estado_proveedor" + ", ";
            sqlInsert = sqlInsert + "ind_ver_validacion" + ")";
            sqlInsert = sqlInsert + " VALUES (";
            sqlInsert = sqlInsert + estadoProveedor.getId() + ", ";
            if (StringUtils.isBlank(estadoProveedor.getDescripcionEstadoProveedor())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + estadoProveedor.getDescripcionEstadoProveedor() + "'" + ", ";
            }
            sqlInsert = sqlInsert + estadoProveedor.getOrden() + ", ";
            if (StringUtils.isBlank(estadoProveedor.getCodigoEstadoProveedor())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + estadoProveedor.getCodigoEstadoProveedor() + "'" + ", ";
            }
            if (StringUtils.isBlank(estadoProveedor.getIndVerValidacion())) {
                sqlInsert = sqlInsert + "null";
            } else {
                sqlInsert = sqlInsert + "'" + estadoProveedor.getIndVerValidacion() + "'";
            }
            if (StringUtils.isBlank(estadoProveedor.getIndVerLicitacion())) {
                sqlInsert = sqlInsert + "null";
            } else {
                sqlInsert = sqlInsert + "'" + estadoProveedor.getIndVerLicitacion() + "'";
            }
            sqlInsert = sqlInsert + " );";
            ExcelDefault.setValueCell(sqlInsert, dataRow.createCell(contador), "S", cellStyleList);
            contador++;
        }
        return book;
    }

    protected EstadoProveedor completarDatosBean(EstadoProveedor bean) {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected EstadoProveedor setCompletarDatosBean(EstadoProveedor bean) {
        return bean;
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    public EstadoProveedor save(EstadoProveedor dto) {
        log.debug("Ingresando save: ", dto);
        if (dto == null) {
            return null;
        }
        dto = this.completarDatosBean(dto);
        dto = this.setBeforeSave(dto);
        this.setSave(dto);
        dto = this.estadoProveedorRepository.save(dto);
        dto = this.setAfterSave(dto);
        return dto;
    }

    protected abstract void setSave(EstadoProveedor dto);

    protected EstadoProveedor setBeforeSave(EstadoProveedor dto) {
        return dto;
    }

    protected EstadoProveedor setAfterSave(EstadoProveedor dto) {
        return dto;
    }

    /**
    * Save new entity or update the corresponding entity if any.
    */
    public EstadoProveedor create(EstadoProveedor dto) {
        log.debug("Ingresando create: ", dto);
        if (dto == null) {
            return null;
        }

        EstadoProveedor estadoProveedor;
        estadoProveedor = new EstadoProveedor();

        estadoProveedor.setDescripcionEstadoProveedor(dto.getDescripcionEstadoProveedor());
        estadoProveedor.setOrden(dto.getOrden());
        estadoProveedor.setCodigoEstadoProveedor(dto.getCodigoEstadoProveedor());
        estadoProveedor.setIndVerValidacion(dto.getIndVerValidacion());
        estadoProveedor.setIndVerLicitacion(dto.getIndVerLicitacion());
        estadoProveedor = this.completarDatosBean(estadoProveedor);
        this.setCreate(estadoProveedor);
        return this.estadoProveedorRepository.save(estadoProveedor);
    }

    protected abstract void setCreate(EstadoProveedor dto);

    protected String validacionesPrevias(EstadoProveedor dto) {
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
        this.estadoProveedorRepository.deleteById(id);
    }

    protected abstract void setDelete(Integer id);

    public void deleteAll() {
        log.debug("Ingresando deleteAll");
        this.setDeleteAll();
        this.estadoProveedorRepository.deleteAll();
    }

    protected abstract void setDeleteAll();

    /**
     * Converts the passed estadoProveedor to a DTO.
     */
    protected EstadoProveedor toDTO(EstadoProveedor estadoProveedor) {
        return estadoProveedor;
    }

    /**
     * Converts the passed dto to a EstadoProveedor.
     * Convenient for query by example.
     */
    protected EstadoProveedor toEntity(EstadoProveedor dto) {
        return dto;
    }

}