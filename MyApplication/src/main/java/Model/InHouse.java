package Model;

/** This is the InHouse Part class, which is a subclass of the abstract Part class.*/
public class InHouse extends Part{

    /** This is the InHouse Part Constructor.
     * The InHouse Part class constructor requires an ID, Name, Price, Stock, Minimum #, and Maximum #, and a Machine ID. This constructor uses the super constructor of the Part class.
     * @param id This is the InHouse Part ID (int).
     * @param name This is the InHouse Part Name (String).
     * @param price This is the InHouse Part Price (double).
     * @param stock This is the Stock # of the InHouse Part (int).
     * @param min This is the Minimum Stock of this InHouse Part we can have (int).
     * @param max This is the Maximum Stock of this InHouse Part we can have (int).
     * @param machineid This is the InHouse Part Machine ID (int). */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineid) {
        super(id, name, price, stock, min, max);
        this.machineid = machineid;
    }

    private int machineid;

    /** This is the InHouse Part Machine ID getter.
     * This returns the Machine ID of the InHouse Part.
     * @return Returns the Machine ID of the InHouse Part (int).*/
    public int getMachineid() {
        return machineid;
    }

    /** This is the InHouse Part Machine ID setter.
     * This sets the Machine ID for the InHouse Part.
     * @param machineid This is the desired Machine ID (int).*/
    public void setMachineid(int machineid) {
        this.machineid = machineid;
    }

}
