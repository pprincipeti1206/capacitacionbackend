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

import com.incloud.hcp.domain.MigCertificado;
import com.incloud.hcp.domain.MigCertificadoAdjunto;
import com.incloud.hcp.domain.MigCertificadoAdjunto_;
import com.incloud.hcp.repository._framework.JPACustomRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
public interface MigCertificadoAdjuntoRepository extends JPACustomRepository<MigCertificadoAdjunto, Integer> {

    default List<MigCertificadoAdjunto> findCompletePaginated(String query, int maxResults) {
        MigCertificadoAdjunto probe = new MigCertificadoAdjunto();
        //probe.setName(query);
        probe.setName(null);
        ExampleMatcher matcher = ExampleMatcher.matching() //
                .withMatcher(MigCertificadoAdjunto_.name.getName(), match -> match.ignoreCase().startsWith());

        Page<MigCertificadoAdjunto> page = this.findAll(Example.of(probe, matcher), PageRequest.of(0, maxResults));
        return page.getContent();
    }

    Long countByMigCertificado(@Param("migCertificado") MigCertificado migCertificado);

}
