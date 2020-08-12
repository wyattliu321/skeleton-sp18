/**
 * LinkedListDeque
 */

public class LinkedListDeque<T> {
    private class TNode{
        private T item;
        private TNode prev;
        private TNode next;

        private TNode(T x, TNode p, TNode n){
            item =x;
            prev = p;
            next = n;
        }
    }

    private TNode sentinel;
    private int size;

    /**
     *  create an empty deque;
     */

    public LinkedListDeque(){
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Return the number of items in the deque
     */
    public int size(){
        return size;
    }

    /**
     * return ture if deque is empty, false otherwise
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Add an item of type T to the very ftont of the deque
     */
    public void addFirst(T item){
        sentinel.next = new TNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size+=1;
    }

    /**
     * Add an item of type T tothe back of the deque
     */
    public void addLast(T item){
        sentinel.prev = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size+=1;
    }

    /**
     * remove and return the item at the front of the deque
     * if no such item exists, then return null
     */

    public T removeFirst(){
        T toRemove = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        if (! isEmpty()){
            size-=1;
        }
        return toRemove;
    }

    public T removeLast(){
        T toRemove = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if(!isEmpty()){
            size -= 1;
        }
        return toRemove;
    }

    /**
     *  print the items in the deque from first to last, separated by a space,
     *  Once all the items have been printed, print out a new line
     */

    public void printDeque(){
        TNode toPrint = sentinel.next;
        for (int i =0; i< size ; i++){
            System.out.print((toPrint.item + " "));
            toPrint = toPrint.next;
        }
        System.out.println();
    }

    /**
     * get the item at the given index, where 0 is the front, 1 is the next item, a
     * and so forth, if no such item exist, return null. Must not alter the deque
     */
    public T get(int index){
        TNode toGet = sentinel.next;
        for (int i =0; i< index; i++){
            toGet = toGet.next;
        }
        return toGet.item;
    }

    /**
     * same as above iterative method, this time applying recursive version.
     */
    private T getRecursive(int index, TNode curr){
        if(index == 0){
            return curr.item;
        }
        return getRecursive(index-1, curr.next);
    }
    /**
     * overload method?
     */
    private T getRecursive(int index){
        return getRecursive(index, sentinel.next);
    }

}
