import java.io.Serializable;
import java.util.ArrayList;

public class GameObject implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	public final int MAX_GUESSES = 6;
	String currGuess;
	StringBuilder currString;
	int currGuesses;
	ArrayList<String> categories;
	ArrayList<String> categoriesCompleted;
	String categoryChoice;;
	boolean newCategory;
	int attempts;
	String roundOver;

	public GameObject() {
		currGuess = "";
		currString = new StringBuilder();
		currGuesses = 0;
		categories = new ArrayList<String>();
		categoriesCompleted = new ArrayList<String>();
		categoryChoice = "";
		newCategory = true;
		attempts = 0;
		roundOver = "";
	}

    public void updateState(GameObject newData) {
        this.currGuess = newData.currGuess;
        this.currString = newData.currString;
        this.currGuesses = newData.currGuesses;
        this.categories = new ArrayList<>(newData.categories);
        this.categoriesCompleted = new ArrayList<>(newData.categoriesCompleted);
        this.categoryChoice = newData.categoryChoice;
        this.newCategory = newData.newCategory;
        this.attempts = newData.attempts;
        this.roundOver = newData.roundOver;
    }
    
    public void print() {
    	System.out.println("address:" + this);
    	System.out.println("currGuess:" + currGuess);
    	System.out.println("currGuesses:" + currGuesses);
    	System.out.println("currString:" + currString.toString());
    	System.out.println("categories:" + categories);
    	System.out.println("categoriesCompleted:" + categoriesCompleted);
    	System.out.println("categoryChoice:" + categoryChoice);
    	System.out.println("newCategory:" + newCategory);
    	System.out.println("attempts:" + attempts);
    	System.out.println("\n");
    }

	public String getCurrGuess() {
		return currGuess;
	}

	public void setCurrGuess(String currGuess) {
		this.currGuess = currGuess;
	}

	public StringBuilder getCurrString() {
		return currString;
	}

	public void setCurrString(StringBuilder currString) {
		this.currString = currString;
	}

	public int getCurrGuesses() {
		return currGuesses;
	}

	public void setCurrGuesses(int currGuesses) {
		this.currGuesses = currGuesses;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}

	public ArrayList<String> getCategoriesCompleted() {
		return categoriesCompleted;
	}

	public void setCategoriesCompleted(ArrayList<String> categoriesCompleted) {
		this.categoriesCompleted = categoriesCompleted;
	}

	public String getCategoryChoice() {
		return categoryChoice;
	}

	public void setCategoryChoice(String categoryChoice) {
		this.categoryChoice = categoryChoice;
	}
	
	public boolean getNewcategory() {
		return newCategory;
	}
	
	public void setNewCategory(boolean newCategory) {
		this.newCategory = newCategory;
	}
	
	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
}
