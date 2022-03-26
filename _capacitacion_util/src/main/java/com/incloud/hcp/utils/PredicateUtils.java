package com.incloud.hcp.utils;

import com.incloud.hcp.domain.BaseDomain;
import com.incloud.hcp.enums.ConditionEnum;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PredicateUtils {

    public static void addPredicatesListValorPrimitivo(
            List<Predicate> predicates,
            String nombreCampo,
            List<?> listaValorCampo,
            CriteriaBuilder cb,
            Root<? extends BaseDomain> root) {
        if (listaValorCampo == null || listaValorCampo.size() <=0 ) {
            return;
        }
        predicates.add(root.get(nombreCampo).in(listaValorCampo));
        return;
    }

    public static void addPredicatesListValorBean(
            List<Predicate> predicates,
            String nombreCampo,
            List<?> listaValorCampo,
            CriteriaBuilder cb,
            Root<? extends BaseDomain> root) {
        if (listaValorCampo == null || listaValorCampo.size() <=0 ) {
            return;
        }
        Join<? extends BaseDomain, ?> listaJoin = root.join(nombreCampo);
        predicates.add(listaJoin.in(listaValorCampo));
        return;
    }

    public static void addPredicates(
            List<Predicate> predicates,
            String condicion,
            String nombreCampo,
            String valorCampo,
            CriteriaBuilder cb,
            Root<? extends BaseDomain> root) {
        if (!Optional.ofNullable(valorCampo).isPresent()) {
            return;
        }
        valorCampo = valorCampo.toUpperCase().trim();
        condicion = condicion.toUpperCase();
        if (!Optional.ofNullable(condicion).isPresent()) {
            condicion = ConditionEnum.CONTIENE.getEstado();
        }
        if (ConditionEnum.IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.equal(cb.upper(root.get(nombreCampo)), valorCampo));
        }
        if (ConditionEnum.DIFERENTE.getEstado().equals(condicion)) {
            predicates.add(cb.notEqual(cb.upper(root.get(nombreCampo)), valorCampo));
        }
        if (ConditionEnum.MAYOR.getEstado().equals(condicion)) {
            predicates.add(cb.greaterThan(cb.upper(root.get(nombreCampo)), valorCampo));
        }
        if (ConditionEnum.MAYOR_IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.greaterThanOrEqualTo(cb.upper(root.get(nombreCampo)), valorCampo));
        }
        if (ConditionEnum.MENOR.getEstado().equals(condicion)) {
            predicates.add(cb.lessThan(cb.upper(root.get(nombreCampo)), valorCampo));
        }
        if (ConditionEnum.MENOR_IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.lessThanOrEqualTo(cb.upper(root.get(nombreCampo)), valorCampo));
        }
        if (ConditionEnum.CONTIENE.getEstado().equals(condicion)) {
            predicates.add(cb.like(cb.upper(root.get(nombreCampo)), "%" + valorCampo + "%"));
        }
        return;
    }

    public static void addPredicates(
            List<Predicate> predicates,
            String condicion,
            String nombreCampo,
            Integer valorCampo,
            CriteriaBuilder cb,
            Root<? extends BaseDomain> root) {
        if (!Optional.ofNullable(valorCampo).isPresent()) {
            return;
        }
        if (!Optional.ofNullable(condicion).isPresent()) {
            condicion = ConditionEnum.IGUAL.getEstado();
        }
        if (ConditionEnum.IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.equal(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.DIFERENTE.getEstado().equals(condicion)) {
            predicates.add(cb.notEqual(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MAYOR.getEstado().equals(condicion)) {
            predicates.add(cb.greaterThan(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MAYOR_IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MENOR.getEstado().equals(condicion)) {
            predicates.add(cb.lessThan(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MENOR_IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.lessThanOrEqualTo(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.CONTIENE.getEstado().equals(condicion)) {
            predicates.add(cb.equal(root.get(nombreCampo), valorCampo));
        }
    }

    public static void addPredicates(
            List<Predicate> predicates,
            String condicion,
            String nombreCampo,
            Long valorCampo,
            CriteriaBuilder cb,
            Root<? extends BaseDomain> root) {
        if (!Optional.ofNullable(valorCampo).isPresent()) {
            return;
        }
        if (!Optional.ofNullable(condicion).isPresent()) {
            condicion = ConditionEnum.IGUAL.getEstado();
        }
        if (ConditionEnum.IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.equal(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.DIFERENTE.getEstado().equals(condicion)) {
            predicates.add(cb.notEqual(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MAYOR.getEstado().equals(condicion)) {
            predicates.add(cb.greaterThan(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MAYOR_IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MENOR.getEstado().equals(condicion)) {
            predicates.add(cb.lessThan(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MENOR_IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.lessThanOrEqualTo(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.CONTIENE.getEstado().equals(condicion)) {
            predicates.add(cb.equal(root.get(nombreCampo), valorCampo));
        }
    }

    public static void addPredicates(
            List<Predicate> predicates,
            String condicion,
            String nombreCampo,
            BigDecimal valorCampo,
            CriteriaBuilder cb,
            Root<? extends BaseDomain> root) {
        if (!Optional.ofNullable(valorCampo).isPresent()) {
            return;
        }
        if (!Optional.ofNullable(condicion).isPresent()) {
            condicion = ConditionEnum.IGUAL.getEstado();
        }
        if (ConditionEnum.IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.equal(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.DIFERENTE.getEstado().equals(condicion)) {
            predicates.add(cb.notEqual(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MAYOR.getEstado().equals(condicion)) {
            predicates.add(cb.greaterThan(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MAYOR_IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MENOR.getEstado().equals(condicion)) {
            predicates.add(cb.lessThan(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MENOR_IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.lessThanOrEqualTo(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.CONTIENE.getEstado().equals(condicion)) {
            predicates.add(cb.equal(root.get(nombreCampo), valorCampo));
        }
    }

    public static void addPredicates(
            List<Predicate> predicates,
            String condicion,
            String nombreCampo,
            Double valorCampo,
            CriteriaBuilder cb,
            Root<? extends BaseDomain> root) {
        if (!Optional.ofNullable(valorCampo).isPresent()) {
            return;
        }
        if (!Optional.ofNullable(condicion).isPresent()) {
            condicion = ConditionEnum.IGUAL.getEstado();
        }
        if (ConditionEnum.IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.equal(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.DIFERENTE.getEstado().equals(condicion)) {
            predicates.add(cb.notEqual(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MAYOR.getEstado().equals(condicion)) {
            predicates.add(cb.greaterThan(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MAYOR_IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MENOR.getEstado().equals(condicion)) {
            predicates.add(cb.lessThan(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MENOR_IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.lessThanOrEqualTo(root.get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.CONTIENE.getEstado().equals(condicion)) {
            predicates.add(cb.equal(root.get(nombreCampo), valorCampo));
        }
    }

    public static void addPredicates(
            List<Predicate> predicates,
            String condicion,
            String nombreCampo,
            Date valorCampo,
            CriteriaBuilder cb,
            Root<? extends BaseDomain> root) {
        if (!Optional.ofNullable(valorCampo).isPresent()) {
            return;
        }
        boolean contieneTime = true;
        int hora = DateUtils.getHour(valorCampo);
        int minuto = DateUtils.getMinute(valorCampo);
        int segundo = DateUtils.getSeconds(valorCampo);
        if (hora == 0 && minuto == 0 && segundo == 0) {
            contieneTime = false;
        }
        String sfecha = new String("");
        Date fecha = null;
        Date fechaSiguiente = null;
        if (!contieneTime) {
            sfecha = DateUtils.convertDateToString("dd/MM/yyyy", valorCampo);
            fecha = DateUtils.convertStringToDateSinException("dd/MM/yyyy", sfecha);
            fechaSiguiente = DateUtils.sumarRestarDias(fecha, 1);
        }

        if (!Optional.ofNullable(condicion).isPresent()) {
            condicion = ConditionEnum.IGUAL.getEstado();
        }
        if (ConditionEnum.IGUAL.getEstado().equals(condicion)) {
            if (!contieneTime) {
                predicates.add(cb.greaterThanOrEqualTo(root.<Date>get(nombreCampo), fecha));
                predicates.add(cb.lessThan(root.<Date>get(nombreCampo), fechaSiguiente));
            }
            else {
                predicates.add(cb.equal(root.<Date>get(nombreCampo), valorCampo));
            }
        }
        if (ConditionEnum.DIFERENTE.getEstado().equals(condicion)) {
            predicates.add(cb.notEqual(root.<Date>get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MAYOR.getEstado().equals(condicion)) {
            predicates.add(cb.greaterThan(root.<Date>get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MAYOR_IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.greaterThanOrEqualTo(root.<Date>get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MENOR.getEstado().equals(condicion)) {
            predicates.add(cb.lessThan(root.<Date>get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MENOR_IGUAL.getEstado().equals(condicion)) {
            if (!contieneTime) {
                predicates.add(cb.lessThan(root.<Date>get(nombreCampo), fechaSiguiente));
            }
            else {
                predicates.add(cb.lessThanOrEqualTo(root.<Date>get(nombreCampo), valorCampo));
            }
        }
        if (ConditionEnum.CONTIENE.getEstado().equals(condicion)) {
            if (!contieneTime) {
                predicates.add(cb.greaterThanOrEqualTo(root.<Date>get(nombreCampo), fecha));
                predicates.add(cb.lessThan(root.<Date>get(nombreCampo), fechaSiguiente));
            }
            else {
                predicates.add(cb.equal(root.<Date>get(nombreCampo), valorCampo));
            }
        }
    }

    public static void addPredicates(
            List<Predicate> predicates,
            String condicion,
            String nombreCampo,
            LocalDateTime valorCampo,
            CriteriaBuilder cb,
            Root<? extends BaseDomain> root) {
        if (!Optional.ofNullable(valorCampo).isPresent()) {
            return;
        }
        if (!Optional.ofNullable(condicion).isPresent()) {
            condicion = ConditionEnum.IGUAL.getEstado();
        }
        if (ConditionEnum.IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.equal(root.<LocalDateTime>get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.DIFERENTE.getEstado().equals(condicion)) {
            predicates.add(cb.notEqual(root.<LocalDateTime>get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MAYOR.getEstado().equals(condicion)) {
            predicates.add(cb.greaterThan(root.<LocalDateTime>get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MAYOR_IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.greaterThanOrEqualTo(root.<LocalDateTime>get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MENOR.getEstado().equals(condicion)) {
            predicates.add(cb.lessThan(root.<LocalDateTime>get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.MENOR_IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.lessThanOrEqualTo(root.<LocalDateTime>get(nombreCampo), valorCampo));
        }
        if (ConditionEnum.CONTIENE.getEstado().equals(condicion)) {
            predicates.add(cb.equal(root.<LocalDateTime>get(nombreCampo), valorCampo));
        }
    }

    public static void addPredicates(
            List<Predicate> predicates,
            String condicion,
            String nombreCampo,
            BaseDomain valorCampo,
            CriteriaBuilder cb,
            Root<? extends BaseDomain> root) {
        if (!Optional.ofNullable(valorCampo).isPresent()) {
            return;
        }
        condicion = ConditionEnum.IGUAL.getEstado();
        if (ConditionEnum.IGUAL.getEstado().equals(condicion)) {
            predicates.add(cb.equal(root.get(nombreCampo), valorCampo));
        }

    }
}
