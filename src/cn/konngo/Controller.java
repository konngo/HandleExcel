package cn.konngo;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        FileChooser chooser=new FileChooser();
        chooser.setTitle("选择您要修改格式的excel文件");
        Stage selectFile = new Stage();
        chooser.setInitialDirectory(new File(System.getProperty("user.home")+"/Desktop/"));
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLS", "*.xls"),
                new FileChooser.ExtensionFilter("XLSX", "*.xlsx")
        );

        File file = chooser.showOpenDialog(selectFile);
        if (file != null) {
            fileName=file.getName();
            manageConsole("选择文件成功");
            fileField.setText(file.getAbsolutePath());

            // 导入excel信息
            try {
                attences=ExcelUtil.importExcel(new HSSFWorkbook(new FileInputStream(file)));

                manageConsole("读取Exel文件成功");
            }catch (Exception e){
                manageConsole("读取Exel文件失败");
            }
        }
    }

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
