//dan smith
//2010-02-05

import java.util.*;
import java.io.*;

public class tileSearch {

public static void main(String args[]) throws Exception {
	
	//process arguments
	
	int arg = 0;
	
	boolean weightedCost = false;
	
	if (args[arg].equalsIgnoreCase("-cost")) {
		weightedCost = true;
		arg++;
	}
	
	String searchMethodString = args[arg++];
	search.method searchMethod;

	if (searchMethodString.equals("A-star"))
		searchMethod = search.method.A_star;
	else
		searchMethod = search.method.valueOf(searchMethodString);

	String input = new Scanner(new File(args[arg])).nextLine();
	
	//validate input file
	for (int i = 0; i < input.length(); i++)
		if (!(input.charAt(i) == 'x'
		   || input.charAt(i) == 'B'
		   || input.charAt(i) == 'W'))
			throw new Exception("invalid character in input: " + input.charAt(i));
	
	//search
	
	state startState;
	if (weightedCost)
		startState = new tileState_cost(input);
	else
		startState = new tileState(input);
	
	new search(searchMethod, startState).run();
}

}