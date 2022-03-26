package com.incloud.hcp.repository._framework.impl;

import com.incloud.hcp.domain.BaseDomain;
import com.incloud.hcp.repository._framework.JPACustomRepository;
import com.incloud.hcp.utils.DateUtils;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JPACustomRepositoryImpl<T extends BaseDomain, I> extends SimpleJpaRepository<T,I> implements JPACustomRepository<T,I> {


    private EntityManager entityManager;

    public JPACustomRepositoryImpl(
            JpaEntityInformation<T, ?> entityInformation,
            EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    /*******************************************/
    /* Metodos de Busqueda Personalizados      */
    /*******************************************/

    @Transactional(readOnly = true)
    public List<T> findByAttributeContainsText(String attributeName, String text) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getDomainClass());
        Root<T> root = query.from(getDomainClass());
        query.select(root).where(builder.like(root.<String> get(attributeName), "%" + text + "%"));
        TypedQuery<T> q = entityManager.createQuery(query);
        Stream<T> listaStream = q.getResultStream();
        return listaStream.collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<T> findByAttributeLeftContainsText(String attributeName, String text) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getDomainClass());
        Root<T> root = query.from(getDomainClass());
        query.select(root).where(builder.like(root.<String> get(attributeName), "%" + text ));
        TypedQuery<T> q = entityManager.createQuery(query);
        Stream<T> listaStream = q.getResultStream();
        return listaStream.collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<T> findByAttributeRightContainsText(String attributeName, String text) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getDomainClass());
        Root<T> root = query.from(getDomainClass());
        query.select(root).where(builder.like(root.<String> get(attributeName),  text + "%"));
        TypedQuery<T> q = entityManager.createQuery(query);
        Stream<T> listaStream = q.getResultStream();
        return listaStream.collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<T> findByAttributeBetweenDate(String attributeName, String sfecha) throws Exception {
        Date fechaInicio = DateUtils.convertStringToDate("dd-MM-yyyy", sfecha);
        Date fechaFin = DateUtils.convertStringToDate("dd-MM-yyyy", sfecha);
        fechaFin = DateUtils.sumarRestarHoras(fechaFin, 23);
        fechaFin = DateUtils.sumarRestarMinutos(fechaFin, 59);
        fechaFin = DateUtils.sumarRestarSegundos(fechaFin, 59);

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getDomainClass());
        Root<T> root = query.from(getDomainClass());
        query.select(root).where(builder.between(root.<Date> get(attributeName),  fechaInicio, fechaFin));
        TypedQuery<T> q = entityManager.createQuery(query);
        Stream<T> listaStream = q.getResultStream();
        return listaStream.collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<T> findByAttributeBetweenLocalDateTime(String attributeName, String sfecha) throws Exception {
        Date fechaInicio = DateUtils.convertStringToDate("dd-MM-yyyy", sfecha);
        Date fechaFin = DateUtils.convertStringToDate("dd-MM-yyyy", sfecha);
        fechaFin = DateUtils.sumarRestarHoras(fechaFin, 23);
        fechaFin = DateUtils.sumarRestarMinutos(fechaFin, 59);
        fechaFin = DateUtils.sumarRestarSegundos(fechaFin, 59);
        LocalDateTime lfechaInicio = DateUtils.convertToLocalDateTime(fechaInicio);
        LocalDateTime lfechaFin = DateUtils.convertToLocalDateTime(fechaFin);

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getDomainClass());
        Root<T> root = query.from(getDomainClass());
        query.select(root).where(builder.between(root.<LocalDateTime> get(attributeName),  lfechaInicio, lfechaFin));
        TypedQuery<T> q = entityManager.createQuery(query);
        Stream<T> listaStream = q.getResultStream();
        return listaStream.collect(Collectors.toList());
    }


    /*******************************************/
    /* Metodos de Busqueda de Campos Auditoria */
    /*******************************************/

    @Transactional(readOnly = true)
    public List<T> findByCreatedBy(String valor) {
        return this.findByAttributeContainsText("createdBy", valor);
    }

    @Transactional(readOnly = true)
    public List<T> findByModifiedBy(String valor) {
        return this.findByAttributeContainsText("modifiedBy", valor);
    }

    @Transactional(readOnly = true)
    public List<T> findByCreatedDateBetween(String valor) throws Exception {
        return this.findByAttributeBetweenLocalDateTime("createdDate", valor);
    }

    @Transactional(readOnly = true)
    public List<T> findByModifiedDateBetween(String valor) throws Exception {
        return this.findByAttributeBetweenLocalDateTime("modifiedDate", valor);
    }

    @Transactional(readOnly = true)
    public List<T> findAuditQuery() {
        AuditReader reader = AuditReaderFactory.get(this.entityManager);
        AuditQuery query = reader.createQuery()
                .forRevisionsOfEntity(getDomainClass(), true, true);
        query.addOrder(AuditEntity.revisionNumber().desc());
        List<T> listaStream = query.getResultList();
        return listaStream;

    }

    @Transactional(readOnly = true)
    public List<T> findAuditQueryBetweenDate(String fechaIni, String fechaFin) throws Exception {
        Date dfechaInicio = DateUtils.convertStringToDate("dd-MM-yyyy", fechaIni);
        Date dfechaFin = DateUtils.convertStringToDate("dd-MM-yyyy", fechaFin);
        dfechaFin = DateUtils.sumarRestarHoras(dfechaFin, 23);
        dfechaFin = DateUtils.sumarRestarMinutos(dfechaFin, 59);
        dfechaFin = DateUtils.sumarRestarSegundos(dfechaFin, 59);
        AuditReader reader = AuditReaderFactory.get(this.entityManager);
        AuditQuery query = reader.createQuery()
                .forRevisionsOfEntity(getDomainClass(), true, true)
                .add(AuditEntity.revisionProperty("timestamp").between(dfechaInicio.getTime(), dfechaFin.getTime()));
        query.addOrder(AuditEntity.revisionNumber().desc());
        List<T> listaStream = query.getResultList();
        return listaStream;

    }

}
