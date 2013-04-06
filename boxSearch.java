import java.util.*;
import java.io.*;

//dan smith
//2010-4-23

public class boxSearch {

public static void main(String[] args) throws FileNotFoundException {
	state startState = new boxState(new Scanner(new File(args[0])));

	new search(search.method.valueOf(args[1]), startState).run();
	/*
	new search(search.method.BFS, startState).run();
	new search(search.method.UCS, startState).run();
	new search(search.method.GS, startState).run();
	new search(search.method.A_star, startState).run();
	*/
}

}
