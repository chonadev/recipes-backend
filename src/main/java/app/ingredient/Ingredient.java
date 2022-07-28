package app.ingredient;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Ingredient implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String name;
	private double amount;

//	@Column(name="recipe_id")
//	private long recipeId;
	
	public Ingredient() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

//	public long getRecipeId() {
//		return recipeId;
//	}
//
//	public void setRecipeId(long recipeId) {
//		this.recipeId = recipeId;
//	}
	
}
