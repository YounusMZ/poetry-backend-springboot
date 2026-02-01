/*package com.example.poetrybackend.config;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.VARCHAR, "text");
        registerColumnType(Types.BLOB, "blob");
        registerColumnType(Types.BOOLEAN, "integer");
        registerColumnType(Types.TIMESTAMP, "datetime");
    }

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    @Override
    public IdentityColumnSupportImpl getIdentityColumnSupport() {
        return new IdentityColumnSupportImpl();
    }

    @Override
    public String getIdentitySelectString(String table, String column, int type) {
        return "select last_insert_rowid()";
    }

    @Override
    public String getLimitString(String query, boolean hasOffset) {
        return query + (hasOffset ? " limit ? offset ?" : " limit ?");
    }

    @Override
    public boolean supportsCurrentTimestampSelection() {
        return true;
    }

    @Override
    public String getCurrentTimestampSelectString() {
        return "select current_timestamp";
    }

    @Override
    public boolean hasAlterTable() {
        return false;
    }
}*/