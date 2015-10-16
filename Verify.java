import java.util.*;
import java.io.*;

public class Verify {

    public static void main(String[] args) throws IOException {
	Scanner sc     = new Scanner(new File(args[0]));
	int n          = sc.nextInt(); // number of people
	int m          = sc.nextInt(); // number of tables 
	int [][] A     = new int[n][n]; // A[i][j] = 1 <-> together(i,j); A[i][j] = -1 <-> apart(i,j)
	int [][] alloc = new int[m][n]; // alloc[i][j] = 1 <-> guest i is in table j
	int [] guest  = new int[n];    // guest[i] = k <-> guest[i] i table k
	int size       = n/m; // size of a table
	while (sc.hasNext()){
	    String s = sc.next();
	    int i    = sc.nextInt();
	    int j    = sc.nextInt();
	    if (s.equals("together")) A[i][j] = A[j][i] = 1;
	    if (s.equals("apart")) A[i][j] = A[j][i] = -1;
	}
	sc.close();
	sc = new Scanner(new File(args[1]));
	for (int i=0;i<m;i++){ // read a table
	    int k = sc.nextInt(); // table number is k
	    for (int j=0;j<size;j++){
		int p = sc.nextInt();
		alloc[k][p] = 1;
		guest[p] = k;
	    }
	}
	sc.close();
	System.out.println("guests: "+ n +"  tables: "+ m +"  table size: "+ size);
	for (int i=0;i<n;i++)
	    for (int j=0;j<n;j++){
		if (guest[i] != guest[j] && A[i][j] == 1) System.out.println("guest["+ i +"] and guest["+ j +"] should be together");
		if (guest[i] == guest[j] && A[i][j] == -1) System.out.println("guest["+ i +"] and guest["+ j +"] should be apart");
	    }
	for (int i=0;i<m;i++){
	    int count = 0;
	    for (int j=0;j<n;j++) count = count + alloc[i][j];
	    if (count != size) System.out.println("table["+ i +"] is wrong size "+ count);
	}
	for (int j=0;j<n;j++){
	    int count = 0;
	    for (int i=0;i<m;i++) count = count + alloc[i][j];
	    if (count != 1) System.out.println("guest["+ j +"] must be in exactly 1 table");
	}
    }
}