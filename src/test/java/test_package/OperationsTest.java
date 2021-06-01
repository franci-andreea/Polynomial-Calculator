package test_package;

import controller.Controller;
import model.Polynomial;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperationsTest
{
    Polynomial polynomial1 = Controller.readPolynomial("2x^2 - 4x^3 + 7x");
    Polynomial polynomial2 = Controller.readPolynomial("3x^3 + 5x^2 - 11x");
    Polynomial result;

    @Test
    public void addTwoPolynomials()
    {
        result = Controller.addTwoPolynomials(polynomial1, polynomial2);
        assertEquals("-x^3+7x^2-4x", result.printPolynomial());
    }

    @Test
    public void subtractTwoPolynomials()
    {
        result = Controller.subtractTwoPolynomials(polynomial1, polynomial2);
        assertEquals("-7x^3-3x^2+18x", result.printPolynomial());
    }

    @Test
    public void multiplyTwoPolynomials()
    {
        result = Controller.multiplyTwoPolynomials(polynomial1, polynomial2);
        assertEquals("-12x^6-14x^5+75x^4+13x^3-77x^2", result.printPolynomial());
    }

    @Test
    public void derivatePolynomial()
    {
        result = Controller.derivatePolynomial(polynomial1);
        assertEquals("-12x^2+4x+7x^0", result.printPolynomial());
    }

    @Test
    public void integratePolynomial()
    {
        result = Controller.integratePolynomial(polynomial1);
        assertEquals("-x^4+0.7x^3+3.5x^2", result.printPolynomial());
    }
}
