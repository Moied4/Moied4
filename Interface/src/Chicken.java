
public class Chicken extends Animal implements Edible {
	
	@Override
	public String eat() {
		return "eat chicken";
	}
	
	@Override
	public String speak() {
		return "Buk Buk";
	}
}