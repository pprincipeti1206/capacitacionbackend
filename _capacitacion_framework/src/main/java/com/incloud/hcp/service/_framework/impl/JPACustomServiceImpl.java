package com.incloud.hcp.service._framework.impl;

import com.incloud.hcp.common.excel.ExcelDefault;
import com.incloud.hcp.domain.AppParametria;
import com.incloud.hcp.domain.BaseDomain;
import com.incloud.hcp.domain.BaseResponseDomain;
import com.incloud.hcp.exception.ServiceException;
import com.incloud.hcp.repository._framework.JPACustomMapperMybatis;
import com.incloud.hcp.repository._framework.JPACustomRepository;
import com.incloud.hcp.service._framework.JPACustomRequiredNewService;
import com.incloud.hcp.service._framework.JPACustomService;
import com.incloud.hcp.service._framework.bean.BeanCargaMasivoDTO;
import com.incloud.hcp.service._framework.bean.BeanFinalDatosPredicateDto;
import com.incloud.hcp.service._framework.bean.BeanListaMasivoDTO;
import com.incloud.hcp.service.support.PageRequestByExample;
import com.incloud.hcp.service.support.PageResponse;
import com.incloud.hcp.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.awt.Color;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public abstract class JPACustomServiceImpl<R extends BaseResponseDomain, T extends BaseDomain, I> implements JPACustomService<R, T, I> {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    protected MessageSource messageSource;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    protected JPACustomRepository<T, I> mainRepository;

    @Autowired
    protected JPACustomMapperMybatis<T> jpaCustomMapperMybatis;

    @Autowired
    protected JPACustomRequiredNewService<T> mainRequiredNewService;


    /*****************************/
    /* Metodos de Busqueda       */
    /*****************************/

    protected List<T> setAfterFindJpa(List<T> result) {
        return result;
    }

    @Transactional(readOnly = true)
    public List<T> findMybatis(T req) {
        log.debug("Ingresando findMybatis: ", req);
        Example<T> example = null;
        T bean = req;
        List<T> list = this.jpaCustomMapperMybatis.getLista(bean);
        return list;
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        log.debug("Ingresando findAll");
        Sort sort = Sort.by("id");
        sort = this.setFindAll(sort);
        Stream<T> listaStream = this.mainRepository.findAll(sort).parallelStream();
        List<T> result = listaStream.collect(Collectors.toList());
        result = this.setAfterFindJpa(result);
        return result;
    }
    protected abstract Sort setFindAll(Sort sort);


    @Transactional(readOnly = true)
    public List<T> find(T req) {
        log.debug("Ingresando find: ", req);
        Example<T> example = null;
        T bean = req;
        ExampleMatcher matcher = null;
        if (bean != null) {
            matcher = this.setAbstractFind(matcher);
            example = Example.of(bean, matcher);
        }
        Sort sort = Sort.by("id");
        sort = this.setFind(req, matcher, example, sort);
        Stream<T> listaStream = this.mainRepository.findAll(example, sort).parallelStream();
        List<T> result = listaStream.collect(Collectors.toList());
        result = this.setAfterFindJpa(result);
        return result;
    }
    protected abstract ExampleMatcher setAbstractFind(ExampleMatcher matcher);
    protected abstract Sort setFind(T req, ExampleMatcher matcher, Example<T> example, Sort sort);


    @Transactional(readOnly = true)
    public Optional<T> findOne(I id) {
        log.debug("Ingresando findOne: ", id);
        return this.mainRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public PageResponse<T> findPaginated(PageRequestByExample<T> req) {
        log.debug("Ingresando findPaginated: ", req);
        Example<T> example = null;
        T bean = req.bean;
        ExampleMatcher matcher = null;
        if (bean != null) {
            matcher = this.setAbstractFind(matcher);
            example = Example.of(bean, matcher);
        }
        Page<T> page;
        req.generarLazyDefecto("id");
        this.setFindPaginated(req, matcher, example);
        if (example != null) {
            page = this.mainRepository.findAll(example, req.toPageable());
        } else {
            page = this.mainRepository.findAll(req.toPageable());
        }
        List<T> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        content = this.setAfterFindJpa(content);
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }
    protected abstract void setFindPaginated(
            PageRequestByExample<T> req,
            ExampleMatcher matcher,
            Example<T> example);


    @Transactional(readOnly = true)
    public List<T> findCondicion(R req) throws Exception {
        log.debug("Ingresando findCondicion: ", req);
        R bean = req;
        Sort sort = Sort.by("id");
        req = this.setFindBeanCondicion(req);
        sort = this.setFindCondicion(sort);
        List<T> lista = new ArrayList<T>();
        lista = this.getDataPredicate(req);
        lista = this.setAfterFindJpa(lista);
        return lista;
    }

    @Transactional(readOnly = true)
    public List<T> findCondicionVersion02(R req) throws Exception {
        log.debug("Ingresando findCondicion: ", req);
        R bean = req;
        Sort sort = Sort.by("id");
        sort = this.setFindCondicion(sort);
        List<T> lista = new ArrayList<T>();
        lista = this.getDataPredicate(req);
        lista = this.setAfterFindJpa(lista);
        return lista;
    }

    protected R setFindBeanCondicion(R req) throws Exception {
        return req;
    }
    protected Sort setFindCondicion(Sort sort) {
        return sort;
    }
    protected abstract T setObtenerBeanResponse(R bean) ;
    protected abstract Class<T> setObtenerClassBean();
    protected List<Predicate> setAbstractPredicate(R bean, CriteriaBuilder cb, Root<T> root) {
        return null;
    }
    protected List<T> getDataPredicate(R bean) throws Exception {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        T beanEntity = this.setObtenerBeanResponse(bean);
        if (!Optional.ofNullable(beanEntity).isPresent()) {
            String msg = this.messageSource.getMessage("message.beanEntity.requerido", null, LocaleContextHolder.getLocale());
            throw new Exception(msg);
        }
        CriteriaQuery<T> query= cb.createQuery(this.setObtenerClassBean());
        Root<T> root = query.from(this.setObtenerClassBean());

        List<Predicate> predicates = this.setAbstractPredicate(bean, cb, root);
        predicates = this.setAdicionalDeltaPredicate(
                predicates,
                bean,
                cb,
                query,
                root);
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        Stream<T> listaStream = this.entityManager.createQuery(query).getResultStream();
        //return listaStream.collect(Collectors.toList());

        List<T> listaFinal = listaStream.collect(Collectors.toList());
        BeanFinalDatosPredicateDto<T> beanPredicateFinal = new BeanFinalDatosPredicateDto<T>();
        beanPredicateFinal.setListaFinal(listaFinal);
        beanPredicateFinal.setTotal(0);

        if (listaFinal != null && listaFinal.size() >= 0) {
            long total = 0;
            beanPredicateFinal = this.setAdicionalMasFiltrosPredicate(bean, listaFinal, total);
        }
        return beanPredicateFinal.getListaFinal();
    }


    @Transactional(readOnly = true)
    public PageResponse<T> findCondicionPaginated(R req, PageRequest pageRequest) throws Exception {
        log.debug("Ingresando findCondicionPaginated: ", req);
        R bean = req;
        Pageable pageable = bean.getPageRequest().toPageable();
        Sort sort = Sort.by("id");
        req = this.setFindBeanCondicion(req);
        sort = this.setFindCondicion(sort);
        Page<T> page =  this.getDataPredicatePaginated(req, pageRequest, pageable);
        List<T> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        content = this.setAfterFindJpa(content);
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }
    protected Page<T> getDataPredicatePaginated(R bean, PageRequest pageRequest, Pageable pageable) throws Exception {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        T beanEntity = this.setObtenerBeanResponse(bean);
        if (!Optional.ofNullable(beanEntity).isPresent()) {
            String msg = this.messageSource.getMessage("message.beanEntity.requerido", null, LocaleContextHolder.getLocale());
            throw new Exception(msg);
        }
        CriteriaQuery<T> query= cb.createQuery(this.setObtenerClassBean());
        Root<T> root = query.from(this.setObtenerClassBean());

        List<Predicate> predicates = this.setAbstractPredicate(bean, cb, root);
        predicates = this.setAdicionalDeltaPredicate(
                predicates,
                bean,
                cb,
                query,
                root);
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        TypedQuery<T> tquery = this.entityManager.createQuery(query);
        tquery.setFirstResult((pageRequest.getPageNumber() - 1) * pageRequest.getPageSize());
        tquery.setMaxResults(pageRequest.getPageSize());


        /* Obteniendo el total de registros */
        CriteriaQuery<Long> countCriteria = cb.createQuery(Long.class);
        Root<T> countRoot = countCriteria.from(this.setObtenerClassBean());
        countRoot = this.setAdicionalDeltaTotalPredicate(bean, countRoot);
        long total = this.entityManager.createQuery(
                countCriteria.select(cb.count(countRoot))
                        .where(predicates.toArray(new Predicate[predicates.size()]))
        ).getSingleResult();


        List<T> listaFinal = tquery.getResultList();
        BeanFinalDatosPredicateDto<T> beanPredicateFinal = new BeanFinalDatosPredicateDto<T>();
        beanPredicateFinal.setListaFinal(listaFinal);
        beanPredicateFinal.setTotal(total);
        if (listaFinal != null && listaFinal.size() >= 0) {
            beanPredicateFinal = this.setAdicionalMasFiltrosPredicate(bean, listaFinal, total);
        }
        return new PageImpl<T>(
                beanPredicateFinal.getListaFinal(),
                pageable,
                beanPredicateFinal.getTotal());
    }


//    protected void setAdicionalSubqueryDeltaPredicate(
//            R beanResponse,
//            List<Predicate> predicates,
//            CriteriaBuilder cb,
//            Root<T> root,
//            Root<T> countRoot,
//            CriteriaQuery<T> query,
//            CriteriaQuery<Long> countCriteria
//
//    ) {
//
//        return ;
//    }

    protected BeanFinalDatosPredicateDto<T> setAdicionalMasFiltrosPredicate(
            R beanResponse,
            List<T> listaFinal,
            long total) throws Exception {
        BeanFinalDatosPredicateDto<T> beanPredicate = new BeanFinalDatosPredicateDto<T>();
        beanPredicate.setListaFinal(listaFinal);
        beanPredicate.setTotal(total);
        return beanPredicate;
    }

    protected List<Predicate> setAdicionalDeltaPredicate(
            List<Predicate> predicates,
            R bean,
            CriteriaBuilder cb,
            CriteriaQuery<T> query,
            Root<T> root) throws Exception{
        return predicates;
    }

    protected Root<T> setAdicionalDeltaTotalPredicate(
            R bean,
            Root<T> countRoot) throws Exception{
        return countRoot;
    }


    /*******************************************************/
    /* Metodos de Busqueda de Campos Auditoria (Paginados) */
    /*******************************************************/

    public PageResponse<T> findAllPaginatedSortCreatedDate(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size,
                Sort.by("createdDate")
                        .descending()
                        .and(Sort.by("createdBy")));
        Page<T> pageResult = this.mainRepository.findAll(pageRequest);
        List<T> content = pageResult.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(pageResult.getTotalPages(), pageResult.getTotalElements(), content);
    }



    /****************/
    /* METODOS CRUD */
    /****************/

    protected abstract String validacionesPrevias(T bean) throws Exception;
    protected T completarDatosBean(T bean) throws Exception {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }
    protected T setCompletarDatosBean(T bean) throws Exception {
        return bean;
    }
    protected String validacionesPreviasCreate(T bean) throws Exception {
        return null;
    }
    protected String validacionesPreviasSave(T bean) throws Exception {
        return null;
    }

    /**
     * Save new entity or update the corresponding entity if any.
     */
    public T create(T dto) throws Exception {
        log.debug("Ingresando create: ", dto);
        if (dto == null) {
            return null;
        }
        T bean;
        bean = this.setAbstractCreate(dto);
        bean = this.completarDatosBean(bean);
        bean = this.setCreate(bean);
        String msg = this.validacionesPrevias(bean);
        if (StringUtils.isNotBlank(msg)) {
            throw new ServiceException(msg);
        }
        msg = this.validacionesPreviasCreate(bean);
        if (StringUtils.isNotBlank(msg)) {
            throw new ServiceException(msg);
        }
        bean = this.mainRepository.save(bean);
        bean = this.setAfterCreate(bean);
        return bean;
    }
    protected abstract T setAbstractCreate(T dto) throws Exception;
    protected abstract T setCreate(T dto) throws Exception;
    protected T setAfterCreate(T dto) throws Exception {
        return dto;
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    public T save(T dto) throws Exception {
        log.debug("Ingresando save: ", dto);
        if (dto == null) {
            return null;
        }
        dto = this.completarDatosBean(dto);
        dto = this.setBeforeSave(dto);
        this.setSave(dto);
        String msg = this.validacionesPrevias(dto);
        if (StringUtils.isNotBlank(msg)) {
            throw new ServiceException(msg);
        }
        msg = this.validacionesPreviasSave(dto);
        if (StringUtils.isNotBlank(msg)) {
            throw new ServiceException(msg);
        }
        dto = this.mainRepository.save(dto);
        dto = this.setAfterSave(dto);
        return dto;
    }
    protected abstract void setSave(T dto) throws Exception;
    protected T setBeforeSave(T dto) throws Exception {
        return dto;
    }
    protected T setAfterSave(T dto) throws Exception {
        return dto;
    }


    /**
     * Delete the passed dto as a new entity or update the corresponding entity if any.
     */
    public void delete(I id) throws Exception{
        log.debug("Ingresando delete: ", id);
        if (id == null) {
            return;
        }
        this.setDelete(id);
        this.mainRepository.deleteById(id);
    }
    protected abstract void setDelete(I id) throws Exception;


    public void deleteAll() throws Exception{
        log.debug("Ingresando deleteAll");
        this.setDeleteAll();
        this.mainRepository.deleteAll();
    }

    protected void setDeleteAll() throws Exception {

    }


    /************************/
    /* METODOS CRUD Masivos */
    /************************/

    public List<T> saveAll(List<T> lista) throws Exception {
        return this.mainRepository.saveAll(lista);
    }


    public List<BeanCargaMasivoDTO<T>> uploadExcel(InputStream in) throws Exception{
        log.debug("Ingresando uploadExcel");
        List<BeanCargaMasivoDTO<T>> listaDTO = new ArrayList<BeanCargaMasivoDTO<T>>();
        int inicioRegistroData = 1;
        AppParametria appParametriaData = this.setObtenerRegistroConfiguracionUploadExcel();
        if (Optional.of(appParametriaData).isPresent()) {
            inicioRegistroData = new Integer(appParametriaData.getValue1()).intValue();
        }
        try {
            Workbook workbook = new XSSFWorkbook(in);
            try {
                Sheet datatypeSheet = workbook.getSheetAt(0);
                Iterator<Row> iterator = datatypeSheet.iterator();
                int contadorRegistro = 1;
                while (iterator.hasNext()) {
                    if (contadorRegistro < inicioRegistroData) {
                        contadorRegistro++;
                        Row currentRow = iterator.next();
                        continue;
                    }
                    BeanCargaMasivoDTO<T> beanCargaMasivoDTO = this.createInstanceMasivoDTO();
                    T bean = this.setInicializarBeanUpdateExcel();
                    Row currentRow = iterator.next();
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    boolean error = false;
                    try {
                        while (cellIterator.hasNext()) {
                            Cell currentCell = cellIterator.next();
                            bean = this.setUploadExcel(currentCell, bean, currentCell.getColumnIndex() + 1);
                        }
                        String mensaje = this.validacionesPrevias(bean);
                        if (StringUtils.isNotBlank(mensaje)) {
                            throw new ServiceException(mensaje);
                        }
                        if (this.depurarUploadExcel(bean)) {
                            beanCargaMasivoDTO.setBean(bean);
                            beanCargaMasivoDTO.setErrorMensaje("");
                            beanCargaMasivoDTO.setError(false);
                        }

                    } catch (Exception e) {
                        beanCargaMasivoDTO.setBean(bean);
                        beanCargaMasivoDTO.setErrorMensaje(Utils.obtieneMensajeErrorException(e));
                        beanCargaMasivoDTO.setError(true);
                    }
                    listaDTO.add(beanCargaMasivoDTO);
                }
            } catch (Exception exw) {

            } finally {
                workbook.close();
            }
        } catch (Exception ex) {

        }
        return listaDTO;
    }
    protected abstract AppParametria setObtenerRegistroConfiguracionUploadExcel();
    protected abstract T setInicializarBeanUpdateExcel();
    protected boolean depurarUploadExcel(T dto) {
        return true;
    }
    protected T setUploadExcel(Cell currentCell, T bean, int contador) throws Exception {
        return bean;
    }

    /**
     * Save All the passed dto as a new entity or update the corresponding entity if any.
     */
    public BeanListaMasivoDTO<BeanCargaMasivoDTO<T>> saveAllNewRequired(List<T> listaBean) throws Exception {
        log.debug("Ingresando saveAll: ");
        if (listaBean == null || listaBean.size() <= 0) {
            return null;
        }
        BeanListaMasivoDTO<BeanCargaMasivoDTO<T>> beanListaMasivoDTO = this.createInstanceListaMasivoDTO();
        List<BeanCargaMasivoDTO<T>> listaDTO = new ArrayList<BeanCargaMasivoDTO<T>>();
        boolean ejecucionOK = true;

        for (T bean : listaBean) {
            BeanCargaMasivoDTO<T> beanCargaMasivoDTO = this.createInstanceMasivoDTO();
            beanCargaMasivoDTO.setError(false);
            String mensaje = this.setSaveMasivo(bean);
            if (StringUtils.isNotBlank(mensaje)) {
                ejecucionOK = false;
                beanCargaMasivoDTO.setError(true);
                beanCargaMasivoDTO.setErrorMensaje(mensaje);
            } else {
                try {
                    mensaje = this.validacionesPrevias(bean);
                    if (StringUtils.isNotBlank(mensaje)) {
                        throw new ServiceException(mensaje);
                    }
                    bean = this.completarDatosBean(bean);
                    bean = this.mainRequiredNewService.saveMasivo(bean);
                    bean = this.completarDatosBeanAfterSaveMasivo(bean);
                    beanCargaMasivoDTO.setErrorMensaje("ACTUALIZADO EN BD");
                    beanCargaMasivoDTO.setError(false);
                } catch (Exception e) {
                    ejecucionOK = false;
                    beanCargaMasivoDTO.setError(true);
                    beanCargaMasivoDTO.setErrorMensaje(Utils.obtieneMensajeErrorException(e));
                }
            }
            beanCargaMasivoDTO.setBean(bean);
            listaDTO.add(beanCargaMasivoDTO);
        }
        beanListaMasivoDTO.setEjecucionOK(ejecucionOK);
        beanListaMasivoDTO.setListaDTO(listaDTO);
        return beanListaMasivoDTO;
    }
    protected T completarDatosBeanAfterSaveMasivo(T dto) {
        return dto;
    }
    protected abstract String setSaveMasivo(T dto) throws Exception;


    /**
     * Delete masivo the passed dto as a new entity or update the corresponding entity if any.
     */
    public BeanListaMasivoDTO<BeanCargaMasivoDTO<T>> deleteAllNewRequired(List<T> listaBean) throws Exception {
        log.debug("Ingresando deleteMasivo: ");
        if (listaBean == null || listaBean.size() <= 0) {
            return null;
        }
        BeanListaMasivoDTO<BeanCargaMasivoDTO<T>> beanListaMasivoDTO = this.createInstanceListaMasivoDTO();
        List<BeanCargaMasivoDTO<T>> listaDTO = new ArrayList<BeanCargaMasivoDTO<T>>();
        boolean ejecucionOK = true;

        listaBean = this.setBeforeDeleteMasivo(listaBean);
        for (T dto : listaBean) {
            BeanCargaMasivoDTO<T> beanCargaMasivoDTO = this.createInstanceMasivoDTO();
            beanCargaMasivoDTO.setError(false);
            try {
                I id = this.setObtenerId(dto);
                this.setDelete(id);
                this.mainRequiredNewService.deleteMasivo(dto);
                beanCargaMasivoDTO.setErrorMensaje("ELIMINADO EN BD");
                beanCargaMasivoDTO.setError(false);
            } catch (Exception e) {
                ejecucionOK = false;
                beanCargaMasivoDTO.setError(true);
                beanCargaMasivoDTO.setErrorMensaje(Utils.obtieneMensajeErrorException(e));
            }
        }
        this.setAfterDeleteMasivo();
        beanListaMasivoDTO.setEjecucionOK(ejecucionOK);
        beanListaMasivoDTO.setListaDTO(listaDTO);
        return beanListaMasivoDTO;
    }
    protected abstract I setObtenerId(T bean);
    protected List<T> setBeforeDeleteMasivo(List<T> listaDto) throws Exception {
        return listaDto;
    }
    protected void setAfterDeleteMasivo() throws Exception {
        return;
    }



    /*****************************/
    /* Metodos que generan Excel */
    /*****************************/

    @Transactional(readOnly = true)
    public HSSFWorkbook downloadExcel(T req) {
        log.debug("Ingresando downloadExcel: ", req);
        List<T> lista = this.find(req);
        Optional<List<T>> oList = Optional.ofNullable(lista);
        if (!oList.isPresent()) {
            return null;
        }
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet();
        int numberOfSheets = book.getNumberOfSheets();
        book.setSheetName(numberOfSheets - 1, this.devuelveNombreSheet());
        ExcelDefault.createTitle(sheet, this.devuelveListaHeaderExcelXML(), book.createCellStyle(), book.createFont());

        for(T bean: lista) {

            int lastRow = sheet.getLastRowNum();
            int i = lastRow < 0 ? 0 : lastRow;
            HSSFRow dataRow = sheet.createRow(i + 1);
            int contador = this.setAbstractDownloadExcel(bean, dataRow);

            /* Agregar aqui si desean colocar mas campos del BEAN */
            this.setDownloadExcelItem(bean, dataRow);

        };
        this.setDownloadExcel(sheet);
        int totalColumn = sheet.getRow(0).getLastCellNum();
        for (int i = 0; i < totalColumn; i++) {
            sheet.autoSizeColumn(i, true);
        }
        return book;
    }
    protected abstract String devuelveNombreSheet();
    protected abstract String devuelveListaHeaderExcelXML();
    protected abstract int setAbstractDownloadExcel(T bean, HSSFRow dataRow);
    protected void setDownloadExcelItem(T bean, HSSFRow dataRow) {

    }
    protected void setDownloadExcel(HSSFSheet sheet) {

    }


    @Transactional(readOnly = true)
    public SXSSFWorkbook downloadExcelSXLSX(T req) {
        log.debug("Ingresando downloadExcelSXLSX: ", req);
        List<T> lista = this.find(req);
        Optional<List<T>> oList = Optional.ofNullable(lista);
        if (!oList.isPresent()) {
            return null;
        }

        SXSSFWorkbook book = new SXSSFWorkbook(100);
        XSSFWorkbook xbook = book.getXSSFWorkbook();
        SXSSFSheet sheet = book.createSheet();
        //sheet.trackAllColumnsForAutoSizing();
        int numberOfSheets = book.getNumberOfSheets();
        book.setSheetName(numberOfSheets - 1, this.devuelveNombreSheet());
        int nroColumnas = ExcelDefault.createTitleAndWidth(
                xbook,
                sheet,
                this.devuelveListaHeaderExcelXML(),
                this.devuelveNombreSheet(),
                null);

        sheet.untrackAllColumnsForAutoSizing();

        XSSFCellStyle cellStyle01 = ExcelDefault.devuelveCellStyle(xbook, new Color(0, 0, 1), new Color(226, 239, 218), false, (short) 10);
        XSSFCellStyle cellStyle02 = ExcelDefault.devuelveCellStyle(xbook, new Color(0, 0, 192), new Color(255, 255, 255), false, (short) 10);
        List<CellStyle> cellStyleList = null;
        List<CellStyle> cellStyleList01 = ExcelDefault.generarCellStyle(xbook, cellStyle01);
        List<CellStyle> cellStyleList02 = ExcelDefault.generarCellStyle(xbook, cellStyle02);
        boolean filaImpar = true;
        for (T bean : lista) {
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
            contador = this.setAbstractDownloadExcelSXLSX(bean, dataRow, cellStyleList);

            /* Agregar aqui si desean colocar mas campos del BEAN */
            this.setDownloadExcelItem(bean, dataRow);
        }
        this.setDownloadExcel(sheet);
        return book;
    }
    protected abstract int setAbstractDownloadExcelSXLSX(T bean, Row dataRow, List<CellStyle> cellStyleList);
    protected void setDownloadExcelItem(T bean, Row dataRow) {

    }
    protected void setDownloadExcel(Sheet sheet) {

    }


    @Transactional(readOnly = true)
    public SXSSFWorkbook generateInsertExcelSXLSX(T req) {
        log.debug("Ingresando generateInsertExcelSXLSX: ", req);
        List<T> lista = this.find(req);
        Optional<List<T>> oList = Optional.ofNullable(lista);
        if (!oList.isPresent()) {
            return null;
        }

        SXSSFWorkbook book = new SXSSFWorkbook(100);
        XSSFWorkbook xbook = book.getXSSFWorkbook();
        SXSSFSheet sheet = book.createSheet();
        sheet.trackAllColumnsForAutoSizing();
        int numberOfSheets = book.getNumberOfSheets();
        book.setSheetName(numberOfSheets - 1, this.devuelveNombreSheet());
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

        for (T bean : lista) {
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
            String sqlInsert = this.setAbstractGenerateInsertExcelSXLSX(bean);
            ExcelDefault.setValueCell(sqlInsert, dataRow.createCell(contador), "S", cellStyleList);
            contador++;
        }
        return book;
    }
    protected abstract String setAbstractGenerateInsertExcelSXLSX(T bean);


    /************************/
    /* Instancia de Bean    */
    /************************/

    /**
     * Converts the passed moduleSystem to a DTO.
     */
    protected final T toDTO(T bean) {
        return bean;
    }
    protected abstract T createInstance();
    protected abstract BeanCargaMasivoDTO<T> createInstanceMasivoDTO();
    protected abstract BeanListaMasivoDTO<BeanCargaMasivoDTO<T>> createInstanceListaMasivoDTO();




}