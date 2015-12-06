/**
 * KVPair class definition: Pretty specific for Project 2
 * @author CS3114 Instructor and TAs
 * @version 9/22/2014
 */
public class KVPair<T extends Comparable<? super T>> implements Comparable<KVPair<T>>
{
    private T value;
    private T value2 = null;

    /**
     * Constructor
     * @param k the key (first Handle)
     * @param v the value (second Handle)
     */
    public KVPair(T value)
    {
        this.value = value;
    }

    public KVPair(T handle, T handle2) {
		this.value = handle;
		this.value2 = handle2;
	}

	/**
     * The magic that lets us compare two KVPairs.
     * KVPairs are all that this knows how to compare against
     * First compare the key field. If they are identical,
     * then break the tie with the value field.
     * @return the usual for a comparable (+, 0, -)
     * @param it the KVPair to compare "this" against
     */
    public int compareTo(KVPair<T> it)
    {
    	int firstComp = value.compareTo(it.getValue());
    	if (firstComp == 0 && value2 != null) {
    		return value2.compareTo(it.getValue2());
    	}
    	return firstComp;
    }


    /**
     * Getter for "vertLine"
     * @return the vertLine
     */
    public T getValue()
    {
        return value;
    }
    
    public T getValue2()
    {
    	return value2;
    }

    /**
     * Standard toString method
     * @return the printable string
     */
    public String toString()
    {
    	if (value2 != null) {
    		return value.toString() + " " + value2.toString();
    	}
        return value.toString();
    }
    
    public int getX()
    {
    	return ((VerticalLine) value).getX();
    }
}