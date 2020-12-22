
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class clientTCP {
    public static void main(String[] args) throws Exception {
        String dataFromClient;
        String replyFromServer;

        while(true){
            Socket socket = new Socket("4.tcp.ngrok.io", 10225);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("YOU:   ");
            dataFromClient = input.readLine();

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            output.writeBytes(dataFromClient+'\n');

            replyFromServer = inFromServer.readLine();
            System.out.println("REPLY: " + replyFromServer);
            socket.close();
            if(replyFromServer.equals("exit")){
                System.out.println("EXIT ... !");
                break;
            }
        }
    }
}
