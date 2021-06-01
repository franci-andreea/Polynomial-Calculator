package controller;

import model.Monomial;
import model.Polynomial;
import view.AppMainWindow;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller
{
    private AppMainWindow appMainWindow;

    /**
     * Constructor that initializes the controller and creates a new application window
     */
    public Controller()
    {
        appMainWindow = new AppMainWindow();
    }

    /**
     * Method that initializes all the buttons visible on the main window
     */
    public void start()
    {
        appMainWindow.initializeAddButton();
        appMainWindow.initializeSubtractButton();
        appMainWindow.initializeMultiplyButton();
        appMainWindow.initializeDerivateButton();
        appMainWindow.initializeIntegrateButton();
    }

    /**
     * Method that reads a polynomial as string and converts it to a Polynomial instance
     * @param polynomialIntroduced - the string introduced by the user in the graphical user interface representing the
     *                               polynomial introduced
     * @return a Polynomial object that has all the monomials separated in a list
     */
    public static Polynomial readPolynomial(String polynomialIntroduced) {
        Polynomial polynomial = new Polynomial();
        Monomial monomial;
        double coefficient;
        int rank;

        String input = polynomialIntroduced;
        Pattern pattern = Pattern.compile("(([+-]?)([\\d]*)[Xx][\\^]?(\\d)?)");
        input = input.replace(" ", "");
        Matcher matcher = pattern.matcher(input);
        while(matcher.find())
        {
            if(matcher.group(3).isEmpty() || matcher.group(3).equals(""))
                coefficient = 1;
            else
                coefficient = Double.parseDouble(matcher.group(3));

            if(matcher.group(2).isEmpty() || matcher.group(2).equals(""))
                coefficient = coefficient * 1;
            else if(matcher.group(2).equals("-"))
                coefficient = coefficient * (-1);

            if(matcher.group(4) == null || matcher.group(4).isEmpty() || matcher.group(4).equals(""))
                rank = 1;
            else
                rank = Integer.parseInt(matcher.group(4));

            monomial = new Monomial(coefficient, rank);

            polynomial.addMonomialToPolynomialList(monomial);
        }

        return polynomial;
    }

    /**
     * Method that performs the addition of two polynomials
     * @param polynomial1 - a Polynomial object representing the first polynomial introduced
     * @param polynomial2 - a Polynomial object representing the second polynomial introduced
     * @return - a Polynomial object that represents the result of the operation
     */
    public static Polynomial addTwoPolynomials(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();
        double coefficient;
        int rank;

        boolean found;

        polynomial1.getPolynomial().sort(new PolynomialSorter());
        polynomial2.getPolynomial().sort(new PolynomialSorter());

        found = false;
        for(Monomial monomial1 : polynomial1.getPolynomial())
        {
            for(Monomial monomial2 : polynomial2.getPolynomial())
            {
                if(monomial1.getRank() == monomial2.getRank())
                {
                    coefficient = monomial1.getCoefficient() + monomial2.getCoefficient();
                    rank = monomial1.getRank();
                    result.getPolynomial().add(new Monomial(coefficient, rank));
                    found = true;
                }
            }

            if(!found)
            {
                result.getPolynomial().add(monomial1);
            }

            found = false;
        }

        found = false;
        for(Monomial monomial2 : polynomial2.getPolynomial())
        {
            for(Monomial monomial : result.getPolynomial())
                if(monomial2.getRank() == monomial.getRank())
                {
                    found = true;
                }
            if(!found)
            {
                result.getPolynomial().add(monomial2);
            }
            found = false;
        }

        result.sortPolynomialByDegree();

        return result;
    }

    /**
     * Method that performs the subtraction of two polynomials
     * @param polynomial1 - a Polynomial object representing the first polynomial introduced
     * @param polynomial2 - a Polynomial object representing the second polynomial introduced
     * @return - a Polynomial object that represents the result of the operation
     */
    public static Polynomial subtractTwoPolynomials(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();
        double coefficient;
        int rank;
        boolean found;
        polynomial1.getPolynomial().sort(new PolynomialSorter());
        polynomial2.getPolynomial().sort(new PolynomialSorter());
        found = false;
        for(Monomial monomial1 : polynomial1.getPolynomial()) {
            for(Monomial monomial2 : polynomial2.getPolynomial()) {
                if(monomial1.getRank() == monomial2.getRank()) {
                    coefficient = monomial1.getCoefficient() - monomial2.getCoefficient();
                    rank = monomial1.getRank();
                    result.getPolynomial().add(new Monomial(coefficient, rank));
                    found = true;
                }
            }
            if(!found) {
                result.getPolynomial().add(monomial1);
            }

            found = false;
        }
        found = false;
        for(Monomial monomial2 : polynomial2.getPolynomial()) {
            for(Monomial monomial : result.getPolynomial())
                if(monomial2.getRank() == monomial.getRank()) {
                    found = true;
                }
            if(!found) {
                coefficient = monomial2.getCoefficient() * (-1);
                monomial2.setCoefficient(coefficient);
                result.getPolynomial().add(monomial2);
            }
            found = false;
        }
        result.sortPolynomialByDegree();
        return result;
    }

    /**
     * Method that performs the multiplication of two polynomials
     * @param polynomial1 - a Polynomial object representing the first polynomial introduced
     * @param polynomial2 - a Polynomial object representing the second polynomial introduced
     * @return - a Polynomial object that represents the result of the operation
     */
    public static Polynomial multiplyTwoPolynomials(Polynomial polynomial1, Polynomial polynomial2) {
        polynomial1.sortPolynomialByDegree();
        polynomial2.sortPolynomialByDegree();

        Polynomial result = new Polynomial();
        Polynomial aux;

        for(Monomial monomial1 : polynomial1.getPolynomial())
        {
            aux = monomial1.multiplyWithAPolynomial(polynomial2);
            result = addTwoPolynomials(result, aux);
        }

        return result;
    }

    /**
     * Method that performs the derivation of a polynomial
     * @param polynomial - a Polynomial object representing the first polynomial introduced
     * @return - a Polynomial object that represents the result of the operation
     */
    public static Polynomial derivatePolynomial(Polynomial polynomial) {
        polynomial.sortPolynomialByDegree();

        double coefficient;
        int rank;

        Polynomial result = new Polynomial();

        for(Monomial monomial : polynomial.getPolynomial())
        {
            coefficient = monomial.getCoefficient() * monomial.getRank();
            rank = monomial.getRank() - 1;

            result.getPolynomial().add(new Monomial(coefficient, rank));
        }

        result.printPolynomial();

        return result;
    }

    /**
     * Method that performs the integration of a polynomial
     * @param polynomial - a Polynomial object representing the first polynomial introduced
     * @return - a Polynomial object that represents the result of the operation
     */
    public static Polynomial integratePolynomial(Polynomial polynomial) {
        polynomial.sortPolynomialByDegree();

        double coefficient;
        int rank;

        Polynomial result = new Polynomial();

        for(Monomial monomial : polynomial.getPolynomial())
        {
            coefficient = monomial.getCoefficient() / (monomial.getRank() + 1);
            rank = monomial.getRank() + 1;

            result.getPolynomial().add(new Monomial(coefficient, rank));
        }

        result.printPolynomial();

        return result;
    }
}
