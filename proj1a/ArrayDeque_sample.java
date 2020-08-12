/**
 * ArrayDeque
 * implemented in circular way
 */

public class ArrayDeque<T> {
    public T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private int initsize = 8;


    /**
     * create a empty arraydeque
     */

    public ArrayDeque(){
        items = (T[]) new Object[initsize];
        nextFirst =0;
        nextLast =1;
        size = 0;
    }

    /**
     *  return true if deque is full, false otherwise.
      */
    private boolean isFull(){
        return size == items.length;
    }


    private boolean isSparse(){
        return items.length >= 16 && size <= (items.length/4);

    }

    /**
     * Add one circularly
     */

    private int plusOne(int index){
        return (index +1) % items.length;
    }

    private int minusOne(int index){
        return (index-1+items.length) % items.length;
    }

    /**
     * resize the deque.
     */
    private void resize(int capacity){
        T[] newDeque = (T[]) new Object [capacity];
        int oldIndex = plusOne(nextFirst);
        for (int newIndex = 0; newIndex < size ; newIndex++){
            newDeque[newIndex] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
    }

    /**
     * upsize the deque.
     */

    private void upSize(){
        resize(size*2);
    }

    /**
     *  Downsize the deque
     */
    private void downSize(){
        resize(items.length/2);
    }

    /**
     * Return true if deque is empty, false otherwise.
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * return the number of items in the deque
     */
    public int size(){
        return size;
    }

    /**
     * return the number of items in the deque from the first to last,
     * separate by a space
     */
    public void printDeque(){
        for(int i = plusOne(nextFirst); i!=nextLast; i=plusOne(i)){
            System.out.print(items[i]+ " ");
        }
        System.out.println();
    }

    public void addFirst(T x){
        if(isFull()){
            upSize();
        }
        items[nextFirst]=x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T x){
        if(isFull()){
            upSize();
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /**
     * Remove and return the item at the front of the deque.
     * If no such item exist, return null
     */

    public T removeFirst(){
        if(isSparse()){
            downSize();
        }
        nextFirst = plusOne(nextFirst);
        T toRemove = items[nextFirst];
        items[nextFirst] = null ;
        if (!isEmpty()){
            size -=1;
        }
        return toRemove;
    }

    /**
     * Remove and return the item at the back of the deque
     * If no such item exist, return null
     */
    public T removeLast(){
        if(isSparse()){
            downSize();
        }
        nextLast = minusOne(nextLast);
        T toRemove = items[nextLast];
        items[nextLast] = null;
        if (!isEmpty()){
            size -= 1;
        }
        return toRemove;
    }

    /**
     * Get the item at the given index, where 0 is the front.
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque.
     */
    public T get(int index){
        if(index >= size){
            return null;
        }
        int start = plusOne(nextFirst);
        return items[(start + index)% items.length];
    }

    /**
     *  Create a deep copy of other.
     */
    public ArrayDeque(ArrayDeque other){
        items =(T[]) new Object[other.size];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;

        System.arraycopy(other.items, 0, items, 0, other.size);
    }


}
