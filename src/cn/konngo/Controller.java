package cn.konngo;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.*;
import java.util.List;

/**
 *  控制器
 *  cn.konngo.cn
 */
public class Controller {
    public TextField fileField;
    public TextArea console;
    public StringBuilder builder=new StringBuilder("");
    private List<Attence> attences=null;
    private  String fileName;


    @FXML

    /**
     * 点击选择文件
     */
    public void chooseFile() throws IOException {
        // 文件选择器选择excel文件
        FileChooser chooser=new FileChooser();
        chooser.setTitle("选择您要修改格式的excel文件");
        Stage selectFile = new Stage();
        // 文件选择器初始地址
        chooser.setInitialDirectory(new File(System.getProperty("user.home")+"/Desktop/"));
        // 过滤其他文件
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLSX", "*.xlsx"),
                new FileChooser.ExtensionFilter("XLS", "*.xls")
        );

        // 获取文件
        File file = chooser.showOpenDialog(selectFile);
        if (file != null) {
            fileName=file.getName();
            manageConsole("选择文件成功");
            fileField.setText(file.getAbsolutePath());

            FileInputStream fi=new FileInputStream(file);

            // 导入excel文件
            Workbook wb=null;

            // 判断文件是xlsx还是xls格式
            try {
                wb=new XSSFWorkbook(fi);
                manageConsole("读取到xlsx文件");
            }catch (Exception e){
                wb=new HSSFWorkbook(fi);
                manageConsole("正在尝试读取xls文件");
            }

            // 导入excel信息
            try {
                attences=ExcelUtil.importExcel(wb);
                manageConsole("读取Exel文件成功");
            }catch (Exception e){
                e.printStackTrace();
                manageConsole("读取Exel文件失败");
            }
        }
    }




    // 窗口界面输出
    public  void manageConsole(String str){
        builder.append(str+"\r\n");
        console.setText(builder.toString());
    }

    /**
     *  输出文件
     */
    public void OutputFile(MouseEvent mouseEvent) {

        manageConsole("正在导出文件");
        try {
            ExcelUtil.outputExcel(attences,fileName);
            manageConsole("文件导出成功");
        } catch (IOException e) {
            manageConsole("导出excel文件失败");
            e.printStackTrace();
        }

    }
}
