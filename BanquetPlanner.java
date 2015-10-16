/**
 * We are given n guests to be allocated to m equally sized tables (where n % m = 0) at a banquest.  
 * There are constraint of the form together(i,j) and apart(i,j) where
 * together(i,j) means that guests i and j must sit at the same table and
 * apart(i,j) means that guests i and j must sit at different tables. 
 * By default, guests can sit at any table with any other guest
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.chocosolver.solver.*;
import org.chocosolver.solver.variables.*;
import org.chocosolver.solver.constraints.*;

public class BanquetPlanner {
    Solver solver;
    int nGuests;
    int mTables;
    int tableSize;

    //
    // constrained variables declared here
    //

    BanquetPlanner(String fname) throws IOException {
        try (Scanner sc = new Scanner(new File(fname))) {
            nGuests   = sc.nextInt(); // number of guests
            mTables   = sc.nextInt(); // number of tables
            tableSize = nGuests / mTables;
            solver    = new Solver("banquet planner");

	    //
            // create the constrained variables here
	    //

            while (sc.hasNext()) {
                String s = sc.next();
                int i    = sc.nextInt();
                int j    = sc.nextInt();
		//
                // post constraints for together and apart
		//
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
	//
	// print out solution in specified format (see readme.txt)
	// so that results can be verified
	//
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

