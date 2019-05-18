package Tinkoff;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class Excel implements IDocument {


    public Excel() {
        this.path = String.format("%d",RandomExtension.getRandomNumber(10000))+".xls";
        rowNumber = 0;
    }
    /**
     * метод который создает документ и создает в нем заголовки
     * @return при успешной работы метода возвращает true, иначе false
     */
    public boolean create() {
        try {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Персональные данные");
            Row row = sheet.createRow(rowNumber);
            Cell[] cells = new Cell[8];
            String[] headers = new String[]
                    {"Имя", "Фамилия", "Отчетство", "Пол", "Возраст", "Дата рождения", "Место рождения", "Место проживания"};
            for (int i = 0; i <cells.length; i++) {
                cells[i] = row.createCell(i);
                cells[i].setCellValue(headers[i]);
                sheet.autoSizeColumn(i);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     *  метод для запини информации о пользовательях
     * @param users - массив пользователей
     * @return  при успешной записи данных возвращает true, иначе false
     */
    public boolean write(User[] users) {

        try {
            for (User user:users)
            {
                Row row = sheet.createRow(++rowNumber);
                String[] data = user.toStringArray();
                for (int j = 0; j < data.length; j++) {
                    Cell cell = row.createCell(j );
                        cell.setCellValue(data[j]);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    /**
     * Метод который закрывает документ и сохраняет его в указанном пользователем директории
     * после в лог записываем об результате
     * @return  при успешном выполнении возвращает true, иначе false
     */
    public boolean close() {
        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            workbook.close();
            log.fine("Файл создан.Путь: "+path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            log.warning("Файл не оздан. Ошибка.");
            return false;
        }
    }
    private static final Logger log = Logger.getLogger(Excel.class.getName());

    private int rowNumber;
    private XSSFSheet sheet;
    private XSSFWorkbook workbook;
    private final String path;
}
