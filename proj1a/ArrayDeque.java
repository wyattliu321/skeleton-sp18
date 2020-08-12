/**
 * ArrayDeque
 * implemented in a circular way
 */


public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        T[] items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public int plusOne(int index){
        return (index + 1) % items.length;
    }

    public int minusOne(int index){
        return (index -1 + items.length)% items.length;

    }

    public boolean isEmpty(){
        return size == 0;

    }

    public boolean isFull(){
       return size == items.length;
        /* return plusOne(nextFirst) == minusOne(nextLast); */

    }

    public boolean isSparse(){
        return items.length >= 16 && size < (items.length/4);
    }

    public int size(){
        return size;
    }

    public void resize(int capacity){
        T[] newitems = (T[]) new Object[capacity];
        int n = items.length;
        int OrigHead = plusOne(nextFirst);
        int r = items.length - OrigHead;
        System.arraycopy(items, OrigHead, newitems, 0, r);
        System.arraycopy(items,0, newitems, r, OrigHead);
        items = newitems;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    public void doubleCapacity(){
        resize(items.length*2);
    }

    public void halveCapacity(){
        resize (items.length/2);
    }

    public void printDeque(){
        for(int i = plusOne(nextFirst); i!=nextLast ; i=plusOne(i)){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public void addFirst(T item){
        if (isFull()){
            doubleCapacity();
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1 ;
    }

    public void addLast(T item){
        if (isFull()){
            doubleCapacity();
        }
        items[nextLast] = item;
        nextLast = minusOne(nextLast);
        size += 1;
    }

    public T removeFirst(){
        if (isSparse()){
            halveCapacity();
        }
        nextFirst = plusOne(nextFirst);
        T toRemove = items[nextFirst];
        items[nextFirst] = null;
        if (!isEmpty()){
            size -= 1;
        }
        return toRemove;
    }

    public T removeLast(){
        if(isSparse()){
            halveCapacity();
        }
        nextLast = minusOne(nextLast);
        T toRemove = items[nextLast];
        items[nextLast] = null;
        if (isEmpty()){
            size -= 1;
        }
        return toRemove;
    }

    public T get(int index){
        if(index >= size){
            return null;
        }
        int start = plusOne(nextFirst);
        return items[(start + index) % items.length];

    }

    /**
     *  Return a deep copy if other
     */

    public ArrayDeque(ArrayDeque other){
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
        items = (T[]) new Object[size];
        System.arraycopy(other.items,0,items,0,size);

    }

}
