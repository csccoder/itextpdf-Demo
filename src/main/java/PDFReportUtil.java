import Config.ModuleConfig;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class PDFReportUtil {
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

    public static PdfPTable createTable(List dataList, ModuleConfig moduleConfig) throws DocumentException {
        PdfPTable pdfPTable = new PdfPTable(moduleConfig.getFieldConfigList().size() + 1);
        pdfPTable.setSpacingBefore(10f); // Space before table
        pdfPTable.setSpacingAfter(10f);
        pdfPTable.setWidthPercentage(100);
        //setFieldWidths(new float[]{8,20,10,10,10,10,10,10,10,10,10});
        //setFieldWidths(new float[]{5,20,10,10,10,10,10});
        if(moduleConfig.getFieldWidths()!=null){
            pdfPTable.setWidths(moduleConfig.getFieldWidths());
        }
        pdfPTable.setTotalWidth(520);
        pdfPTable.setLockedWidth(true);
        //设置模块标题
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(moduleConfig.getModuleName(),headfont));
        cell.setRowspan(2);
        cell.setColspan(1);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPTable.addCell(cell);

        //设置表头
        for(ModuleConfig.FieldConfig fieldConfig : moduleConfig.getFieldConfigList()){
            cell = new PdfPHeaderCell();
            cell.setPhrase(new Phrase(fieldConfig.getExportName(),headfont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(cell);
        }

        if(dataList!=null&&dataList.size()>0){

            for(Object entity : dataList){
                for(ModuleConfig.FieldConfig fieldConfig : moduleConfig.getFieldConfigList()){
                    Object fieldValue = null;
                    try {
                        Field field = moduleConfig.getClazz().getDeclaredField(fieldConfig.getFieldName());
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
                    pdfPTable.addCell(cell);
                }
            }
        }else{
            cell = new PdfPCell(new Phrase("无数据",textfont));
            cell.setColspan(moduleConfig.getFieldConfigList().size()+1);
            pdfPTable.addCell(cell);
        }

        return pdfPTable;
    }

    public static void doReport(Map<String,List> dataMap, Map<String,ModuleConfig> moduleConfigMap){
        try {
            Document document = new Document();
            document.setPageSize(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\chenny\\Desktop\\javapdfHelloworldA.pdf"));
            document.open();
            for(Map.Entry<String, ModuleConfig> entry : moduleConfigMap.entrySet()){
                String key = entry.getKey();
                ModuleConfig moduleConfig = entry.getValue();
                List dataList = dataMap.get(key);
                document.add(createTable(dataList,moduleConfig));
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
