package Model;

/** This is the abstract Part class.*/
public abstract class Part {

    /** This is the Part class constructor.
     * The Part class constructor requires an ID, Name, Price, Stock, Minimum #, and Maximum #.
     * @param id This is the Part ID (int).
     * @param name This is the Part Name (String).
     * @param price This is the Part Price (double).
     * @param stock This is the Stock # of the Part (int).
     * @param min This is the Minimum Stock of this Part we can have (int).
     * @param max This is the Maximum Stock of this Part we can have (int). */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }


    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** This is the Part ID getter.
     * This returns the ID of the Part.
     * @return Returns the Part ID (int).*/
    public int getId() {
        return id;
    }

    /** This is the Part ID setter.
     * This sets the ID for the Part.
     * @param id This is the desired Part ID (int).*/
    public void setId(int id) {
        this.id = id;
    }

    /** This is the Part Name getter.
     * This returns the Name of the Part.
     * @return Returns the Part Name (String).*/
    public String getName() {
        return name;
    }

    /** This is the Part Name setter.
     * This sets the Name for the Part.
     * @param name This is the desired Part Name (String).*/
    public void setName(String name) {
        this.name = name;
    }

    /** This is the Part Price getter.
     * This returns the Price of the Part.
     * @return Returns the Part Price (double).*/
    public double getPrice() {
        return price;
    }

    /** This is the Part Price setter.
     * This sets the ID for the Part.
     * @param price This is the desired Part Price (double).*/
    public void setPrice(double price) {
        this.price = price;
    }

    /** This is the Part Stock getter.
     * This returns the Stock of the Part.
     * @return Returns the Part Stock (int).*/
    public int getStock() {
        return stock;
    }

    /** This is the Part Stock setter.
     * This sets the Stock for the Part.
     * @param stock This is the desired Part Stock (int).*/
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** This is the Part Minimum Stock getter.
     * This returns the Minimum Stock of the Part.
     * @return Returns the Minimum Stock of the Part (int).*/
    public int getMin() {
        return min;
    }

    /** This is the Part Minimum Stock setter.
     * This sets the Minimum Stock for the Part.
     * @param min This is the desired Minimum Stock of the Part (int).*/
    public void setMin(int min) {
        this.min = min;
    }

    /** This is the Part Maximum Stock getter.
     * This returns the Maximum Stock of the Part.
     * @return Returns the Maximum Stock of the Part (int).*/
    public int getMax() {
        return max;
    }

    /** This is the Part Maximum Stock setter.
     * This sets the Maximum Stock for the Part.
     * @param max This is the desired Maximum Stock of the Part (int).*/
    public void setMax(int max) {
        this.max = max;
    }

}
