
public class node {
	
	public double probability;
	public String word;
	public String pos_Tag;
	public node adjacent;
	
	/*Constructors*/
	public node(double probability, String word, String pos_Tag, node adjacent) {
		super();
		this.probability = probability;
		this.word = word;
		this.pos_Tag = pos_Tag;
		this.adjacent = adjacent;
	}
	
	public node(String word, String pos_Tag){
		this(0.0, word, pos_Tag, null);
	}

	public double getProbability() {
		return probability;
	}

	public String getWord() {
		return word;
	}

	public String getPos_Tag() {
		return pos_Tag;
	}

	public node getAdjacent() {
		return adjacent;
	}

}
