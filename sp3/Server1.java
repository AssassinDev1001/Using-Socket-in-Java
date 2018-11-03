import java.io.*;
import java.net.*;

/**
 * @author Assassin
 * @since 2017.4.21
 */
class Server1 extends Router {
	public static void main(String[] args) throws Exception {
		int[][] router0Values = new int[4][3];
		int[][] router2Values = new int[4][3];
		int[][] router3Values = new int[4][3];
		int[][] router1Values = {{0,2,1},{1,100,0},{2,0,1},{3,99,99}};
		int[][] updatedValues = new int[4][3];

		System.out.println("Server is Running");
		ServerSocket welcomeSocket = new ServerSocket(PORT);

		while(true) {

			Socket connectionSocket = welcomeSocket.accept();
			Socket connectionSocket2 = welcomeSocket.accept();
			Socket connectionSocket3 = welcomeSocket.accept();

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			BufferedReader inFromClient2 = new BufferedReader(new InputStreamReader(connectionSocket2.getInputStream()));
			DataOutputStream  outToClient2 = new DataOutputStream(connectionSocket2.getOutputStream());

			BufferedReader inFromClient3 = new BufferedReader(new InputStreamReader(connectionSocket3.getInputStream()));
			DataOutputStream  outToClient3 = new DataOutputStream(connectionSocket3.getOutputStream());

			System.out.println("Receiving Router 0 Value:");

			for(int i = 0; i < 4; i++){
				for (int j = 0; j< 3; j++){
					router0Values[i][j] = inFromClient.read();
				}
			}
			printValues(router0Values, 0);

			System.out.println();
			System.out.println("Router 1 intial values");

			printValues(router1Values, 1);

			System.out.println();
			System.out.println("Receiving router 2 values");
			for(int i = 0; i < 4; i++){
				for (int j = 0; j< 3; j++){
					router2Values[i][j] = inFromClient2.read();
				}
			}
			printValues(router2Values, 2);

			System.out.println();
			System.out.println("Receiving router 3 values");
			for(int i = 0; i < 4; i++){
				for (int j = 0; j< 3; j++){
					router3Values[i][j] = inFromClient3.read();
				}
			}
			printValues(router3Values, 3);

      System.out.println();
			for (int i = 0; i <4; i++){
				for (int j = 0; j <3; j++){
					updatedValues[i][j]=router0Values[i][j];
					if((router0Values[i][0] == router1Values[i][0])&&(router1Values[i][1]!=100)&&(i!=0)
							&&(router0Values[i][2]>router1Values[i][2]+router0Values[1][2]  || updatedValues[i][2]==99)){
						updatedValues[i][j] = router1Values[i][j];
						updatedValues[i][2]= router0Values[1][2]+router1Values[i][2];
					}
				}
			}
			System.out.println("------------- Update 1: -------------");
			printValues(updatedValues, 0);

			System.out.println();
			outToClient.writeByte(updatedValues.length);
			outToClient.writeByte(updatedValues[0].length);

			outToClient2.writeByte(updatedValues.length);
			outToClient2.writeByte(updatedValues[0].length);

			outToClient3.writeByte(updatedValues.length);
			outToClient3.writeByte(updatedValues[0].length);
			for (int i = 0; i<4;i++){
				for (int j = 0; j < 3; j++){
					outToClient.writeByte(updatedValues[i][j]);
					outToClient2.writeByte(updatedValues[i][j]);
					outToClient3.writeByte(updatedValues[i][j]);
				}
			}

			//Compare values with Router 2
			for (int i = 0; i <4; i++){
				for (int j = 0; j <3; j++){
					if((updatedValues[i][0] == router2Values[i][0])&&(router2Values[i][1]!=100)&&(i!=0)
							&&(updatedValues[i][2]>router2Values[i][2]+router0Values[2][2]  || updatedValues[i][2]==99)){
						updatedValues[i][j] = router2Values[i][j];
						updatedValues[i][2]= router0Values[2][2]+router2Values[i][2];
					}
						outToClient.writeByte(updatedValues[i][j]);
						outToClient2.writeByte(updatedValues[i][j]);
						outToClient3.writeByte(updatedValues[i][j]);
				}
			}
			System.out.println("------------- Update 2: -------------");
			printValues(updatedValues, 0);

			//Compare values with Router 3
			for (int i = 0; i <updatedValues.length; i++){
				for (int j = 0; j <updatedValues[i].length; j++){
					if((updatedValues[i][0] == router2Values[i][0])&&(router2Values[i][1]!=100)&&(i!=0)
							&&(updatedValues[i][2]>router2Values[3][2]+router1Values[2][2]+router0Values[1][2] || updatedValues[i][2]==99)){
						updatedValues[i][j] = router2Values[i][j];
						updatedValues[i][2]= router0Values[1][2]+router1Values[2][2]+router2Values[3][2];
					}
					outToClient.writeByte(updatedValues[i][j]);
					outToClient2.writeByte(updatedValues[i][j]);
					outToClient3.writeByte(updatedValues[i][j]);
				}
			}

			System.out.println("------------- Update 3: -------------");
			printValues(updatedValues, 0);

			connectionSocket.close();
			connectionSocket2.close();
			connectionSocket3.close();
			System.out.println("Done.");
			break;
		}
		welcomeSocket.close();
	}
}
