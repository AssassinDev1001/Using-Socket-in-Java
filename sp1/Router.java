/**
 * @author Assassin
 * @since 2017.4.21
 */
class Router {
  //function used to take all the values and print to screen.
  public static final String ADDRESS = "128.235.29.101";
  public static final Integer PORT = 54321;

  public static void printValues(int[][] r0, int routerNum){
    System.out.println("---------------------------------------");
    System.out.println("                Router " + routerNum + "              ");
    System.out.println("---------------------------------------");
    System.out.println("     R      :      I      :      L     ");
    System.out.println("---------------------------------------");
    for(int i = 0; i < r0.length; i++){
      System.out.print("     " + r0[i][0] + "      |");
      if(r0[i][1] == 100) {
        System.out.print("    Local    |");
      } else if(r0[i][1] == 200) {
        System.out.print("     N/A     |");
      } else {
        System.out.print("      " + r0[i][1] + "      |");
      }
      if(r0[i][2] == 200) {
        System.out.print("     N/A     \n");
      } else {
        System.out.print("      " + r0[i][2] + "    \n");
      }
  }
  System.out.println("---------------------------------------\n");

  }
  public static void print99(int[][] r0, int routerNum){
    System.out.println("---------------------------------------");
    System.out.println("                Router " + routerNum + "              ");
    System.out.println("---------------------------------------");
    System.out.println("     R      :      I      :      L     ");
    System.out.println("---------------------------------------");
    for(int i = 0; i < r0.length; i++){
      System.out.print("     " + r0[i][0] + "      |");
      if(r0[i][1] == 100) {
        System.out.print("    Local    |");
      } else if(r0[i][1] == 99) {
        System.out.print("     N/A     |");
      } else {
        System.out.print("      " + r0[i][1] + "      |");
      }
      if(r0[i][2] == 99) {
        System.out.print("     N/A     \n");
      } else {
        System.out.print("      " + r0[i][2] + "    \n");
      }
  }
  System.out.println("---------------------------------------\n");

  }
}
