import java.util.*;

class box implements Comparable <box> {

public final String name;
public final Float d[] = new Float[3]; //dimensions
public final double volume;

public box(String name, float d1, float d2, float d3) {
	this.name = name;

	d[0] = d1;
	d[1] = d2;
	d[2] = d3;
	Arrays.sort(d, Collections.reverseOrder());

	volume = d1 * d2 * d3;
}

public int compareTo(box x) {
	return Double.valueOf(volume).compareTo(x.volume);
}

//this box can contain that box
public boolean canHas(box x) {
	if (d[0] > x.d[0] && d[1] > x.d[1] && d[2] > x.d[2]) {
		assert volume > x.volume;
		return true;
	}
	//else if (d[0] < x.d[0] && d[1] < x.d[1] && d[2] < x.d[2])
	//	return -1;
	else
		return false;
}

}
