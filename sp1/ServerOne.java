import java.io.*;
import java.net.*;

/**
 * @author Assassin
 * @since 2017.4.21
 */
class ServerOne extends Router {

  public static void main(String[] args) throws Exception
    {

      System.out.print("Server Running\n");
      ServerSocket welcomeSocket = new ServerSocket(PORT);

      while(true) {
    	  Socket cS = welcomeSocket.accept();
    	  System.out.println("Router connected...\n");
    	  BufferedReader inFromClient =
    		new BufferedReader(new
           InputStreamReader(cS.getInputStream()));
           DataOutputStream  outToClient =
           new DataOutputStream(cS.getOutputStream());

           //100 means local 99 means unknown
           int[][] r1 = {{0,2,1},{1,100,0},{2,0,1},{3,99,99}};
           // print the vals
           print99(r1, 1);

           //data receive
           int[][] r0 = new int[4][3];

           System.out.println("Receiving Data...\n");
           for(int i = 0; i < r0.length; i++){
              r0[i][0] = inFromClient.read();
              r0[i][1] = inFromClient.read();
              r0[i][2] = inFromClient.read();
            }

           // print the vals
           printValues(r0, 0);

           System.out.print("\n");

		  //Update the values
           System.out.print("\n");
           System.out.print("Sending values of router 1...\n");
           for (int i = 0; i<4;i++){
        		   outToClient.writeByte(r1[i][0]);
               outToClient.writeByte(r1[i][1]);
               outToClient.writeByte(r1[i][2]);
           }
          System.out.print("Values sent. \n");
          System.out.println("\nDone!...\n");
      	}
    }
}
