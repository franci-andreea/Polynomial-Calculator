package model;

import controller.PolynomialSorter;

import java.util.ArrayList;
import java.util.List;

public class Polynomial
{
    private List<Monomial> polynomial;

    /**
     * Constructor to initialize the object's field
     */
    public Polynomial()
    {
        polynomial = new ArrayList<>();
    }

    /**
     * method to pretty print the polynomial and save its form as a string in a StringBuilder
     * @return - a String representing the form of the polynomial
     */
    public String printPolynomial()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Monomial monomial : polynomial)
        {
            System.out.print(monomial.toString() + " ");
            stringBuilder.append(monomial.toString());
        }
        System.out.println();

        return stringBuilder.toString();
    }

    public void addMonomialToPolynomialList(Monomial monomial)
    {
        polynomial.add(monomial);
    }

    public void sortPolynomialByDegree()
    {
        polynomial.sort(new PolynomialSorter());
    }

    @Override
    public String toString()
    {
        return "Polynomial{" +
                "polynomial=" + polynomial +
                '}';
    }

    /**
     * getter to access the list of monomials
     * @return - the list of monomials that compose the polynomial
     */
    public List<Monomial> getPolynomial()
    {
        return polynomial;
    }
}
