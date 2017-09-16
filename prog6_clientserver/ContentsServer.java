import java.net.*;
import java.io.*;

public class ContentsServer{
    public static void main(String []args)throws IOException{
        ServerSocket sersock = new ServerSocket(4000);
        System.out.println("Server ready for communication");

        Socket sock = sersock.accept();

        System.out.println("Connection is successful and waiting for chatting");

        InputStream istream = sock.getInputStream();

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(istream));

        String fname = fileReader.readLine();

        BufferedReader contentReader = new BufferedReader(new FileReader(fname));

        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream,true);
        String str;

        while((str = contentReader.readLine()) != null)
            pwrite.println(str);

        pwrite.close();
        sock.close();
        sersock.close();
        contentReader.close();
        fileReader.close();
    }
}

