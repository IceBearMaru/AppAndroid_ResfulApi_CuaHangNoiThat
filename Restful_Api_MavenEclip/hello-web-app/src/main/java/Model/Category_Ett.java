package Model;

public class Category_Ett {
    private int categoryID;
    private String categoryName;

    public Category_Ett() {
        // Default constructor
    }

    public Category_Ett(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    // Getter and setter methods

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // Other methods if needed
}
