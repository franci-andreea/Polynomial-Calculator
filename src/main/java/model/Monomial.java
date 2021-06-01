package model;

import java.text.DecimalFormat;

public class Monomial
{
    private double coefficient;
    private int rank;

    /**
     * Constructor to initialize the object's fields
     * @param coefficient - a double representing the coefficient of the monomial
     * @param rank - an integer representing the rank (degree) of the monomial
     */
    public Monomial(double coefficient, int rank)
    {
        this.coefficient = coefficient;
        this.rank = rank;
    }

    /**
     * method to multiply a monomial with a polynomial
     * @param polynomial - the polynomial which has to be multiplied with the monomial
     * @return - a Polynomial object representing the result of the operation
     */
    public Polynomial multiplyWithAPolynomial(Polynomial polynomial)
    {
        double coefficientResulted;
        int rankResulted;

        Polynomial result = new Polynomial();

        for(Monomial monomial : polynomial.getPolynomial())
        {
            coefficientResulted = coefficient * monomial.getCoefficient();
            rankResulted = rank + monomial.getRank();

            result.getPolynomial().add(new Monomial(coefficientResulted, rankResulted));
        }

        result.printPolynomial();

        return result;
    }

    /**
     * method to obtain the monomial as a string
     * @return - a String representing the monomial from the polynomial
     */
    @Override
    public String toString()
    {
        StringBuilder monomialAsString = new StringBuilder();

        DecimalFormat decimalFormat = new DecimalFormat("###.#");

        if(coefficient == 0)
        {
            monomialAsString.append(" ");
        }
        else
        {
            if(coefficient == -1)
                monomialAsString.append("-");
            if (coefficient > 0)
            {
                monomialAsString.append("+");
            }
            if (coefficient != 1 && coefficient != -1)
                monomialAsString.append(decimalFormat.format(coefficient));
            if (rank != 1)
            {
                monomialAsString.append("x^");
                monomialAsString.append(rank);
            } else if(rank < 0)
                monomialAsString.append(" ");
            else
                monomialAsString.append("x");
        }
        return monomialAsString.toString();
    }

    /**
     * useful getters and setters
     */
    public double getCoefficient()
    {
        return coefficient;
    }

    public void setCoefficient(double coefficient)
    {
        this.coefficient = coefficient;
    }

    public int getRank()
    {
        return rank;
    }
}
