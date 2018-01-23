import com.work.chatapp.Client;
import com.work.chatapp.Server;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;


public class ServerTest {

    @Test
    public void serverTest() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(s);
        //Server server = new Server(5000);


    }

    @Test
    public void clientTest() {
        Client client = new Client("127.0.0.1", 5000);
    }
}
