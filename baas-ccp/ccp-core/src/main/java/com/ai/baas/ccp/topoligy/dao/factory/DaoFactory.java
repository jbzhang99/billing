package com.ai.baas.ccp.topoligy.dao.factory;

import com.ai.baas.ccp.topoligy.dao.interfaces.IBlUserinfoDAO;

public final class DaoFactory {

    private DaoFactory() {
    }

    static Object getService(Class<?> clazz, String tableName) {
        return DaoInstanceFactory.getInstance(clazz, tableName);
    }

    public static IBlUserinfoDAO getIBlUserinfoDAO() {
        return (IBlUserinfoDAO) getService(IBlUserinfoDAO.class, "tablename");
    }
}
