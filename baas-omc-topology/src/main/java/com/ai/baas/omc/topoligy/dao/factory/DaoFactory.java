package com.ai.baas.omc.topoligy.dao.factory;

import com.ai.baas.omc.topoligy.dao.interfaces.IBlUserinfoDAO;

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
