package com.ai.baas.op.web.util;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ai.baas.amc.api.oweinfoquery.param.ChargeDetailInfo;
import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfo;
import com.ai.baas.op.web.model.arrearage.OweDetailShowInfo;
import com.ai.baas.op.web.model.arrearage.OweGDetailShowInfo;
import com.ai.opt.sdk.components.excel.util.ExcelDateUtil;
import com.ai.opt.sdk.components.excel.util.ExcelStringUtil;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public final class ExcelUtil {
    private ExcelUtil() {}

    /**
     * 对象序列化版本号名称
     */
    public static final String UID = "serialVersionUID";
    
    private static final Logger LOG = Logger.getLogger(ExcelUtil.class);
    
    /**
     * 导出欠费数据到指定excel中
     * @param os 文件输出流
     * @param sheetName excel sheet页的名称，为空时，默认取当前时间戳yyyyMMddHHmmssSS
     * @param oweDetailShowInfo 数据对象
     * @throws Exception
     */
    public static void exportExcel(OutputStream os, String sheetName, OweDetailShowInfo oweDetailShowInfo) throws Exception {
        WritableWorkbook workbook = null;
        try {
            // 检测文件是否存在，如果存在则修改文件，否则创建文件
            workbook = Workbook.createWorkbook(os);
            // 根据当前工作表数量创建相应编号的工作表
            int sheetNo = workbook.getNumberOfSheets() + 1;
            if(ExcelStringUtil.isBlank(sheetName)){
                sheetName = ExcelDateUtil.format(new Date(), "yyyyMMddHHmmssSS");
            }
            WritableSheet sheet = workbook.createSheet(sheetName, sheetNo);
            //标题样式
            WritableCellFormat headFormat = buildHeadCellFormat();
            //正文样式
            WritableCellFormat bodyFormat = buildBodyCellFormat();
            //正文数字样式
            WritableCellFormat bodyDigitalFormat = buildBodyDigitalFormat();
            //添加正文
            List<OweGDetailShowInfo> oweDetailShowInfoList = oweDetailShowInfo.getOweDetailShowInfoList();
            int listCount = 0;//明细行数
            for(OweGDetailShowInfo info : oweDetailShowInfoList){
                listCount = listCount + info.getCount();
            }
            Label label = new Label(0, 0, "客户名称", headFormat);
            sheet.addCell(label);
            sheet.mergeCells(1, 0, 4, 0);
            label = new Label(1, 0, oweDetailShowInfo.getCustName(), bodyFormat);
            sheet.addCell(label);
            label = new Label(0, 1, "客户等级", headFormat);
            sheet.addCell(label);
            sheet.mergeCells(1, 1, 4, 1);
            label = new Label(1, 1, oweDetailShowInfo.getCustGrade(), bodyFormat);
            sheet.addCell(label);
            label = new Label(0, 2, "欠费开始时间", headFormat);
            sheet.addCell(label);
            sheet.mergeCells(1, 2, 4, 2);
            label = new Label(1, 2, oweDetailShowInfo.getUnsettledMonth(), bodyFormat);
            sheet.addCell(label);
            sheet.mergeCells(0, 3, 0, listCount+3);
            label = new Label(0, 3, "欠费明细", headFormat);
            sheet.addCell(label);
            sheet.setColumnView(1, "欠费时间".length() + 15);
            label = new Label(1, 3, "欠费时间", headFormat);
            sheet.addCell(label);
            sheet.setColumnView(2, "服务号码".length() + 15);
            label = new Label(2, 3, "服务号码", headFormat);
            sheet.addCell(label);
            sheet.setColumnView(3, "账单科目名称".length() + 15);
            label = new Label(3, 3, "账单科目名称", headFormat);
            sheet.addCell(label);
            sheet.setColumnView(4, "欠费金额（元）".length() + 15);
            label = new Label(4, 3, "欠费金额（元）", headFormat);
            sheet.addCell(label);
            int begin = 0,end = 0,count = 0;
            for(int i=0; i<oweDetailShowInfoList.size(); i++){
                OweGDetailShowInfo info = oweDetailShowInfoList.get(i);
                count = info.getCount();
                begin = end;
                end = end+count-1;
                sheet.mergeCells(1, begin+4+i, 1, end+4+i);//欠费时间_合并单元格
                label = new Label(1, begin+4+i, info.getDate(), bodyFormat);//欠费时间_设值
                sheet.addCell(label);
                
                List<OweDetailInfo> detailInfoList = info.getOweDetailInfoList();
                int begin_ = 0,end_ = 0,size = 0;
                for(int j=0; j<detailInfoList.size(); j++){
                    OweDetailInfo detailInfo = detailInfoList.get(j);
                    size = detailInfo.getChargeDetailInfos().size();
                    begin_ = end_;
                    end_ = end_+size-1;
                    sheet.mergeCells(2, begin_+j+(begin+4+i), 2, end_+j+(begin+4+i));//服务号码_合并单元格
                    label = new Label(2, begin_+j+(begin+4+i), detailInfo.getServiceNum(), bodyDigitalFormat);//服务号码_设值
                    sheet.addCell(label);
                    List<ChargeDetailInfo> chargeDetailInfoList = detailInfo.getChargeDetailInfos();
                    for(int k=0; k<chargeDetailInfoList.size(); k++){
                        ChargeDetailInfo chargeDetailInfo = chargeDetailInfoList.get(k);
                        label = new Label(3, k+(begin_+j+(begin+4+i)), chargeDetailInfo.getSubjectName(), bodyFormat);//账单科目名称_设值
                        sheet.addCell(label);
                        label = new Label(4, k+(begin_+j+(begin+4+i)), AmountUtil.Li2Yuan(chargeDetailInfo.getBalance()), bodyFormat);//欠费金额_设值
                        sheet.addCell(label);
                    }
                }
            }
            sheet.setColumnView(0, "合计欠费金额".length() + 15);
            label = new Label(0, listCount+4, "合计欠费金额", headFormat);
            sheet.addCell(label);
            sheet.mergeCells(1, listCount+4, 4, listCount+4);
            label = new Label(1, listCount+4, AmountUtil.Li2Yuan(oweDetailShowInfo.getBalance())+"元", bodyFormat);
            sheet.addCell(label);
        } finally {
            if (workbook != null) {
                workbook.write();
                workbook.close();
                if(os!=null){
                    os.flush();
                    os.close();
                }
            }
        }
    }
    
    private static WritableCellFormat buildHeadCellFormat() throws WriteException {
        //标题字体
        WritableFont headwfont =  new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);  
        //标题样式
        WritableCellFormat headFormat = new WritableCellFormat(headwfont);
        headFormat.setAlignment(Alignment.CENTRE);
        headFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        headFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        headFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.DARK_BLUE);
        headFormat.setBackground(Colour.ICE_BLUE);
        return headFormat;
    }
    private static WritableCellFormat buildBodyCellFormat() throws WriteException {
        //正文字体
        WritableFont bodywfont =  new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);  
        //正文样式
        WritableCellFormat bodyFormat = new WritableCellFormat(bodywfont);
        bodyFormat.setAlignment(Alignment.CENTRE);
        bodyFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.DARK_BLUE);
        bodyFormat.setBackground(Colour.WHITE);
        return bodyFormat;
    }
    private static WritableCellFormat buildBodyDigitalFormat() throws WriteException {
        //正文数字字体
        WritableFont bodyDigitalwfont =  new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);  
        //正文数字样式
        WritableCellFormat bodyDigitalFormat = new WritableCellFormat(bodyDigitalwfont);
        bodyDigitalFormat.setAlignment(Alignment.RIGHT);
        bodyDigitalFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        bodyDigitalFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.DARK_BLUE);
        bodyDigitalFormat.setBackground(Colour.WHITE);
        return bodyDigitalFormat;
    }
    
//    /**
//     * 判断属性是否为数字类型（int,long,float,double等）
//     */
//    protected static <T> boolean isDigitalType(Class<T> clazz, String fieldName) {
//        boolean flag = false;
//        try {
//            Field field = clazz.getDeclaredField(fieldName);
//            String fieldType=field.getType().getName();
//            flag = "short".equals(fieldType)||"int".equals(fieldType) 
//                    || "long".equals(fieldType)||"float".equals(fieldType)
//                    ||"double".equals(fieldType);
//            Object typeObj = field.getType().newInstance();
//            flag = typeObj instanceof Integer || typeObj instanceof Long 
//                    || typeObj instanceof Float || typeObj instanceof Double 
//                    || typeObj instanceof BigDecimal || typeObj instanceof BigInteger;
//        } catch (Exception e) {
//            // 把异常吞掉直接返回false
//        }
//        return flag;
//    }
//    
//    /**
//     * 判断属性是否为日期类型
//     * @param clazz 数据类型
//     * @param fieldName 属性名
//     * @return 如果为日期类型返回true，否则返回false
//     */
//    protected static <T> boolean isDateType(Class<T> clazz, String fieldName) {
//        boolean flag = false;
//        try {
//            Field field = clazz.getDeclaredField(fieldName);
//            Object typeObj = field.getType().newInstance();
//            flag = typeObj instanceof Date;
//        } catch (Exception e) {
//            // 把异常吞掉直接返回false
//        	LOG.error("失败：", e);
//        }
//        return flag;
//    }
//    
//    protected static <T> boolean isTimestampType(Class<T> clazz, String fieldName) {
//        boolean flag = false;
//        try {
//            Field field = clazz.getDeclaredField(fieldName);
//            String fieldType=field.getType().getName();
//            //Object typeObj = field.getType().newInstance();
//            flag = "java.sql.Timestamp".equals(fieldType);
//        } catch (Exception e) {
//            // 把异常吞掉直接返回false
//        	LOG.error("失败：", e);
//        }
//        return flag;
//    }
    
    /*
    //从一个JSON得到一个java对象
    private static OweDetailShowInfo getOweDetailShowInfo(){
        
        String jsonString = "{\"balance\":82000,\"unsettledMonth\":\"2016年03月\",\"oweDetailShowInfoList\":[{\"date\":\"2016年03月\",\"oweDetailInfoList\":[{\"accDate\":\"2016年03月\",\"serviceNum\":\"186\",\"chargeDetailInfos\":[{\"balance\":10000000,\"subjectId\":2004003,\"subjectName\":\"2004003\"},{\"balance\":10000000,\"subjectId\":2004004,\"subjectName\":\"2004004\"},{\"balance\":10000000,\"subjectId\":2004003,\"subjectName\":\"2004003\"},{\"balance\":5000000,\"subjectId\":2004004,\"subjectName\":\"2004004\"},{\"balance\":10000000,\"subjectId\":2004004,\"subjectName\":\"2004004\"}],\"acctId\":\"21\"},{\"accDate\":\"2016年03月\",\"serviceNum\":\"185\",\"chargeDetailInfos\":[{\"balance\":5000000,\"subjectId\":2004004,\"subjectName\":\"2004004\"}],\"acctId\":\"33\"}],\"count\":6},{\"date\":\"2016年04月\",\"oweDetailInfoList\":[{\"accDate\":\"2016年04月\",\"serviceNum\":\"186\",\"chargeDetailInfos\":[{\"balance\":10000000,\"subjectId\":2004003,\"subjectName\":\"2004003\"},{\"balance\":5000000,\"subjectId\":2004004,\"subjectName\":\"2004004\"},{\"balance\":10000000,\"subjectId\":2004004,\"subjectName\":\"2004004\"},{\"balance\":5000000,\"subjectId\":2004004,\"subjectName\":\"2004004\"},{\"balance\":10000000,\"subjectId\":2004004,\"subjectName\":\"2004004\"},{\"balance\":10000000,\"subjectId\":2004003,\"subjectName\":\"2004003\"},{\"balance\":5000000,\"subjectId\":2004004,\"subjectName\":\"2004004\"},{\"balance\":10000000,\"subjectId\":2004004,\"subjectName\":\"2004004\"}],\"acctId\":\"21\"}],\"count\":8},{\"date\":\"2016年05月\",\"oweDetailInfoList\":[{\"accDate\":\"2016年05月\",\"serviceNum\":\"146\",\"chargeDetailInfos\":[{\"balance\":10000000,\"subjectId\":2004003,\"subjectName\":\"2004003\"},{\"balance\":5000000,\"subjectId\":2004004,\"subjectName\":\"2004004\"}],\"acctId\":\"21\"},{\"accDate\":\"2016年05月\",\"serviceNum\":\"136\",\"chargeDetailInfos\":[{\"balance\":10000000,\"subjectId\":2004004,\"subjectName\":\"2004004\"}],\"acctId\":\"33\"}],\"count\":3}],\"tenantId\":\"ACDA2FED6DBE40C598AD0612C40702C5\",\"custGrade\":\"V1\",\"custName\":\"Coco\"}";
        JSONObject object = JSONObject.fromObject(jsonString);
        OweDetailShowInfo bean = (OweDetailShowInfo) JSONObject.toBean(object, OweDetailShowInfo.class);
        
        List<OweGDetailShowInfo> infoList = new ArrayList<OweGDetailShowInfo>();  
        JSONArray oweDetailShowInfoArray = JSONArray.fromObject(bean.getOweDetailShowInfoList());
        for(int i = 0; i<oweDetailShowInfoArray.size(); i++){  
            JSONObject jsonObject = oweDetailShowInfoArray.getJSONObject(i);  
            OweGDetailShowInfo oweGDetailShowInfo = (OweGDetailShowInfo) JSONObject.toBean(jsonObject, OweGDetailShowInfo.class);
            
            List<OweDetailInfo> detailInfoList = new ArrayList<OweDetailInfo>();
            JSONArray detailInfoArray = JSONArray.fromObject(oweGDetailShowInfo.getOweDetailInfoList());
            for(int j = 0; j<detailInfoArray.size(); j++){  
                JSONObject jsonObject2 = detailInfoArray.getJSONObject(j);
                OweDetailInfo oweDetailInfo = (OweDetailInfo) JSONObject.toBean(jsonObject2, OweDetailInfo.class);
                
                List<ChargeDetailInfo> chargeDetailInfoList = new ArrayList<ChargeDetailInfo>();
                JSONArray chargeDetailInfoArray = JSONArray.fromObject(oweDetailInfo.getChargeDetailInfos());
                for(int k = 0; k<chargeDetailInfoArray.size(); k++){  
                    JSONObject jsonObject3 = chargeDetailInfoArray.getJSONObject(k);
                    ChargeDetailInfo  chargeDetailInfo = (ChargeDetailInfo) JSONObject.toBean(jsonObject3, ChargeDetailInfo.class);
                    chargeDetailInfoList.add(chargeDetailInfo);
                }
                oweDetailInfo.setChargeDetailInfos(chargeDetailInfoList);
                
                detailInfoList.add(oweDetailInfo);
            }
            oweGDetailShowInfo.setOweDetailInfoList(detailInfoList);
            
            infoList.add(oweGDetailShowInfo);
        }
        bean.setOweDetailShowInfoList(infoList);
        
        return bean;  
    } 
    
    public static void main(String[] args) throws Exception{
        OweDetailShowInfo oweDetailShowInfo = getOweDetailShowInfo();//模拟数据
        File file = new File("/Users/liangbs/Downloads/JXL2003"+DateUtil.getDateString(new Date(), DateUtil.yyyyMMddHHmmssSSS)+".xls");
        ExcelUtil.exportExcel(file, "欠费明细", oweDetailShowInfo);
    }*/
    
}
