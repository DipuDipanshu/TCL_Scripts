import java.net.*;
import java.io.*;

public class clientserver{
    public static void main(String args[]) throws Exception{
        Socket sock=new Socket("10.2.20.74",4000);
        System.out.println("Enter the file name");
        BufferedReader keyread=new BufferedReader(new InputStreamReader(System.in));
        String fname=keyread.readLine();
        OutputStream ostream=sock.getOutputStream();
        PrintWriter pwrite=new PrintWriter(ostream,true);
        pwrite.println(fname);
        InputStream istream=sock.getInputStream();
        BufferedReader socketread=new BufferedReader(new InputStreamReader(istream));
        String str;
        while((str=socketread.readLine())!=null)
        {
            System.out.println(str);
        }
        pwrite.close();
        socketread.close();
        keyread.close();
    }
}
