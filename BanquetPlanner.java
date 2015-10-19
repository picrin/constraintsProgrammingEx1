/**
 * We are given n guests to be allocated to m equally sized tables (where n % m = 0) at a banquest.  
 * There are constraint of the form together(i,j) and apart(i,j) where
 * together(i,j) means that guests i and j must sit at the same table and
 * apart(i,j) means that guests i and j must sit at different tables. 
 * By default, guests can sit at any table with any other guest
 */

import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Scanner;
import org.chocosolver.solver.*;
import org.chocosolver.solver.variables.*;
import org.chocosolver.solver.constraints.*;
import java.util.HashMap;
import java.util.ArrayList;

public class BanquetPlanner {
    Solver solver;
    int nGuests;
    int mTables;
    int tableSize;
    public static OutputStream p = System.out;
    IntVar tableSizeIV;
    IntVar[] guests;
 

    //
    // constrained variables declared here
    //

    BanquetPlanner(String fname) throws IOException {
        try (Scanner sc = new Scanner(new File(fname))) {
            nGuests   = sc.nextInt(); // number of guests
            mTables   = sc.nextInt(); // number of tables
            tableSize = nGuests / mTables;
            
            solver    = new Solver("banquet planner");
            tableSizeIV = VF.enumerated("tableSize", new int[]{tableSize}, solver);
            
            guests = VariableFactory.enumeratedArray("guests", nGuests, 0, mTables - 1, solver);
            for (int i = 0; i < mTables; i++) {
                solver.post(ICF.count(i, guests, tableSizeIV));
            }
            while (sc.hasNext()) {
                String s = sc.next();
                int i    = sc.nextInt();
                int j    = sc.nextInt();
                if(s.equals("together")){
                    solver.post(ICF.arithm(guests[i], "=", guests[j]));
                } else if (s.equals("apart")) {
                    solver.post(ICF.arithm(guests[i], "!=", guests[j]));
                }
            }
        }

	//
	// post constraints to ensure that every table is of size tableSize
	//
    }

    boolean solve() {
        return solver.findSolution();
    }

    void result() {
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        for (int i = 0; i < mTables; i++){
            hm.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < nGuests; i++){
            hm.get(guests[i].getValue()).add(i);
        }
        for(int i = 0; i < mTables; i++){
           System.out.print(i + " ");
           ArrayList<Integer> guestsAtTable = hm.get(i);
           for(int j = 0; j < guestsAtTable.size() - 1; j++){
              System.out.print(guestsAtTable.get(j) + " ");
           }
           System.out.print(guestsAtTable.get(guestsAtTable.size() - 1) + "\n");
           
        }

        //for (IntVar guest: guests){
        //    System.out.println(guestNo++ + " " + guest.getValue());
        //}
        //
	// print out solution in specified format (see readme.txt)
	// so that results can be verified
	//
            //stats();
    }

    void stats() {
        System.out.println("nodes: " + solver.getMeasures().getNodeCount() + "   cpu: " + solver.getMeasures().getTimeCount());
    }

    public static void main(String[] args) throws IOException {
        BanquetPlanner bp = new BanquetPlanner(args[0]);
        if (bp.solve())
            bp.result();
        else
            System.out.println(false);
        bp.stats();
    }
}

