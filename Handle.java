/**
 * This class stores a Handle.
 *
 * @author karl88
 * @author sfbahr
 * @version Sep 17, 2014
 */
public class Handle implements Comparable<Handle>
{
    private int index;

    // ----------------------------------------------------------
    /**
     * Creates a new Handle object.
     *
     * @param initIndex the Handle being stored
     */
    public Handle(int initIndex)
    {
        index = initIndex;
    }

    // ----------------------------------------------------------
    /**
     * A getter method for the handle.
     *
     * @return the handle
     */
    public int get()
    {
        return index;
    }

    /**
     * Compares one handle to another.
     *
     * @param that The Handle that this one is being compared to
     * @return 1 if this is greater than that, 0 if equal, -1 if less than
     */
    @Override
    public int compareTo(Handle that)
    {
        if (this.index > that.get())
        {
            return 1;
        }
        else if (this.index == that.get())
        {
            return 0;
        }
        return -1;
    }
    
    @Override
    public String toString()
    {
        return ((Integer) index).toString();
    }
}
