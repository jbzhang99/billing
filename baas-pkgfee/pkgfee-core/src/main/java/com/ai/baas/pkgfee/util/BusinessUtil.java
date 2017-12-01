package com.ai.baas.pkgfee.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.RequestHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 业务校验工具类<br>
 * Date: 2015年8月12日 <br>
 * Copyright (c) 2015 asiainfo.com <br>
 * 
 * @author mayt
 */
public final class BusinessUtil {

    private BusinessUtil() {
    }

    private static final Logger LOGGER = LogManager.getLogger(BusinessUtil.class);

    /**
     * 将属性名转换成表的列名 userId -> user_id
     * 
     * @param name
     * @return
     * @author mayt
     * @ApiDocMethod
     */
    static String switchParam(String name) {

        if (name.matches("[a-z]+[A-Z][a-z]+([A-Z][a-z]+)*")) {

            Pattern pattern = Pattern.compile("[A-Z]");

            Matcher matcher = pattern.matcher(name);

            while (matcher.find()) {

                String old = matcher.group();
                String ne = matcher.group().toLowerCase();

                name = name.replaceAll(old, "_" + ne);

            }

        }
        return name;
    }

    public static Map<String, String> assebleDshmData(Object bo) {
        Map<String, String> map = new HashMap<String, String>();
        getFieldsMap(map, bo.getClass(), bo);

        Map<String, String> maps = new HashMap<String, String>();
        for (Entry<String, String> s : map.entrySet()) {
            maps.put(switchParam(s.getKey()), s.getValue());
        }
        LOGGER.info(maps);
        return maps;
    }

    private static void getFieldsMap(Map<String, String> map, Class<?> clazz, Object bo) {
        if (map == null) {
            map = new HashMap<>();
        }
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName();
                Object value;
                try {
                    value = field.get(bo);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new SystemException(e);
                }
                Type type = field.getGenericType();
                String valueStr;
                if (null == value) {
                    valueStr = "";
                } else if ("class java.sql.Timestamp".equals(type.toString())) {
                    valueStr = DateUtil.getDateString((Timestamp)value, DateUtil.DATETIME_FORMAT);
                } else {
                    valueStr = String.valueOf(value);
                }
                map.put(key, valueStr);
            }
            if (null != clazz.getSuperclass()) {
                getFieldsMap(map, clazz.getSuperclass(), bo);
            }
        }
    }

}
