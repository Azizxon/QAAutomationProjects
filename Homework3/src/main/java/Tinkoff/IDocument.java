package Tinkoff;

public interface IDocument {
    boolean create();
    boolean write(User[] users);
    boolean close();
}
