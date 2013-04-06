import java.util.*;

class boxState extends state {

private final List <box> toFill;
private final List <box> toPut;

private final String assignment; //assignment of one box into another that led to this state

//start state
public boxState(Scanner in) {
	super();

	List <box> tempToFill = new ArrayList <box>();
	List <box> tempToPut = new ArrayList <box>();

	//read in boxes
	while (in.hasNext()) {
		box newBox = new box(in.next(), in.nextFloat(), in.nextFloat(), in.nextFloat());
		tempToFill.add(newBox);
		tempToPut.add(newBox);
	}

	//will try to fill one box at a time, in order of increasing volume
	Collections.sort(tempToFill);
	//toPut must be in descending order for the heuristic function
	Collections.sort(tempToPut, Collections.reverseOrder());

	toFill = Collections.unmodifiableList(tempToFill);
	toPut = Collections.unmodifiableList(tempToPut);

	assignment = "";
}

//regular state
public boxState(state parent, double cost, String assignment, List <box> toFill, List <box> toPut) {
	super(parent, cost);
	this.assignment = assignment;
	this.toFill = toFill;
	this.toPut = toPut;
}

@Override
protected Collection <state> successors() {
	Collection <state> successors = new ArrayList <state>();

	List <box> newToFill = new ArrayList <box>(toFill);
	boolean boxFilled = false;

	while (!newToFill.isEmpty() && !boxFilled) {
		//try to fill first box
		box om = newToFill.remove(0);

		//try every box to put in
		for (int i = 0; i < toPut.size(); i++) {
			box nom = toPut.get(i);

			if (om.canHas(nom)) {
				
				if (!boxFilled) {
					//we won't be removing any more from newToFill
					newToFill = Collections.unmodifiableList(newToFill);
					boxFilled = true;
				}

				List <box> newToPut = new ArrayList <box>(toPut);
				newToPut.remove(i);
				newToPut = Collections.unmodifiableList(newToPut);

				successors.add(new boxState(
					this,
					//cost is the amount of extra space left in the outer box
					om.volume - nom.volume,
					nom.name + " -> " + om.name,
					newToFill,
					newToPut
				));
			}
		}
	}

	return successors;
}

//heuristic: least possible amount of space wasted (every remaining box to fill gets the larget box it can hold)
@Override
protected double calcH() {
	double h = 0;

	for (box om : toFill) {
		for (box nom : toPut) {
			if (om.canHas(nom)) { //this is the largest box om can has
				h += om.volume - nom.volume;
				break;
			}
		}
	}

	return h;
}

@Override
public String toString() {
	return assignment;
}


@Override
public boolean equals(Object obj) {
	if (obj == null) {
		return false;
	}
	if (getClass() != obj.getClass()) {
		return false;
	}
	final boxState other = (boxState) obj;
	if (this.toFill != other.toFill &&
		(this.toFill == null || !this.toFill.equals(other.toFill))) {
		return false;
	}
	if (this.toPut != other.toPut && (this.toPut == null || !this.toPut.equals(other.toPut))) {
		return false;
	}
	return true;
}

@Override
public int hashCode() {
	int hash = 3;
	hash = 89 * hash + (this.toFill != null ? this.toFill.hashCode() : 0);
	hash = 89 * hash + (this.toPut != null ? this.toPut.hashCode() : 0);
	return hash;
}
}
