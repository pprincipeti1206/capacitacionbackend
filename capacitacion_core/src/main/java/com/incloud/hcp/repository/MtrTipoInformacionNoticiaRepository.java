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

import com.incloud.hcp.domain.MtrTipoInformacionNoticia;
import com.incloud.hcp.domain.MtrTipoInformacionNoticia_;
import com.incloud.hcp.repository._framework.JPACustomRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@NoRepositoryBean
public interface MtrTipoInformacionNoticiaRepository extends JPACustomRepository<MtrTipoInformacionNoticia, Integer> {

    /**
     * Return the persistent instance of {@link MtrTipoInformacionNoticia} with the given unique property value descripcion,
     * or null if there is no such persistent instance.
     *
     * @param descripcion the unique value
     * @return the corresponding {@link MtrTipoInformacionNoticia} persistent instance or null
     */
    @RestResource(path = "/getByDescripcion")
    MtrTipoInformacionNoticia getByDescripcion(@Param("descripcion") String descripcion);

    default List<MtrTipoInformacionNoticia> findCompletePaginated(String query, int maxResults) {
        MtrTipoInformacionNoticia probe = new MtrTipoInformacionNoticia();
        //probe.setDescripcion(query);
        probe.setDescripcion(null);
        ExampleMatcher matcher = ExampleMatcher.matching() //
                .withMatcher(MtrTipoInformacionNoticia_.descripcion.getName(), match -> match.ignoreCase().startsWith());

        Page<MtrTipoInformacionNoticia> page = this.findAll(Example.of(probe, matcher), PageRequest.of(0, maxResults));
        return page.getContent();
    }

}