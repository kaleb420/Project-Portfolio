public class Problem11 {
    /**
     * Checks if P->Q is true or false
     * @param P boolean
     * @param Q boolean
     * @return true or false
     */
    static boolean cond(boolean P, boolean Q){
        if (P==true && Q==false)
            return false;
        else
            return true;
    }

    /**
     * Checks if P <-> Q is true or false
     * @param P boolean
     * @param Q boolean
     * @return true or false
     */
    static boolean bicond(boolean P, boolean Q){
        if (P==Q)
            return true;
        else
            return false;
    }

    /**
     * Checks if P ^ Q is true or false
     * @param P boolean
     * @param Q boolean
     * @return true or false
     */
    static boolean and(boolean P, boolean Q){
        return P && Q;
    }

    /**
     * Checks if P ∨ Q is true or false
     * @param P boolean
     * @param Q boolean
     * @return true or false
     */
    static boolean or(boolean P, boolean Q){
        return P || Q;
    }

    /**
     * Checks if !P is true or false
     * @param P boolean
     * @return true or false / the opposite of what P was initialized as
     */
    static boolean not(boolean P){
        return !P;
    }
}
