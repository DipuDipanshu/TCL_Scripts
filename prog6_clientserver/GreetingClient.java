import java.net.*;
import java.io.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class GreetingClient
{
    Image newimg;
    static BufferedImage bimg;
    byte[] bytes;

    public static void main(String [] args)
    {
        String serverName = "10.2.20.74 ";
        int port = 6066;
        try
        {
            System.out.println("Connecting to " + serverName
                    + " on port " + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Just connected to "
                    + client.getRemoteSocketAddress());

            DataInputStream in=new DataInputStream(client.getInputStream());
            System.out.println(in.readUTF());
            System.out.println(in.readUTF());

            DataOutputStream out =
                new DataOutputStream(client.getOutputStream());

            out.writeUTF("Hello from "
                    + client.getLocalSocketAddress());
            out.writeUTF("client: hello to server");

                ImageIcon img1=new ImageIcon("index1.jpeg");
            Image img = img1.getImage();
            Image newimg = img.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newimg);

            bimg = ImageIO.read(new File("/home/dipudipanshu/clientserver/index.jpeg"));

            ImageIO.write(bimg,"jpeg",client.getOutputStream());
            System.out.println("Image sent!!!!");
            client.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
