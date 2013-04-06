import java.util.*;

class tileState extends state {

protected final String state;
protected final int xPos; //position of the free space
protected final int action; //tile that was moved to reach this state

tileState(String state) {
	super();

	this.state = state;
	xPos = state.indexOf('x');
	this.action = 0;
}

tileState(String state, state parent, int action, int cost) {
	super(parent, cost);
	
	this.state = state;
	xPos = state.indexOf('x');
	this.action = action;
}

protected double calcH() {
	double h = 0;
	for (int space = 0; space < state.length(); space++) {
		if (state.charAt(space) == 'W' && space < xPos
		 || state.charAt(space) == 'B' && space > xPos)
			h++;
	}

	return h;
}

public Collection <state> successors() {
	Collection <state> successors = new ArrayList <state>();
	
	for (int space = 0; space < state.length(); space++)
		if (state.charAt(space) != 'x')
			successors.add(successor(space));
	
	return successors;
}

protected state successor(int move) {
	StringBuilder newState = new StringBuilder(state);
	newState.setCharAt(xPos, newState.charAt(move));
	newState.setCharAt(move, 'x');
	
	return new tileState(
		newState.toString(),
		this,
		move,
		cost(move, xPos)
	);
}

protected int cost(int space, int xPos) {
	return 1;
}

@Override public String toString() {
	if (parent == null)
		return "       " + state;
	else
		return "move " + action + " " + state;
}

@Override public boolean equals(Object s2) {
	if (!(s2 instanceof tileState))
		return false;
	
	return state.equals(((tileState) s2).state);
}

@Override public int hashCode() {
	return state.hashCode();
}

}