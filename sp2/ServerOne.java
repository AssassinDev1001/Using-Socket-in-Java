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
           //data receive
           int[][] r0 = new int[4][3];
           int[][] r1 = {{0,2,1},{1,100,0},{2,0,1},{3,200,200}};

           printValues(r1, 1);

            System.out.println("\nGetting data from router 0...\n");
           for(int i = 0; i < r0.length; i++){
              r0[i][0] = inFromClient.read();
              r0[i][1] = inFromClient.read();
              r0[i][2] = inFromClient.read();
            }

           // print the vals
           printValues(r0, 0);

           System.out.print("\n");

           //100 means local 200 means unknown




          System.out.print("Updating the values of router 0...\n");
           int[][] uV = new int[4][3];
           for (int i = 0; i <uV.length; i++){
        	   for (int j = 0; j <uV[i].length; j++){
	        	   if(r0[i][0] == r1[i][0]){
	        		   if(r1[i][1]!=100 && r1[i][1]!=200){
	        			   if(r0[i][2] < r0[i][2]+r1[i][2]){
	        				   uV[i][j] = r1[i][j];
	        				   uV[i][2] = r0[1][2]+r1[i][2];
	        			   }
	        		   }else if(r1[i][1]==100 || r1[i][1]==200) {
	        			   uV[i][j] = r0[i][j];
	        		   }
	        		   if (i == 0){
	        			   uV[i][j] = r0[i][j];
	        		   }
	        	   }
	           }
           }
           System.out.print("Sending values of router 0...\n");
           for (int i = 0; i<uV.length;i++){
               outToClient.writeByte(uV[i][0]);
               outToClient.writeByte(uV[i][1]);
               outToClient.writeByte(uV[i][2]);
           }
          System.out.print("Values sent. \n\n");
           System.out.print("Values sent. \n");
           //print here
           System.out.print("Updated router 0...\n\n");
           printValues(uV, 0);
           System.out.println("\nDone!...\n");
      	}
    }
}
