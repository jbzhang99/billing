package com.ai.baas.cust.util;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ai.opt.base.exception.SystemException;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public final class ExpressionUtil {
    
    private static final Logger LOGGER = LogManager.getLogger(ExpressionUtil.class);
    private ExpressionUtil() {}
    
    private static final Evaluator evaluator = new Evaluator();
    
    public static String getExpressionResult(String expression, Map<String, String> variables) {
        evaluator.setVariables(variables);
            try {
                return evaluator.evaluate(expression);
            } catch (EvaluationException e) {
                LOGGER.error("表达式计算错误", e);
                throw new SystemException("表达式计算错误", e);
            }
    }
}
