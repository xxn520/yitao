/**
 * 
 */
package com.yitao.core.hibernate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * @author iDay
 *
 */
public class HibernatePhysicalNamingStrategy implements PhysicalNamingStrategy {

	/* (non-Javadoc)
	 * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalCatalogName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
	 */
	@Override
	public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return convert(name);
	}

	/* (non-Javadoc)
	 * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalSchemaName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
	 */
	@Override
	public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return convert(name);
	}

	/* (non-Javadoc)
	 * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalTableName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
	 */
	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return convert(name);
	}

	/* (non-Javadoc)
	 * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalSequenceName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
	 */
	@Override
	public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return convert(name);
	}

	/* (non-Javadoc)
	 * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalColumnName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
	 */
	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return convert(name);
	}
	
	private Identifier convert(Identifier identifier) {
        if (identifier == null || StringUtils.isEmpty(identifier.getText())) {
            return identifier;
        }

        String regex = "([a-z])([A-Z])";
        String replacement = "$1_$2";
        String newName = identifier.getText().replaceAll(regex, replacement).toLowerCase();
        return Identifier.toIdentifier(newName);
    }

}
