/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.marks;

/**
 *
 * @author john
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer extends Thread {

    final static int TCP_SERVER_PORT = 9701;
    private Socket socket;
    Object[] datas = null;
    ArrayList< Object[]> allobj = new ArrayList<Object[]>();

    public TCPServer(Socket sock) {
        // TODO Auto-generated constructor stub
        socket = sock;
        datas = new Object[]{"Reg Number", "Marks"};
        allobj.add(datas);

    }

    public void run() {

        String path = "/Users/john/Documents/E-Marks/";
        String file_name = "students_marks.xlsx";
        String sheet_name = "marks";

        System.out.println(this.socket.getPort() + " working or sleeping for 5 seconds");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        DataInputStream clientinp;
        DataOutputStream clientout;

        try {
            clientinp = new DataInputStream(socket.getInputStream());
            clientout = new DataOutputStream(socket.getOutputStream());
            System.out.println("here");

            StringBuffer inputLine = new StringBuffer();
            while (true) {

                //if () {
                try {
                    String tmp;
                    while ((tmp = clientinp.readUTF()) != null) {
                        inputLine.append(tmp);
                        System.out.println(tmp);
                        String[] splits = tmp.split("\\|");
                        for (int i = 0; i < splits.length; i++) {
                            String marks = splits[i];
                            if (marks.contains(":")) {
                                datas = new Object[]{marks.split(":")[0], Double.parseDouble(marks.split(":")[1])};
                                allobj.add(datas);
                            }
                        }
                        Create_Excel.createExcel2(allobj, path + file_name, sheet_name);
                    }
                    /**
                     * String[] splits = clientinp.readUTF().split(":"); datas =
                     * new Object[]{splits[0], splits[1]}; allobj.add(datas);
                     * Create_Excel.createExcel2(allobj, path+file_name,
                     * sheet_name); *
                     */
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //  }

                System.out.println("here now");
                String sentence = clientinp.readUTF();
                System.out.println("not here now");
                System.out.println(sentence);

                clientout.writeBytes(sentence);

            }

        } catch (IOException e) {

            System.out.println(e.getStackTrace());
        } finally {

            try {
                this.socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws IOException {
        ServerSocket serversocket;
        serversocket = new ServerSocket(TCP_SERVER_PORT);

        while (true) {
            Socket clientsocket = serversocket.accept();
            new TCPServer(clientsocket).start();

        }
    }
}
