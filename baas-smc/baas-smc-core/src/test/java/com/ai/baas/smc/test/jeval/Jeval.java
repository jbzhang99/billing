//package com.ai.baas.smc.test.jeval;
//
//import net.sourceforge.jeval.ArgumentTokenizer;
//import net.sourceforge.jeval.EvaluationException;
//import net.sourceforge.jeval.Evaluator;
//import net.sourceforge.jeval.function.FunctionException;
//import net.sourceforge.jeval.function.math.Abs;
//import net.sourceforge.jeval.operator.GreaterThanOperator;
//import net.sourceforge.jeval.operator.GreaterThanOrEqualOperator;
//import net.sourceforge.jeval.operator.LessThanOperator;
//import net.sourceforge.jeval.operator.LessThanOrEqualOperator;
//
//public class Jeval {
//
//    public static boolean greater(String leftOperand,String rightOperand){
//        GreaterThanOperator greaterThanOperator =new GreaterThanOperator();
//        return greaterThanOperator.evaluate(leftOperand, rightOperand).equals("1.0")?true:false;
//    }
//    
//    public static boolean greaterThanOrEqualOperator(String leftOperand,String rightOperand){
//        GreaterThanOrEqualOperator greaterThanOrEqualOperator =new GreaterThanOrEqualOperator();
//        return greaterThanOrEqualOperator.evaluate(leftOperand, rightOperand).equals("1.0")?true:false;
//    }
//    public static boolean lessThanOperator(String leftOperand,String rightOperand){
//        LessThanOperator lessThanOperator =new LessThanOperator();
//        return lessThanOperator.evaluate(leftOperand, rightOperand).equals("1.0")?true:false;
//    }
//    
//    public static boolean lessThanOrEqualOperator(String leftOperand,String rightOperand){
//        LessThanOrEqualOperator lessThanOrEqualOperator =new LessThanOrEqualOperator();
//        return lessThanOrEqualOperator.evaluate(leftOperand, rightOperand).equals("1.0")?true:false;
//    }
//    
//    public static boolean equal(String leftOperand,String rightOperand){
//        return leftOperand.equals(rightOperand)?true:false;
//    }
//    
//    public static void main(String[] args) throws FunctionException, EvaluationException {
//        String b ="1,3,6,7";
//        
//       System.out.println(greater("3","2"));
//       System.out.println(greaterThanOrEqualOperator("1","2"));
//       System.out.println(lessThanOperator("2","2"));
//       System.out.println(lessThanOrEqualOperator("2","2"));
//        
//        String a ="1,3,6,7";
//        ArgumentTokenizer argumentTokenizer = new ArgumentTokenizer(a, ',');
//        
//        while(argumentTokenizer.nextElement()!=null){
//            if((int)argumentTokenizer.nextElement()==2);
//        }
////        System.out.println(argumentTokenizer.nextElement());
////        System.out.println(argumentTokenizer.hasMoreElements());
////        System.out.println(argumentTokenizer.hasMoreTokens());
////        System.out.println(argumentTokenizer.nextToken());
////        Abs abs=new Abs();
//        Evaluator evaluator=new Evaluator();
//        System.out.println(evaluator.evaluate("4 /(3 + 1) + (3 + 1) + 1"));
////        System.out.println(abs.execute(evaluator, "-20").getResult());
//    }
//
//}
