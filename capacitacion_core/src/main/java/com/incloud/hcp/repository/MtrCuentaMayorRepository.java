/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/repository/EntityRepository.java.e.vm
 */
package com.incloud.hcp.repository;

import com.incloud.hcp.domain.MtrCuentaMayor;
import com.incloud.hcp.domain.MtrCuentaMayor_;
import com.incloud.hcp.domain.MtrSociedad;
import com.incloud.hcp.domain.MtrTipoImputacion;
import com.incloud.hcp.repository._framework.JPACustomRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
public interface MtrCuentaMayorRepository extends JPACustomRepository<MtrCuentaMayor, Integer> {

    default List<MtrCuentaMayor> findCompletePaginated(String query, int maxResults) {
        MtrCuentaMayor probe = new MtrCuentaMayor();
        //probe.setCodigoCuentaMayor(query);
        probe.setCodigoCuentaMayor(null);
        ExampleMatcher matcher = ExampleMatcher.matching() //
                .withMatcher(MtrCuentaMayor_.codigoCuentaMayor.getName(), match -> match.ignoreCase().startsWith());

        Page<MtrCuentaMayor> page = this.findAll(Example.of(probe, matcher), PageRequest.of(0, maxResults));
        return page.getContent();
    }

    Long countByMtrTipoImputacion(@Param("mtrTipoImputacion") MtrTipoImputacion mtrTipoImputacion);

    Long countByMtrSociedad(@Param("mtrSociedad") MtrSociedad mtrSociedad);

}
