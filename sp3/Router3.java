import java.io.*;
import java.net.*;

/**
 * @author Assassin
 * @since 2017.4.21
 */
class Router3 extends Router{

    public static void main(String[] args) throws Exception
    {
        System.out.println("Connecting to address:" + ADDRESS + "  port: " + PORT);
        Socket clientSocket = new Socket(ADDRESS, PORT);
        int[][] router3Value = {{0,0,7},{1,99,99},{2,2,2},{3,100,0}};
        DataOutputStream outToServer =
          new DataOutputStream(clientSocket.getOutputStream());
          printValues(router3Value, 3);

        BufferedReader inFromServer =
                new BufferedReader(new
                InputStreamReader(clientSocket.getInputStream()));

        System.out.println("Sending values to server...");
        	for(int i = 0; i<4; i++){
        		for(int j = 0; j<3;j++){
        			outToServer.writeByte(router3Value[i][j]);
        		}
        	}

        	System.out.println("Receiving updated values...");

		//Receive and print updated value
        int[][] uV = new int[4][3];
        for(int count =0; count<3;count++){
            for(int i = 0; i < 4; i++){
              uV[i][1] = inFromServer.read();
              uV[i][2] = inFromServer.read();
              uV[i][0] = inFromServer.read();
    	           }

    	       }
printValues(uV, 1);
		clientSocket.close(); //Close connection

		System.out.println("Done.");

    }
}
