import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Bag<T> {
    private Map<T, Integer> bag = new HashMap<>();
    private int size;

    /**
     * inserts an item into the bag, if it already exists increment the counter by 1
     * @param t type of data being inserted
     */
    void insert(T t){
        int value;
        if (bag.containsKey(t)) {
            value = bag.get(t);
            bag.put(t, value + 1);
        }
        else
            bag.put(t, 1);
        size++;
    }

    /**
     * removes an item (decrements the associated value by 1) from the bag, if 0 remove the key
     * @param t type of data being removed
     * @return true if the item exists and was removed, false if no such item is in the bag
     */
    boolean remove(T t){
        int value;
        if (bag.containsKey(t)){
            value=bag.get(t);
            bag.put(t, value-1);
            if (bag.get(t)==0)
                bag.remove(t);
            size--;
            return true;
        }
        else
            return false;
    }

    /**
     * counts the quantity of t
     * @param t type of data being counted
     * @return the quantity of t
     */
    int count (T t){
        if (bag.containsKey(t))
            return bag.get(t);
        return 0;
    }

    /**
     * counts the number of items in the bag
     * @return number of items in the bag
     */
    int size(){
        return size;
    }

    /**
     * determines if the class contains t
     * @param t variable the method looks for
     * @return true if the class contains t, false otherwise
     */
    boolean contains(T t){
        return bag.containsKey(t);
    }

    /**
     * helper getter to get the key set for b in sub bag
     * @return key set of bag
     */
    Set<T> getKeySet(){
        return bag.keySet();
    }
    /**
     * determines if every element in b is in bag
     * @param b is being compared to bag
     * @return true if every element in b is in bag, false otherwise
     */
    boolean isSubBag(Bag b){
        if (b.size()==0)
            return true;
        if (bag.size()==0 && b.size()!=0)
            return false;
        Set<T> key= b.getKeySet();
        for (T i : key){
            if (!bag.containsKey(i))
                return false;
            if (bag.get(i)<b.count(i))
                return false;
        }
        return true;
    }
}
