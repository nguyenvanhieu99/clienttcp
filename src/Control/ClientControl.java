package Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.User;
import model.serverSendObject;
import model.userRegister;
import model.userSendObject;

public class ClientControl {
     private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket mySocket;
    private String serverHost = "localhost";
    private int serverPort = 1002;

    public ClientControl() throws IOException {
        //openConnection();
      this.mySocket = new Socket(serverHost, serverPort);
        System.out.println("1");
        
             
                     System.out.println("2");

            
                     System.out.println("3");

        
    }

    public Socket openConnection() {
        try {
             mySocket = new Socket(serverHost, serverPort);
             ois= new ObjectInputStream(mySocket.getInputStream());
             oos= new ObjectOutputStream(mySocket.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return mySocket;
    }

    public boolean sendData(Object uso) {
        try {
            ObjectOutputStream oos= new ObjectOutputStream(mySocket.getOutputStream());
            userSendObject name1 = (userSendObject) uso;
            oos.writeObject(name1);
            System.out.println("da gui");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
     public boolean sendData(userRegister userRegister) {
        try {
            ObjectOutputStream oos= new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(userRegister);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public serverSendObject receiveData() {
        String result = null;
        try {
            
            ObjectInputStream ois= new ObjectInputStream(mySocket.getInputStream());
            serverSendObject o;
            o = (serverSendObject) ois.readObject();
            
                return o;
                
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean closeConnection() {
        try {
            mySocket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    
}

}

