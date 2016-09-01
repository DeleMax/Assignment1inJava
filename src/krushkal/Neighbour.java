package krushkal;

public class Neighbour {
	int index;
    Neighbour next;
    int weight;

    Neighbour(int index, int weight, Neighbour next) {
        this.index = index;
        this.weight = weight;
        this.next = next;
    }
}
