//tileState with a weighted cost function

class tileState_cost extends tileState {

tileState_cost(String state) {
	super(state);
}

tileState_cost(String state, state parent, int action, int cost) {
	super(state, parent, action, cost);
}

@Override protected state successor(int move) {
	StringBuilder newState = new StringBuilder(state);
	newState.setCharAt(xPos, newState.charAt(move));
	newState.setCharAt(move, 'x');
	
	return new tileState_cost(
		newState.toString(),
		this,
		move,
		cost(move, xPos)
	);
}

@Override protected int cost(int space, int xPos) {
	return Math.abs(xPos - space);
}

@Override public String toString() {
	if (parent == null)
		return super.toString();
	else
		return super.toString() + " (c=" + cost  + ")";
}

}