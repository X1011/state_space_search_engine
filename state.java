import java.util.*;

abstract class state {

public final state parent; //the predecessor to this state
public final double cost; //cost of the action taken from the previous state
public final double g; //path cost to this state
private double h; //heuristic value of this state (accessed with h() method)

//constuctor for start state
state() {
	parent = null;
	cost = 0;
	g = 0;
}

//constuctor for regular state
state(state parent, double cost) {
	this.parent = parent;
	this.cost = cost;
	g = parent.g + cost;
}

//ugly hack because i can't use calcH() in the constructor
private boolean hCalculated = false;
public double h() {
	if (!hCalculated) {
		h = calcH();
		hCalculated = true;
	}
	return h;
}

protected abstract double calcH(); //calculate h
protected abstract Collection <state> successors();

@Override public abstract String toString();
@Override public abstract boolean equals(Object s2);
@Override public abstract int hashCode();

}