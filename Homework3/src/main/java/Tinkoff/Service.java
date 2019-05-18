package Tinkoff;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

class Service {

    private final IDocument document;
    private IUserRepository userRepository;

    public Service(IDocument document) throws IOException {
        this.document = document;
        this.userRepository = new UserRepository();
    }

    public boolean Run() {

        User[] users = userRepository.getAllUsers();

        boolean isUsersInformationWrite = false;
        boolean isDocumentClosed = false;

        boolean isDocumentCreated = document.create();

        if (isDocumentCreated)
            isUsersInformationWrite = document.write(users);
        if (isUsersInformationWrite)
            isDocumentClosed = document.close();

        return isDocumentClosed;
    }
    public static IDocument makeDocument(String  type) throws IOException, DocumentException {

        IDocument document;
        switch (type) {
            case "excel":
            case "EXCEL":
                document = new Excel();
                break;
            case "pdf":
            case "PDF":
            default:
                document = new PDF();
                break;
        }
        return document;
    }

}
