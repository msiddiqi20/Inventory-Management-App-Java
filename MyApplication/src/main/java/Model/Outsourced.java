package Model;

/** This is the Outsourced Part class, which is a subclass of the abstract Part class.*/
public class Outsourced extends Part{

    /** This is the Outsourced Part Constructor.
     * The Outsourced Part class constructor requires an ID, Name, Price, Stock, Minimum #, and Maximum #, and the Company Name. This constructor uses the super constructor of the Part class.
     * @param id This is the Outsourced Part ID (int).
     * @param name This is the Outsourced Part Name (String).
     * @param price This is the Outsourced Part Price (double).
     * @param stock This is the Stock # of the Outsourced Part (int).
     * @param min This is the Minimum Stock of this Outsourced Part we can have (int).
     * @param max This is the Maximum Stock of this Outsourced Part we can have (int).
     * @param companyName This is the Outsourced Part's Company Name (String). */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    private String companyName;

    /** This is the Outsourced Part Company Name getter.
     * This returns the Company Name of the Outsourced Part.
     * @return Returns the Company Name of the Outsourced Part (String).*/
    public String getCompanyName() {
        return companyName;
    }

    /** This is the Outsourced Part's Company Name setter.
     * This sets the Company Name for the Outsourced Part.
     * @param companyName This is the desired Company Name (String).*/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
