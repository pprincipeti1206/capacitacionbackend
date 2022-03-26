package com.incloud.hcp.repository._framework.impl;

import com.incloud.hcp.domain.BaseDomain;
import com.incloud.hcp.repository._framework.JPACustomJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class JPACustomJdbcDaoImpl<T extends BaseDomain> implements JPACustomJdbcDao<T> {

    @Autowired
    private JdbcTemplate jdbcMaster;


    public void insertJdbc(final List<T> listaInsert) {
        StringBuilder stringSQL = this.devuelveInsertStringBuilderSQL();
        jdbcMaster.batchUpdate(stringSQL.toString(), new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                T beanInsertar = listaInsert.get(i);
                setPreparedStatementInsert(ps, beanInsertar);

            }

            @Override
            public int getBatchSize() {
                return listaInsert.size();
            }
        });
    }

    public void updateJdbc(final List<T> listaUpdate) {
        StringBuilder stringSQL = this.devuelveUpdateStringBuilderSQL();
        jdbcMaster.batchUpdate(stringSQL.toString(), new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                T beanInsertar = listaUpdate.get(i);
                setPreparedStatementUpdate(ps, beanInsertar);

            }

            @Override
            public int getBatchSize() {
                return listaUpdate.size();
            }
        });
    }

    protected abstract void setPreparedStatementInsert(PreparedStatement ps, T bean);
    protected abstract void setPreparedStatementUpdate(PreparedStatement ps, T bean);
    protected abstract StringBuilder devuelveInsertStringBuilderSQL();
    protected abstract StringBuilder devuelveUpdateStringBuilderSQL();
}
