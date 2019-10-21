package MyChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

    class Server {
    private final int PORT = 8189;
    private ConnectBase connectBase;  // ссылка на объект ConnectBase
    private List<ClientHandler> clients; //clients- ссылка на список(ArrayList) будущих клиентов
    private static final Logger logger = Logger.getLogger(MyMain.class);

    // объект ConnectBase позволит получить соединение с бд, содержит "методы-запросы"
    ConnectBase getConnectBase() {
        return connectBase;
    } // возвращает ссылку на объект, у которого есть соединение с бд

    Server() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            connectBase = new ConnectBase(); // получили соединение с базой
            clients = new ArrayList<>(); //создается список clients, для хранения объектов типа ClientHandler
// заходим в цикл ожидания клиентов
            logger.info(" Выполнено подключение к БД");
            while (true) {
                logger.info(" Сервер ожидает подключения");
                Socket socket = server.accept(); // подключенного клиента отдаем ClientHandler-у
                logger.info(" Клиент [ " + socket.getInetAddress() + " ] подключился");
                new ClientHandler(this, socket); //создали держателя ClientHandler
            }
        } catch (IOException e) {
            //System.out.println("Ошибка в работе сервера");
            logger.error(e.getMessage(), e);
        } finally {
            if (connectBase != null) { //здесь надо закрыть базу
                connectBase.close();
            }
        }
    }

    //проверяем, есть ли среди подключившихся клиентов этот ник
    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    //отправка сообщения всем клиентам подряд
    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
            //logger.info(" Клиенту: " + o.getName() + " - " +  msg);
        }
    }

    //отправка сообщения (msg) конкретному клиенту с именем forClient от from
    public synchronized void sendOnly(String msg, String forClient, String from) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(forClient)) {
                o.sendMsg("От " + from + ": " + msg);
                logger.info(" Клиент: " + o.getName() + " получил персональное сообщение от: " + from);
            }
        }
    }

    public synchronized void unsubscribe(ClientHandler o) { //удаление клиента
        clients.remove(o);
    }

    public synchronized void subscribe(ClientHandler o) { //добавление клиента
        clients.add(o);
    }
}

