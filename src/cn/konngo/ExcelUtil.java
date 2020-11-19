package cn.konngo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
    //导出excel
    public static void outputExcel( List<Attence> attences,String fileName) throws IOException {
        //创建工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建工作表对象
        HSSFSheet sheet = workbook.createSheet();
        //创建工作表的行
        HSSFRow row = sheet.createRow(0);//设置第一行，从零开始
        row.createCell(0).setCellValue("员工工号");
        row.createCell(1).setCellValue("考勤时间");
        row.createCell(2).setCellValue("下班/上班");
        int count=1;
        // 遍历考勤信息
        for (Attence a:attences) {
//            boolean flag=true;
            // 没有考勤信息跳过
            if (a.getTimes()==null){
                continue;
            }


            int demo=1;
            // 遍历考勤属性
            for (int i = 0; i < a.getTimes().length ; i++) {
                if (a.getTimes()[i].equals("")) continue;
                // 插入到excel表格中
                row = sheet.createRow(count);
                if (!a.getAno().contains("K")){
                    a.setAno("K"+a.getAno());
                }
                row.createCell(0).setCellValue(a.getAno());
                row.createCell(1).setCellValue(a.getDates().trim()+" "+a.getTimes()[i]);
                row.createCell(2).setCellValue(demo++);
//                if (i==a.getTimes().length-1){
//                    if (flag){
//                        row.createCell(2).setCellValue("1");
//                    }else {
//                        row.createCell(2).setCellValue("2");
//                    }
//                }else {
//                    flag=false;
//                    row.createCell(2).setCellValue("1");
//                }
                count++;
            }
        }
        workbook.setSheetName(0,"AttLog");//设置sheet的Name

        // 修改文件名 输出为xls文件
        String name=fileName.substring(0,fileName.lastIndexOf("."));

        //文档输出
        FileOutputStream out = new FileOutputStream(System.getProperty("user.home")+"/Desktop/transform-"+name+".xls");
        workbook.write(out);
        out.close();

    }


    // 导入excel
    public static List<Attence> importExcel(Workbook wb){

        Attence materiel;

        List<Attence> BOM = new ArrayList<>();
        // 如果是xls文件
        if (wb instanceof HSSFWorkbook) {
            HSSFWorkbook xs= (HSSFWorkbook) wb;
            for (int s = 0; s < xs.getNumberOfSheets(); s++) {
                HSSFSheet sheet = xs.getSheetAt(s);
                int lastRowNum = sheet.getLastRowNum();
                // 跳过表头
                for (int i = 1; i < lastRowNum; i++) {
                    HSSFRow row = sheet.getRow(i);
                    materiel = new Attence();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        HSSFCell cell = row.getCell(j);
                        switch (cell.getColumnIndex()) {
//                            case 0:
//                                break;
                            case 0:
                                materiel.setAno(cell.toString());
                                break;
                            case 1:
                                materiel.setName(cell.toString());
                                break;
                            case 2:
                                materiel.setDep(cell.toString());
                                break;
                            case 3:
                                materiel.setDates(cell.toString());
                                break;
                            case 4:
                                // 时间
                                materiel.setTimes(cell.toString().split(" "));
                                break;
                            default:
                                // TODO 数据格式有误
                                break;
                        }
                    }
                    BOM.add(materiel);
                }
            }
            //如果是xlsx文件
        } else if (wb instanceof XSSFWorkbook) {
            XSSFWorkbook xs = (XSSFWorkbook) wb;
            for (int s = 0; s < xs.getNumberOfSheets(); s++) {
                XSSFSheet sheet = xs.getSheetAt(s);
                int lastRowNum = sheet.getLastRowNum();
                // 跳过表头
                for (int i = 1; i < lastRowNum; i++) {
                    XSSFRow row = sheet.getRow(i);
                    materiel = new Attence();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        XSSFCell cell = row.getCell(j);
                        switch (cell.getColumnIndex()) {
//                            case 0:
//                                break;
                            case 0:
                                materiel.setAno(cell.toString());
                                break;
                            case 1:
                                materiel.setName(cell.toString());
                                break;
                            case 2:
                                materiel.setDep(cell.toString());
                                break;
                            case 3:
                                materiel.setDates(cell.toString());
                                break;
                            case 4:
                                // 时间
                                materiel.setTimes(cell.toString().split(" "));
                                break;
                            default:
                                // TODO 数据格式有误
                                break;
                        }
                    }

                    BOM.add(materiel);
                }
            }
        }
        return BOM;
    }

    /**
     * 设置单元格样式
     */
    public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook){
        HSSFCellStyle style = workbook.createCellStyle();
        //设置上下左右四个边框宽度
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }
}