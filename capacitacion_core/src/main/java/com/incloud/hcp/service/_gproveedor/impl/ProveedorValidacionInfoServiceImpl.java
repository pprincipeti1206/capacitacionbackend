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
import com.incloud.hcp.domain._gproveedor.domain.ProveedorValidacionInfo;
import com.incloud.hcp.domain._gproveedor.domain.ProveedorValidacionInfo_;
import com.incloud.hcp.repository._gproveedor.ProveedorRepository;
import com.incloud.hcp.repository._gproveedor.ProveedorValidacionInfoRepository;
import com.incloud.hcp.service._gproveedor.ProveedorService;
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
 * A simple DTO Facility for ProveedorValidacionInfo.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public abstract class ProveedorValidacionInfoServiceImpl implements ProveedorValidacionInfoService {

    protected final Logger log = LoggerFactory.getLogger(ProveedorValidacionInfoServiceImpl.class);

    protected final String NAME_SHEET = "ProveedorValidacionInfo";
    protected final String CONFIG_TITLE = "com/incloud/hcp/excel/ProveedorValidacionInfoExcel.xml";

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected ProveedorValidacionInfoRepository proveedorValidacionInfoRepository;

    @Autowired
    protected ProveedorService proveedorService;

    @Autowired
    protected ProveedorRepository proveedorRepository;

    @Transactional(readOnly = true)
    public Optional<ProveedorValidacionInfo> findOne(Integer id) {
        log.debug("Ingresando findOne: ", id);
        return this.proveedorValidacionInfoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ProveedorValidacionInfo> findAll() {
        log.debug("Ingresando findAll");
        Sort sort = Sort.by("id");
        sort = this.setFindAll(sort);
        List<ProveedorValidacionInfo> lista = this.proveedorValidacionInfoRepository.findAll(sort);
        return lista;
    }

    protected abstract Sort setFindAll(Sort sort);

    @Transactional(readOnly = true)
    public List<ProveedorValidacionInfo> find(ProveedorValidacionInfo req) {
        log.debug("Ingresando find: ", req);
        Example<ProveedorValidacionInfo> example = null;
        ProveedorValidacionInfo proveedorValidacionInfo = req;
        ExampleMatcher matcher = null;
        if (proveedorValidacionInfo != null) {
            matcher = ExampleMatcher.matching() //
                    .withMatcher(ProveedorValidacionInfo_.usuarioAprobacionRechazo.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorValidacionInfo_.indAprobacionRechazo.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorValidacionInfo_.motivoRechazo.getName(), match -> match.ignoreCase().startsWith());
            example = Example.of(proveedorValidacionInfo, matcher);
        }
        Sort sort = Sort.by("id");
        sort = this.setFind(req, matcher, example, sort);
        List<ProveedorValidacionInfo> lista = this.proveedorValidacionInfoRepository.findAll(example, sort);
        return lista;
    }

    protected abstract Sort setFind(ProveedorValidacionInfo req, ExampleMatcher matcher, Example<ProveedorValidacionInfo> example, Sort sort);

    @Transactional(readOnly = true)
    public PageResponse<ProveedorValidacionInfo> findPaginated(PageRequestByExample<ProveedorValidacionInfo> req) {
        log.debug("Ingresando findPaginated: ", req);
        Example<ProveedorValidacionInfo> example = null;
        ProveedorValidacionInfo proveedorValidacionInfo = toEntity(req.bean);
        ExampleMatcher matcher = null;
        if (proveedorValidacionInfo != null) {
            matcher = ExampleMatcher.matching() //
                    .withMatcher(ProveedorValidacionInfo_.usuarioAprobacionRechazo.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorValidacionInfo_.indAprobacionRechazo.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorValidacionInfo_.motivoRechazo.getName(), match -> match.ignoreCase().startsWith());
            example = Example.of(proveedorValidacionInfo, matcher);
        }

        Page<ProveedorValidacionInfo> page;
        Sort sort = Sort.by("id");
        sort = this.setFind(proveedorValidacionInfo, matcher, example, sort);
        req.generarLazyDefecto();
        this.setFindPaginated(req, matcher, example);
        if (example != null) {
            page = this.proveedorValidacionInfoRepository.findAll(example, req.toPageable(sort));
        } else {
            page = this.proveedorValidacionInfoRepository.findAll(req.toPageable(sort));
        }

        List<ProveedorValidacionInfo> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    protected abstract void setFindPaginated(PageRequestByExample<ProveedorValidacionInfo> req, ExampleMatcher matcher,
            Example<ProveedorValidacionInfo> example);

    @Transactional(readOnly = true)
    public XSSFWorkbook downloadExcelXLSX(ProveedorValidacionInfo req) {
        log.debug("Ingresando downloadExcelXLSX: ", req);
        List<ProveedorValidacionInfo> lista = this.find(req);
        Optional<List<ProveedorValidacionInfo>> oList = Optional.ofNullable(lista);
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

        lista.forEach(proveedorValidacionInfo -> {
            int lastRow = sheet.getLastRowNum();
            int i = lastRow < 0 ? 0 : lastRow;
            XSSFRow dataRow = sheet.createRow(i + 1);
            int contador = 0;
            ExcelDefault.setValueCell(proveedorValidacionInfo.getId(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfo.getFechaAprobacionRechazo(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfo.getUsuarioAprobacionRechazo(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfo.getIndAprobacionRechazo(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfo.getMotivoRechazo(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            /* Agregar aqui si desean colocar mas campos del BEAN */
            this.setDownloadExcelItem(proveedorValidacionInfo, dataRow);

        });
        this.setDownloadExcel(sheet);
        int totalColumn = sheet.getRow(0).getLastCellNum();
        for (int i = 0; i < totalColumn; i++) {
            sheet.autoSizeColumn(i, true);
        }
        return book;
    }

    protected void setDownloadExcelItem(ProveedorValidacionInfo bean, XSSFRow dataRow) {

    }

    protected void setDownloadExcel(XSSFSheet sheet) {

    }

    @Transactional(readOnly = true)
    public SXSSFWorkbook downloadExcelSXLSX(ProveedorValidacionInfo req) {
        log.debug("Ingresando downloadExcelSXLSX: ", req);
        List<ProveedorValidacionInfo> lista = this.find(req);
        Optional<List<ProveedorValidacionInfo>> oList = Optional.ofNullable(lista);
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

        for (ProveedorValidacionInfo proveedorValidacionInfo : lista) {
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

            ExcelDefault.setValueCell(proveedorValidacionInfo.getId(), dataRow.createCell(contador), "I", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfo.getFechaAprobacionRechazo(), dataRow.createCell(contador), "DT", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfo.getUsuarioAprobacionRechazo(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfo.getIndAprobacionRechazo(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorValidacionInfo.getMotivoRechazo(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            /* Agregar aqui si desean colocar mas campos del BEAN */
            this.setDownloadExcelItem(proveedorValidacionInfo, dataRow);

        }
        this.setDownloadExcel(sheet);
        return book;
    }

    protected void setDownloadExcelItem(ProveedorValidacionInfo bean, Row dataRow) {

    }

    protected void setDownloadExcel(Sheet sheet) {

    }

    @Transactional(readOnly = true)
    public SXSSFWorkbook generateInsertExcelSXLSX(ProveedorValidacionInfo req) {
        log.debug("Ingresando generateInsertExcelSXLSX: ", req);
        List<ProveedorValidacionInfo> lista = this.find(req);
        Optional<List<ProveedorValidacionInfo>> oList = Optional.ofNullable(lista);
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

        for (ProveedorValidacionInfo proveedorValidacionInfo : lista) {
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
            String sqlInsert = "INSERT INTO proveedor_validacion_info(";
            sqlInsert = sqlInsert + "id_proveedor_validacion_info" + ", ";
            sqlInsert = sqlInsert + "fecha_aprobacion_rechazo" + ", ";
            sqlInsert = sqlInsert + "usuario_aprobacion_rechazo" + ", ";
            sqlInsert = sqlInsert + "ind_aprobacion_rechazo" + ", ";
            sqlInsert = sqlInsert + "motivo_rechazo" + ")";
            sqlInsert = sqlInsert + " VALUES (";
            sqlInsert = sqlInsert + proveedorValidacionInfo.getId() + ", ";
            if (Optional.ofNullable(proveedorValidacionInfo.getFechaAprobacionRechazo()).isPresent()) {
                sqlInsert = sqlInsert + proveedorValidacionInfo.getFechaAprobacionRechazo().getTime() + ", ";
            }

            else {
                sqlInsert = sqlInsert + "null" + ", ";
            }
            if (StringUtils.isBlank(proveedorValidacionInfo.getUsuarioAprobacionRechazo())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorValidacionInfo.getUsuarioAprobacionRechazo() + "'" + ", ";
            }
            if (StringUtils.isBlank(proveedorValidacionInfo.getIndAprobacionRechazo())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorValidacionInfo.getIndAprobacionRechazo() + "'" + ", ";
            }
            if (StringUtils.isBlank(proveedorValidacionInfo.getMotivoRechazo())) {
                sqlInsert = sqlInsert + "null";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorValidacionInfo.getMotivoRechazo() + "'";
            }
            sqlInsert = sqlInsert + " );";
            ExcelDefault.setValueCell(sqlInsert, dataRow.createCell(contador), "S", cellStyleList);
            contador++;
        }
        return book;
    }

    protected ProveedorValidacionInfo completarDatosBean(ProveedorValidacionInfo bean) {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected ProveedorValidacionInfo setCompletarDatosBean(ProveedorValidacionInfo bean) {
        return bean;
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    public ProveedorValidacionInfo save(ProveedorValidacionInfo dto) {
        log.debug("Ingresando save: ", dto);
        if (dto == null) {
            return null;
        }
        dto = this.completarDatosBean(dto);
        dto = this.setBeforeSave(dto);
        this.setSave(dto);
        dto = this.proveedorValidacionInfoRepository.save(dto);
        dto = this.setAfterSave(dto);
        return dto;
    }

    protected abstract void setSave(ProveedorValidacionInfo dto);

    protected ProveedorValidacionInfo setBeforeSave(ProveedorValidacionInfo dto) {
        return dto;
    }

    protected ProveedorValidacionInfo setAfterSave(ProveedorValidacionInfo dto) {
        return dto;
    }

    /**
    * Save new entity or update the corresponding entity if any.
    */
    public ProveedorValidacionInfo create(ProveedorValidacionInfo dto) {
        log.debug("Ingresando create: ", dto);
        if (dto == null) {
            return null;
        }

        ProveedorValidacionInfo proveedorValidacionInfo;
        proveedorValidacionInfo = new ProveedorValidacionInfo();

        proveedorValidacionInfo.setFechaAprobacionRechazo(dto.getFechaAprobacionRechazo());
        proveedorValidacionInfo.setUsuarioAprobacionRechazo(dto.getUsuarioAprobacionRechazo());
        proveedorValidacionInfo.setIndAprobacionRechazo(dto.getIndAprobacionRechazo());
        proveedorValidacionInfo.setMotivoRechazo(dto.getMotivoRechazo());
        proveedorValidacionInfo = this.completarDatosBean(proveedorValidacionInfo);
        this.setCreate(proveedorValidacionInfo);
        return this.proveedorValidacionInfoRepository.save(proveedorValidacionInfo);
    }

    protected abstract void setCreate(ProveedorValidacionInfo dto);

    protected String validacionesPrevias(ProveedorValidacionInfo dto) {
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
        this.proveedorValidacionInfoRepository.deleteById(id);
    }

    protected abstract void setDelete(Integer id);

    public void deleteAll() {
        log.debug("Ingresando deleteAll");
        this.setDeleteAll();
        this.proveedorValidacionInfoRepository.deleteAll();
    }

    protected abstract void setDeleteAll();

    /**
     * Converts the passed proveedorValidacionInfo to a DTO.
     */
    protected ProveedorValidacionInfo toDTO(ProveedorValidacionInfo proveedorValidacionInfo) {
        return proveedorValidacionInfo;
    }

    /**
     * Converts the passed dto to a ProveedorValidacionInfo.
     * Convenient for query by example.
     */
    protected ProveedorValidacionInfo toEntity(ProveedorValidacionInfo dto) {
        return dto;
    }

}