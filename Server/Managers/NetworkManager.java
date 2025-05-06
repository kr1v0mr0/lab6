package lab5.Server.Managers;


import lab5.Common.Commands.Container;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class NetworkManager {
    DatagramChannel dc;
    int port;
    SocketAddress addr;
    int timeout;
    byte[] bytes = new byte[5096];


    public NetworkManager(int port, int timeout) {
        this.port = port;
    }
    public static final Logger logger = LogManager.getLogger(NetworkManager.class);
    public boolean init() {
        try {
            addr = new InetSocketAddress(port);
            dc = DatagramChannel.open();
            dc.bind(addr);
            dc.configureBlocking(false);
            return true;
        } catch (SocketException e) {
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sendData(byte data[]) {
        try {
            ByteBuffer buf = ByteBuffer.wrap(data);
            dc.send(buf, addr);
            return true;
        } catch (IOException e) {
            return false;

        }

    }

    public byte[] receiveData(int len)  {
        try {
            ByteBuffer buf = ByteBuffer.allocate(len);
            addr = dc.receive(buf);
            if (addr != null) {
                logger.info("Получен запрос от клиента!");
                return buf.array();}
            return null;
        } catch (IOException e) {
            logger.error("Не удалось получить данные.",e);
            return null;
        }
    }
    public static byte[] serializer(Object obj)  {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.close();
            byte[] objBytes = bos.toByteArray();
            logger.info("Ответ успешно сериализован!");
            return objBytes;

        }
        catch (IOException e) {
            System.out.println(e.getStackTrace());
            return null;}
    }
    public static Container deserialize(byte[] bytes) {
        if (bytes == null) return null;
        InputStream is = new ByteArrayInputStream(bytes);
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            logger.info("Команда успешно десериализована!");
            return (Container) ois.readObject();
        } catch (IOException e) {
            logger.error("Не удалось десереализовать объект");
            return null;
        } catch (ClassNotFoundException e) {
            logger.error("Не удалось десереализовать объект");
            return null;
        }
    }

}
