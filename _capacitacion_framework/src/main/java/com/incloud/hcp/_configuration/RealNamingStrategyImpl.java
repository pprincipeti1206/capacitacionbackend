package com.incloud.hcp._configuration;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.io.Serializable;

public class RealNamingStrategyImpl extends org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy implements Serializable {

    //public static final PhysicalNamingStrategyImpl INSTANCE = new PhysicalNamingStrategyImpl();

	private static final long serialVersionUID = -4427046485179633840L;

	@Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return new Identifier(name.getText(), name.isQuoted());
    }


}