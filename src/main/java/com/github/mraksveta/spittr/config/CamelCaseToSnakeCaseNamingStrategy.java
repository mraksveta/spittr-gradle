package com.github.mraksveta.spittr.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CamelCaseToSnakeCaseNamingStrategy extends PhysicalNamingStrategyStandardImpl {
    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return camelCaseToSnakeCase(super.toPhysicalCatalogName(name, jdbcEnvironment));
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return camelCaseToSnakeCase(super.toPhysicalCatalogName(name, jdbcEnvironment));
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return camelCaseToSnakeCase(super.toPhysicalCatalogName(name, jdbcEnvironment));
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return camelCaseToSnakeCase(super.toPhysicalCatalogName(name, jdbcEnvironment));
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return camelCaseToSnakeCase(super.toPhysicalCatalogName(name, jdbcEnvironment));
    }

    private Identifier camelCaseToSnakeCase(Identifier identifier) {
        if (identifier != null) {
            String text = identifier.getText();
            text = camelCaseToSnakeCase(text);
            return Identifier.toIdentifier(text, identifier.isQuoted());
        }
        return null;
    }

    private String camelCaseToSnakeCase(String text) {
        StringBuilder sb = new StringBuilder();
        char[] textChars = text.toCharArray();
        if (textChars.length > 0) {
            sb.append(textChars[0]);
        }
        for (int i = 1; i < textChars.length; i++) {
            char textChar = textChars[i];
            if (Character.isUpperCase(textChar)) {
                sb.append("_");
            }
            sb.append(textChar);
        }
        return sb.toString();
    }
}
