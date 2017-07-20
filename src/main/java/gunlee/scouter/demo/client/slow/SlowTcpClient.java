package gunlee.scouter.demo.client.slow;

import java.io.*;
import java.net.Socket;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 20.
 */
public class SlowTcpClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 8080);
        try (OutputStreamWriter os = new OutputStreamWriter(socket.getOutputStream());
             PrintWriter pw = new PrintWriter(os);
             InputStreamReader is = new InputStreamReader(socket.getInputStream());
             BufferedReader br = new BufferedReader(is)
        ) {

            pw.write("GET /systemInfo HTTP/1.1\n");
            pw.write("Host: 127.0.0.1\n");
            pw.write("\n");
            pw.flush();

            String line;
            while ((line = br.readLine()) != null) {
                //Thread.sleep(10);
                System.out.println(line);
            }

//            char[] buffer = new char[1];
//            while (is.read(buffer) != -1) {
//                //Thread.sleep(10);
//                System.out.print(new String(buffer));
//            }
            System.out.println("read finished");
        }
        System.out.println("socket finished");

    }
}
