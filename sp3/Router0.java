import java.io.*;
import java.net.*;

/**
 * @author Assassin
 * @since 2017.4.21
 */
class Router0 extends Router {

    public static void main(String[] args) throws Exception
    {
        System.out.println("Connecting to address:" + ADDRESS + "  port: " + PORT);
        Socket clientSocket = new Socket(ADDRESS, PORT); //Changed the Host Name and Port Number

        int[][] router0Value = {{0,100,0},{1,0,1},{2,1,3},{3,2,7}};
        DataOutputStream outToServer =
          new DataOutputStream(clientSocket.getOutputStream());

        printValues(router0Value, 0);

        BufferedReader inFromServer =
                new BufferedReader(new
                InputStreamReader(clientSocket.getInputStream()));

        System.out.println("Sending values to server...");
        	for(int i = 0; i<router0Value.length; i++){
        		for(int j = 0; j<router0Value[i].length;j++){
        			outToServer.writeByte(router0Value[i][j]);
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
