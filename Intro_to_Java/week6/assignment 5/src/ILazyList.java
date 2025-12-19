public interface ILazyList<T> {
    /**
     * computes the next element in the list
     * @return next element in the list
     */
    T next();
}
