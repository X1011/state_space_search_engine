I wrote this originally for an AI class assignment and then extended it and entered it into the Spring 2010 ComputingFest programming competition at the University of Texas at Dallas. The code is not the best; I've learned a lot about software design since then. The original readme follows.

The state space search engine is a generalized program that solves state space search problems, wherein a path from a given start state to a desired goal state is found. The cost of this path may also be minimized. For example, in the problem of a single-player tile-moving game, a state is the position of all the tiles on the board, a player transitions between states by moving tiles according to the rules of the game, and a goal state is a winning placement of the tiles. Possible path costs are the number of moves made or the total distance moved by all tiles.

The user specifies a search problem by creating a subclass to represent a state. This class determines the information needed to represent a state and, given any state, generates all possible successor states that can be reached with one transition. It determines the cost of transitioning to each of these states and optionally specifies a heuristic function used in some search strategies to increase the efficiency of the search. The class also determines if a given state is a goal state. Given this class and a starting state, the program can use one of several search strategies to find a solution, including depth-first search, breadth-first search, uniform cost search, greedy search, and A* search.


2 sample programs are included:

tileSearch:
	This is a representation of a single-player tile-moving game, as hinted above. The goal is to move all the Bs to the left of the x and all the Ws to the right by swapping a B or W with the x one at a time.
	
	See tileSearch.bat for how to run it. The -cost flag tells it to use the distance traveled by the tile as the cost of the move. Without the flag, the cost is 1 for all moves. The program only processes the first line of the input file, but i have included 2 more lines in the sample data file that you can swap out.

boxSearch:
	This one was created to solve a real-world problem of interest to me: how to nest boxes inside each other in order to minimize the space they occupy (the sum of the volumes of the outermost boxes). The input file gives a list of boxes, one on each line, with an identifier for the box and the dimensions of the box, in any order.
	
	See tileSearch.bat for how to run it. Note that A* is specified as A_star rather than A-star for this one, just because i'm passing the argument directly to an enum and it doesn't allows dashes in the names.
	
	I created the GraphML files to help me manually verify a solution to this problem.
	
	I have discovered that the way I've defined the cost can lead to a non-optimal solution. (It will prefer placing the largest possible box (in terms of volume) into a given box, but this is too limiting if a smaller box has fewer possible boxes it can fit in due to its shape.) 
