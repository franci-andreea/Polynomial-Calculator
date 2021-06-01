package controller;

import model.Monomial;

import java.util.Comparator;

public class PolynomialSorter implements Comparator<Monomial>
{
    /**
     * method that compared the ranks (degrees) of two monomials
     * @param o1 - the first monomial
     * @param o2 - the second monomial
     * @return - the value of the difference between the two values as an integer
     */
    @Override
    public int compare(Monomial o1, Monomial o2)
    {
        return String.valueOf(o2.getRank()).compareTo(String.valueOf(o1.getRank()));
    }
}
