package krushkal;

public class Vertex {
	int rank;
    Vertex representative;
    int name;
    Neighbour adj;

    Vertex(int name) {
        this.name = name;
        representative = this; // makeset
    }
}
