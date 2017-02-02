public class List {

    private Object []items;  
    private int numItems; 

    /**
     * A way of holding information but with direct index access, so that
     * information can be pulled from anywhere with ease.
     */
    public List()
    {
        this.items = new Object[0];
        this.numItems = 0;
    }  

    /**
     * @return boolean value for whether it is empty or not.
     */
    public boolean isEmpty()
    {
        return (numItems == 0);
    } 

    /**
     * @return how many elements are in the list.
     */
    public int size()
    {
        return numItems;
    }  

    /**
     * removes everything from the list.
     */
    public void removeAll()
    {
        items = new Object[0];
        numItems = 0;
    } 
    
    /**
     * @param index the spot on the list to add the item to.
     * @param item the new item that will be added to the list.
     */
    public void add(int index, Object item)		
    {
    	Object TMP[] = new Object[items.length+1];
          for (int pos = numItems-1; pos >= index; pos--)
            {
                TMP[pos+1] = items[pos];	
            } 
          	items = TMP;
            items[index] = item;
            numItems++;
    }
    
    /**
     * @param index the spot on the list that we want info from.
     * @return the item in the index on the list.
     */
    public Object get(int index)
    {
        if (index >= 0 && index < numItems)
        {
            return items[index];
        }
        return null;
    }
    
    /**
     * @param index finds the item at the index and deletes it.
     */
    public void remove(int index)		
    {
        if (index >= 0 && index < numItems)
        {
        	for (int pos = index+1; pos < numItems; pos++) 
            {
                items[pos-1] = items[pos];		
            } 
            numItems--;
            items[numItems] = null;		
        }
    }
}
