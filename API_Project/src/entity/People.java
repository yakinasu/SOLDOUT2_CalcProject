package entity;

public class People {

	public Integer area_id;
	public Integer unit;
	public Integer persons;
	public String name;

	@Override
	public String toString() {
		return "area+id = " + area_id + ", unit" + unit + ", persons" + persons + ", name" + name;
	}
}
