public class Queue<T> {
	
	private T[] items;			
	private int front;
	private int rear;
	
	/**
	 * Queue is a way of organizing information so that the item entered first,
	 * is the item that will be output first.
	 */
	public Queue()
	{
		front = 0;
		rear = 0;
		items = (T[])new Object[0];
	}
	
	/**
	 * @return boolean value of whether it is empty or not.
	 */
	public boolean isEmpty() {
		return (rear == 0);
	}

	/**
	 * @param newItem the new item entered into the queue.
	 */
	public void enqueue(T newItem)
	{			
		T[]TMP = (T[])new Object[items.length + 1];
		
		for (int a = 0; a < items.length; a++)
		{
			TMP[a] = items[a];
		}
		items = TMP;
		items[rear] = (T) newItem;
		rear++;
	}

	/**
	 * @return the oldest item entered in the queue.
	 */
	public T dequeue()
	{	
		Object result = items[front];
		T[]TMP = (T[])new Object[items.length - 1];
		
		for (int a = 0; a < items.length-1; a++)
		{
			TMP[a] = items[a+1];
		}
		items = TMP;
		rear--;
		return (T) result;
	}

	/**
	 * removes all the items from the queue.
	 */
	public void dequeueAll() {
		front = 0;
		rear = 0;
		items = (T[])new Object[3];
	}
	
	/**
	 * returns all of the planes and their destination.
	 */
	public String toString() 
	{
		int TMP = front;
		String output = "";
		while (TMP < rear)
		{
			output += ((Plane) items[TMP]).getNum() + " to " + ((Plane) items[TMP]).getDestination();
			if (TMP != rear-1)
			{
				output += ", ";
			}
			TMP++;
		}
		return output;
	}

	/** 
	 * @return the oldest item on the queue.
	 */
	public T peek()
	{
		if (isEmpty())
		{
			return null;
		}
		return items[front];
	}
}
