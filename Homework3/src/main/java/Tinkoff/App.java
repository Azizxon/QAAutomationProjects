package Tinkoff;

import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;

class App {

    public static void main(String[] args) throws IOException, DocumentException {
        IDocument document;
        Service service;

        document = Service.makeDocument("excel");
        service = new Service(document);
        service.Run();

        document = Service.makeDocument("pdf");
        service = new Service(document);
        service.Run();
    }
}

