package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SoldOutItems {
	public String item_id;
	public String category;
	public String name;
	public Integer scale;
	public Integer sort; //???
	public Integer limit;

}
