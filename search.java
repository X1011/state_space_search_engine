//implements several search algorithms that find a path from an initial state to a goal state

import java.util.*;

public class search implements Runnable {

	public static enum method {
		BFS, DFS, UCS, GS,
		A_star {@Override public String toString() {return "A*";}}
	}

	Queue <state> remainingStates;
	method searchMethod;

	search(method searchMethod, state startState) {
		this.searchMethod = searchMethod;

		switch (searchMethod) {
			case BFS:
				remainingStates = new ArrayDeque <state>();
				break;
			
			case DFS:
				remainingStates = Collections.asLifoQueue(new ArrayDeque <state>());
				break;
			
			case UCS:
				remainingStates = new PriorityQueue <state>(11, new UCSComparator());
				break;
			
			case GS:
				remainingStates = new PriorityQueue <state>(11, new GSComparator());
				break;
			
			case A_star:
				remainingStates = new PriorityQueue <state>(11, new AStarComparator());
				break;
			
			default:
				throw new IllegalArgumentException("invalid search method: " + searchMethod);
		}
		
		remainingStates.add(startState);
	}

	//find a path to the goal and print it
	public void run() {

		System.out.println("searching using " + searchMethod);
		System.out.println();

		Set <state> visitedStates = new HashSet <state>();
		state goal = null;

		//search
		while (!remainingStates.isEmpty()) {
			state curState = remainingStates.remove();
			
			//goal check
			if (curState.h() == 0) {
				goal = curState;
				break;
			}
			
			if (visitedStates.add(curState)) //curState has not already been visited
				remainingStates.addAll(curState.successors());
		}
		
		//print path
		if (goal != null)
			printPathTo(goal);
		else
			System.out.println("goal not found");
		
		System.out.println();
	}
	
	private void printPathTo(state x) {
		
		int step = 0;
		double totalCost = 0;
		Queue <state> path = Collections.asLifoQueue(new ArrayDeque <state>());
		
		while (x != null) {
			path.add(x);
			x = x.parent;
		}

		for (state i : path) {
			System.out.println(step++ + "  " + i);
			totalCost += i.cost;
		}

		System.out.println("total path cost: " + totalCost);
	}
}

class UCSComparator implements Comparator <state> {
	public int compare(state s1, state s2) {
		return Double.valueOf(s1.g).compareTo(s2.g);
	}
}

class GSComparator implements Comparator <state> {
	public int compare(state s1, state s2) {
		return Double.valueOf(s1.h()).compareTo(s2.h());
	}
}

class AStarComparator implements Comparator <state> {
	public int compare(state s1, state s2) {
		return Double.valueOf(s1.g + s1.h()).compareTo(s2.g + s2.h());
	}
}
