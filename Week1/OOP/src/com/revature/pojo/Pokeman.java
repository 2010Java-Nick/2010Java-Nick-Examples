package com.revature.pojo;

/*
 * POJO: plain old java object
 * Model, has private fields, and getters and setters
 */

/*
 * Naming Conventions
 * class: Pascal casing, capitalize first letter and the first letter of each other work, no spaces
 * ex: MySuperAwesomeClass
 * method: Camel Casing, first letter lowercase, then first letter of each other work capitalzied, no spaces
 * ex: myMethodThatDoesStuff()
 * field: also CamelCase
 * constant: snake case, all upper case, with underscores between words
 * ex: MY_CONSTANT_THAT_IS_ALWAYS_THIS
 * package: reverse domain name, lowercase, dots into folders
 * ex: com.revature.caliber.pojo
 */

public class Pokeman {
	
	//final makes a field constant, for primative types that means you cannot change the value
	private final int MIN_POKEDEX_NUMBER_VALUE = 1;
	
	//We make fields private, to properly encapsulate our class
	//encapsulation is the concept of hiding data
	private int pokedexNumber;
	
	public int getPokedexNumber() {
		//int pokedexNumber = 17; would shadow the instance pokedexNumber variable
		//
		/*
		 * some code		
		 */
		return this.pokedexNumber; //self documenting code, best practice to use this, although not necessary
	}
	
	public void setPokedexNumber(int pokedexNumber) {
		if (pokedexNumber < MIN_POKEDEX_NUMBER_VALUE) {
			pokedexNumber = 0;
		}
		this.pokedexNumber = pokedexNumber;
	}
	
	

}
