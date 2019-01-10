import config.BaseModuleConfig;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dataSource.Datasource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 生成表格，并导出为pdf的工具类
 */
public class GenerateTableReportPdfHepler {
    private static Font headfont ;// 设置字体大小
    private static Font keyfont;// 设置字体大小
    private static Font textfont;// 设置字体大小


    static{
        BaseFont bfChinese;
        try {
            bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            headfont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
            keyfont = new Font(bfChinese, 8, Font.BOLD);// 设置字体大小
            textfont = new Font(bfChinese, 8, Font.NORMAL);// 设置字体大小
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getMaxColumnsForModuleConfig(BaseModuleConfig baseModuleConfig){
        List<BaseModuleConfig.FieldConfig>[] fieldConfigListArr = baseModuleConfig.getFieldConfigList();
        List<Integer> columnList = new ArrayList<Integer>(fieldConfigListArr.length);
        for(List<BaseModuleConfig.FieldConfig> fieldConfigList : fieldConfigListArr){
            columnList.add(fieldConfigList.size());
        }
        Collections.sort(columnList);
        return  columnList.get(columnList.size()-1)+1;
    }

    public static PdfPTable createTable(List dataList, BaseModuleConfig baseModuleConfig) throws DocumentException {
        //获取最大列数
        int maxColumns = getMaxColumnsForModuleConfig(baseModuleConfig);
        PdfPTable pdfPTable = new PdfPTable(maxColumns);
        pdfPTable.setSpacingBefore(10f); // Space before table
        pdfPTable.setSpacingAfter(10f);
        pdfPTable.setWidthPercentage(100);

        if(baseModuleConfig.getFieldWidths()!=null){
            pdfPTable.setWidths(baseModuleConfig.getFieldWidths());
        }
        pdfPTable.setTotalWidth(550);
        pdfPTable.setLockedWidth(true);
        //设置模块标题
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(baseModuleConfig.getModuleName(),headfont));
        if(dataList!= null && dataList.size()>0){
            cell.setRowspan((1+dataList.size())* baseModuleConfig.getFieldConfigList().length);
        }else{
            cell.setRowspan(baseModuleConfig.getFieldConfigList().length*2);
        }
        cell.setColspan(1);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPTable.addCell(cell);


        for(List<BaseModuleConfig.FieldConfig> fieldConfigList : baseModuleConfig.getFieldConfigList()){
            //设置表头
            for(BaseModuleConfig.FieldConfig fieldConfig : fieldConfigList){
                cell = new PdfPHeaderCell();
                cell.setPhrase(new Phrase(fieldConfig.getExportName(),headfont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                if(fieldConfig.getColspan()!=null){
                    cell.setColspan(fieldConfig.getColspan());
                }
                cell.setBackgroundColor(new BaseColor(235,235,235));
                pdfPTable.addCell(cell);
            }

            if(dataList!=null&&dataList.size()>0){

                for(Object entity : dataList){
                    for(BaseModuleConfig.FieldConfig fieldConfig : fieldConfigList){
                        Object fieldValue = null;
                        try {
                            Field field = baseModuleConfig.getClazz().getDeclaredField(fieldConfig.getFieldName());
                            field.setAccessible(true);
                            fieldValue = field.get(entity);
                            if(fieldConfig.getConverter()!=null){
                                fieldValue = fieldConfig.getConverter().doConverter(fieldValue);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        cell = new PdfPCell();
                        cell.setPhrase(new Phrase(String.valueOf(fieldValue),textfont));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        if(fieldConfig.getColspan()!=null){
                            cell.setColspan(fieldConfig.getColspan());
                        }
                        pdfPTable.addCell(cell);
                    }
                }
            }else{
                cell = new PdfPCell(new Phrase("无数据",textfont));
                cell.setColspan(maxColumns+1);
                pdfPTable.addCell(cell);
            }
        }

        return pdfPTable;
    }

    public static void doReport(Map<String,List> dataMap, Map<String, BaseModuleConfig> moduleConfigMap){
        try {
            Document document = new Document();
            document.setPageSize(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\chenny\\Desktop\\helloworld.pdf"));
            document.open();
            for(Map.Entry<String, BaseModuleConfig> entry : moduleConfigMap.entrySet()){
                String key = entry.getKey();
                BaseModuleConfig baseModuleConfig = entry.getValue();
                List dataList = dataMap.get(key);
                document.add(createTable(dataList, baseModuleConfig));
            }
            document.close();
            writer.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        doReport(Datasource.getDataMap(),Datasource.getModuleConfigMap());
    }
}
