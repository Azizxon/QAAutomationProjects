package Tinkoff;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

@SuppressWarnings("JavaDoc")
public class PDF implements IDocument {

    public PDF() throws IOException, DocumentException {
        this.path = String.format("%4d",RandomExtension.getRandomNumber(10000))+".pdf";
        document = new Document(PageSize.A4.rotate());
        BaseFont russianBaseFont = BaseFont.createFont("src/main/resources//russian.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        font = new Font(russianBaseFont, 14.0f, Font.NORMAL, BaseColor.BLACK);
    }
    /**
     * метод который создает документ и создает в нем заголовки
     * @return при успешной работы метода возвращает true, иначе false
     */
    public boolean create() {

        try {
             PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            Paragraph title = new Paragraph("Персональные данные", font);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            String[] headers = new String[]{"Имя", "Фамилия", "Отчетство", "Возраст", "Пол", "Дата рождения", "Место рождения", "Место проживания"};
            table = new PdfPTable(headers.length);
            table.setWidthPercentage(100);
            table.setSpacingBefore(20);
            table.setSpacingAfter(20);
            font.setSize(12.0f);

            for (String header:headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header,font));
                table.addCell(cell);
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
            for (User user:users){
                String[] data = user.toStringArray();
                for (String aData : data) {
                    PdfPCell cell = new PdfPCell(new Phrase(aData, font));
                    table.addCell(cell);
                }
            }
            document.add(table);
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
            document.close();
            log.fine("Файл создан.Путь: "+path);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.warning("Файл не оздан. Ошибка.");
            return false;
        }
    }
    private static final Logger log = Logger.getLogger(PDF.class.getName());

    private final String path;
    private Font font;
    private PdfPTable table;
    private final Document document;
}
