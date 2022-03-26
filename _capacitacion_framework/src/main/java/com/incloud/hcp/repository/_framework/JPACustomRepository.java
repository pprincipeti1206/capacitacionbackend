package com.incloud.hcp.repository._framework;

import com.incloud.hcp.domain.BaseDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@NoRepositoryBean
public interface JPACustomRepository<T extends BaseDomain, I> extends JpaRepository<T, I> {

    default  T createInstance() {
        try {
            Type sooper = getClass().getGenericSuperclass();
            Type t = ((ParameterizedType)sooper).getActualTypeArguments()[ 0 ];
            return (T)(Class.forName( t.toString() ).newInstance());


        }
        catch( Exception e ) {
            return null;
        }
    }

    /*******************************************/
    /* Metodos de Busqueda Personalizados      */
    /*******************************************/

    List<T> findByAttributeContainsText(String attributeName, String text);
    List<T> findByAttributeLeftContainsText(String attributeName, String text);
    List<T> findByAttributeRightContainsText(String attributeName, String text);
    List<T> findByAttributeBetweenDate(String attributeName, String sfecha) throws Exception;
    List<T> findByAttributeBetweenLocalDateTime(String attributeName, String sfecha) throws Exception;


    /*******************************************/
    /* Metodos de Busqueda de Campos Auditoria */
    /*******************************************/
    List<T> findByCreatedBy(String valor);
    List<T> findByModifiedBy(String valor);
    List<T> findByCreatedDateBetween(String valor) throws Exception;
    List<T> findByModifiedDateBetween(String valor) throws Exception;
    List<T> findAuditQuery();
    List<T> findAuditQueryBetweenDate(String fechaIni, String fechaFin) throws Exception;
}
