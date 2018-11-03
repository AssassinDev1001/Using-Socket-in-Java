import java.io.*;
import java.net.*;

/**
 * @author Assassin
 * @since 2017.4.21
 */
class ClientZero extends Router{
    public static void main(String[] args) throws Exception
    {
      System.out.println("Connecting to address:" + ADDRESS + "  port: " + PORT);
        System.out.print("\n\nRouter 0 running\n");
        Socket clientSocket = new Socket(ADDRESS, 54321);

        DataOutputStream outToServer =
          new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer =
                new BufferedReader(new
                InputStreamReader(clientSocket.getInputStream()));
        //100 means local 99 means unknown
        int[][] r0= {{0, 100,0},{1,0,1},{2,1,3},{3,2,7}};

        for(int i = 0; i<r0.length; i++){
          outToServer.writeByte(r0[i][0]);
          outToServer.writeByte(r0[i][1]);
          outToServer.writeByte(r0[i][2]);
      	}

		System.out.print("Receiving data from server... \n");

        int[][] uV = new int[4][3];
        for(int i = 0; i < uV.length; i++){
          uV[i][0] = inFromServer.read();
          uV[i][1] = inFromServer.read();
          uV[i][2] = inFromServer.read();
	       }

         print99(uV, 1);

		clientSocket.close();
		System.out.println("Finished.\n");

    }
}
