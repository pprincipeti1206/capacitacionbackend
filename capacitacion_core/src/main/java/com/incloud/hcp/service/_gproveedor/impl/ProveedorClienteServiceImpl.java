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
import com.incloud.hcp.domain._gproveedor.domain.ProveedorCliente;
import com.incloud.hcp.domain._gproveedor.domain.ProveedorCliente_;
import com.incloud.hcp.repository._gproveedor.ProveedorClienteRepository;
import com.incloud.hcp.repository._gproveedor.ProveedorRepository;
import com.incloud.hcp.service._gproveedor.ProveedorClienteService;
import com.incloud.hcp.service._gproveedor.ProveedorService;
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
 * A simple DTO Facility for ProveedorCliente.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public abstract class ProveedorClienteServiceImpl implements ProveedorClienteService {

    protected final Logger log = LoggerFactory.getLogger(ProveedorClienteServiceImpl.class);

    protected final String NAME_SHEET = "ProveedorCliente";
    protected final String CONFIG_TITLE = "com/incloud/hcp/excel/ProveedorClienteExcel.xml";

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected ProveedorClienteRepository proveedorClienteRepository;

    @Autowired
    protected ProveedorService proveedorService;

    @Autowired
    protected ProveedorRepository proveedorRepository;

    @Transactional(readOnly = true)
    public Optional<ProveedorCliente> findOne(Integer id) {
        log.debug("Ingresando findOne: ", id);
        return this.proveedorClienteRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ProveedorCliente> findAll() {
        log.debug("Ingresando findAll");
        Sort sort = Sort.by("id");
        sort = this.setFindAll(sort);
        List<ProveedorCliente> lista = this.proveedorClienteRepository.findAll(sort);
        return lista;
    }

    protected abstract Sort setFindAll(Sort sort);

    @Transactional(readOnly = true)
    public List<ProveedorCliente> find(ProveedorCliente req) {
        log.debug("Ingresando find: ", req);
        Example<ProveedorCliente> example = null;
        ProveedorCliente proveedorCliente = req;
        ExampleMatcher matcher = null;
        if (proveedorCliente != null) {
            matcher = ExampleMatcher.matching() //
                    .withMatcher(ProveedorCliente_.codigoTipoProveedorCliente.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorCliente_.razonSocial.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorCliente_.ruc.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorCliente_.rubro.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorCliente_.personaContacto.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorCliente_.telefono.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorCliente_.email.getName(), match -> match.ignoreCase().startsWith());
            example = Example.of(proveedorCliente, matcher);
        }
        Sort sort = Sort.by("id");
        sort = this.setFind(req, matcher, example, sort);
        List<ProveedorCliente> lista = this.proveedorClienteRepository.findAll(example, sort);
        return lista;
    }

    protected abstract Sort setFind(ProveedorCliente req, ExampleMatcher matcher, Example<ProveedorCliente> example, Sort sort);

    @Transactional(readOnly = true)
    public PageResponse<ProveedorCliente> findPaginated(PageRequestByExample<ProveedorCliente> req) {
        log.debug("Ingresando findPaginated: ", req);
        Example<ProveedorCliente> example = null;
        ProveedorCliente proveedorCliente = toEntity(req.bean);
        ExampleMatcher matcher = null;
        if (proveedorCliente != null) {
            matcher = ExampleMatcher.matching() //
                    .withMatcher(ProveedorCliente_.codigoTipoProveedorCliente.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorCliente_.razonSocial.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorCliente_.ruc.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorCliente_.rubro.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorCliente_.personaContacto.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorCliente_.telefono.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(ProveedorCliente_.email.getName(), match -> match.ignoreCase().startsWith());
            example = Example.of(proveedorCliente, matcher);
        }

        Page<ProveedorCliente> page;
        Sort sort = Sort.by("id");
        sort = this.setFind(proveedorCliente, matcher, example, sort);
        req.generarLazyDefecto();
        this.setFindPaginated(req, matcher, example);
        if (example != null) {
            page = this.proveedorClienteRepository.findAll(example, req.toPageable(sort));
        } else {
            page = this.proveedorClienteRepository.findAll(req.toPageable(sort));
        }

        List<ProveedorCliente> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    protected abstract void setFindPaginated(PageRequestByExample<ProveedorCliente> req, ExampleMatcher matcher, Example<ProveedorCliente> example);

    @Transactional(readOnly = true)
    public XSSFWorkbook downloadExcelXLSX(ProveedorCliente req) {
        log.debug("Ingresando downloadExcelXLSX: ", req);
        List<ProveedorCliente> lista = this.find(req);
        Optional<List<ProveedorCliente>> oList = Optional.ofNullable(lista);
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

        lista.forEach(proveedorCliente -> {
            int lastRow = sheet.getLastRowNum();
            int i = lastRow < 0 ? 0 : lastRow;
            XSSFRow dataRow = sheet.createRow(i + 1);
            int contador = 0;
            ExcelDefault.setValueCell(proveedorCliente.getId(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getCodigoTipoProveedorCliente(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getRazonSocial(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getRuc(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getRubro(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getPorcentajeParticipacion(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getPersonaContacto(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getTelefono(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getEmail(), dataRow.createCell(contador), cellStyle, dataFormat);
            contador++;
            /* Agregar aqui si desean colocar mas campos del BEAN */
            this.setDownloadExcelItem(proveedorCliente, dataRow);

        });
        this.setDownloadExcel(sheet);
        int totalColumn = sheet.getRow(0).getLastCellNum();
        for (int i = 0; i < totalColumn; i++) {
            sheet.autoSizeColumn(i, true);
        }
        return book;
    }

    protected void setDownloadExcelItem(ProveedorCliente bean, XSSFRow dataRow) {

    }

    protected void setDownloadExcel(XSSFSheet sheet) {

    }

    @Transactional(readOnly = true)
    public SXSSFWorkbook downloadExcelSXLSX(ProveedorCliente req) {
        log.debug("Ingresando downloadExcelSXLSX: ", req);
        List<ProveedorCliente> lista = this.find(req);
        Optional<List<ProveedorCliente>> oList = Optional.ofNullable(lista);
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

        for (ProveedorCliente proveedorCliente : lista) {
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

            ExcelDefault.setValueCell(proveedorCliente.getId(), dataRow.createCell(contador), "I", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getCodigoTipoProveedorCliente(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getRazonSocial(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getRuc(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getRubro(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getPorcentajeParticipacion(), dataRow.createCell(contador), "N", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getPersonaContacto(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getTelefono(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            ExcelDefault.setValueCell(proveedorCliente.getEmail(), dataRow.createCell(contador), "S", cellStyleList);
            contador++;
            /* Agregar aqui si desean colocar mas campos del BEAN */
            this.setDownloadExcelItem(proveedorCliente, dataRow);

        }
        this.setDownloadExcel(sheet);
        return book;
    }

    protected void setDownloadExcelItem(ProveedorCliente bean, Row dataRow) {

    }

    protected void setDownloadExcel(Sheet sheet) {

    }

    @Transactional(readOnly = true)
    public SXSSFWorkbook generateInsertExcelSXLSX(ProveedorCliente req) {
        log.debug("Ingresando generateInsertExcelSXLSX: ", req);
        List<ProveedorCliente> lista = this.find(req);
        Optional<List<ProveedorCliente>> oList = Optional.ofNullable(lista);
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

        for (ProveedorCliente proveedorCliente : lista) {
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
            String sqlInsert = "INSERT INTO proveedor_cliente(";
            sqlInsert = sqlInsert + "id_proveedor_cliente" + ", ";
            sqlInsert = sqlInsert + "codigo_tipo_proveedor_cliente" + ", ";
            sqlInsert = sqlInsert + "razon_social" + ", ";
            sqlInsert = sqlInsert + "ruc" + ", ";
            sqlInsert = sqlInsert + "rubro" + ", ";
            sqlInsert = sqlInsert + "porcentaje_participacion" + ", ";
            sqlInsert = sqlInsert + "persona_contacto" + ", ";
            sqlInsert = sqlInsert + "telefono" + ", ";
            sqlInsert = sqlInsert + "email" + ")";
            sqlInsert = sqlInsert + " VALUES (";
            sqlInsert = sqlInsert + proveedorCliente.getId() + ", ";
            if (StringUtils.isBlank(proveedorCliente.getCodigoTipoProveedorCliente())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorCliente.getCodigoTipoProveedorCliente() + "'" + ", ";
            }
            if (StringUtils.isBlank(proveedorCliente.getRazonSocial())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorCliente.getRazonSocial() + "'" + ", ";
            }
            if (StringUtils.isBlank(proveedorCliente.getRuc())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorCliente.getRuc() + "'" + ", ";
            }
            if (StringUtils.isBlank(proveedorCliente.getRubro())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorCliente.getRubro() + "'" + ", ";
            }
            sqlInsert = sqlInsert + proveedorCliente.getPorcentajeParticipacion() + ", ";
            if (StringUtils.isBlank(proveedorCliente.getPersonaContacto())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorCliente.getPersonaContacto() + "'" + ", ";
            }
            if (StringUtils.isBlank(proveedorCliente.getTelefono())) {
                sqlInsert = sqlInsert + "null" + ", ";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorCliente.getTelefono() + "'" + ", ";
            }
            if (StringUtils.isBlank(proveedorCliente.getEmail())) {
                sqlInsert = sqlInsert + "null";
            } else {
                sqlInsert = sqlInsert + "'" + proveedorCliente.getEmail() + "'";
            }
            sqlInsert = sqlInsert + " );";
            ExcelDefault.setValueCell(sqlInsert, dataRow.createCell(contador), "S", cellStyleList);
            contador++;
        }
        return book;
    }

    protected ProveedorCliente completarDatosBean(ProveedorCliente bean) {
        BigDecimal data = new BigDecimal(0.00);
        if (Optional.ofNullable(bean.getPorcentajeParticipacion()).isPresent()) {
            data = new BigDecimal(bean.getPorcentajeParticipacion().floatValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
            bean.setPorcentajeParticipacion(data.doubleValue());
        }
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected ProveedorCliente setCompletarDatosBean(ProveedorCliente bean) {
        return bean;
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    public ProveedorCliente save(ProveedorCliente dto) {
        log.debug("Ingresando save: ", dto);
        if (dto == null) {
            return null;
        }
        dto = this.completarDatosBean(dto);
        dto = this.setBeforeSave(dto);
        this.setSave(dto);
        dto = this.proveedorClienteRepository.save(dto);
        dto = this.setAfterSave(dto);
        return dto;
    }

    protected abstract void setSave(ProveedorCliente dto);

    protected ProveedorCliente setBeforeSave(ProveedorCliente dto) {
        return dto;
    }

    protected ProveedorCliente setAfterSave(ProveedorCliente dto) {
        return dto;
    }

    /**
    * Save new entity or update the corresponding entity if any.
    */
    public ProveedorCliente create(ProveedorCliente dto) {
        log.debug("Ingresando create: ", dto);
        if (dto == null) {
            return null;
        }

        ProveedorCliente proveedorCliente;
        proveedorCliente = new ProveedorCliente();

        proveedorCliente.setCodigoTipoProveedorCliente(dto.getCodigoTipoProveedorCliente());
        proveedorCliente.setRazonSocial(dto.getRazonSocial());
        proveedorCliente.setRuc(dto.getRuc());
        proveedorCliente.setRubro(dto.getRubro());
        proveedorCliente.setPorcentajeParticipacion(dto.getPorcentajeParticipacion());
        proveedorCliente.setPersonaContacto(dto.getPersonaContacto());
        proveedorCliente.setTelefono(dto.getTelefono());
        proveedorCliente.setEmail(dto.getEmail());
        proveedorCliente = this.completarDatosBean(proveedorCliente);
        this.setCreate(proveedorCliente);
        return this.proveedorClienteRepository.save(proveedorCliente);
    }

    protected abstract void setCreate(ProveedorCliente dto);

    protected String validacionesPrevias(ProveedorCliente dto) {
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
        this.proveedorClienteRepository.deleteById(id);
    }

    protected abstract void setDelete(Integer id);

    public void deleteAll() {
        log.debug("Ingresando deleteAll");
        this.setDeleteAll();
        this.proveedorClienteRepository.deleteAll();
    }

    protected abstract void setDeleteAll();

    /**
     * Converts the passed proveedorCliente to a DTO.
     */
    protected ProveedorCliente toDTO(ProveedorCliente proveedorCliente) {
        return proveedorCliente;
    }

    /**
     * Converts the passed dto to a ProveedorCliente.
     * Convenient for query by example.
     */
    protected ProveedorCliente toEntity(ProveedorCliente dto) {
        return dto;
    }

}