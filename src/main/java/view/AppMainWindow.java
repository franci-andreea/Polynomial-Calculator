package view;

import controller.Controller;

import model.Monomial;
import model.Polynomial;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;

public class AppMainWindow
{
    private JFrame calculatorFrame = new JFrame("Polynomial Calculator");
    private JPanel calculatorPanel;
    private JLabel firstPolynomialLabel;
    private JLabel secondPolynomialLabel;
    private JLabel resultLabel;
    private JTextField firstPolynomialField;
    private JTextField secondPolynomialField;
    private JTextArea resultArea;
    private JButton additionButton;
    private JButton subtractionButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton derivateButton;
    private JButton integrateButton;

    /**
     * Constructor to initialize all the object's fields and create the window
     */
    public AppMainWindow()
    {
        calculatorPanel = new JPanel();

        calculatorPanel.setBorder(BorderFactory.createTitledBorder("Calculator"));

        calculatorPanel.setLayout(new GridLayout(5, 3, 15, 15));

        firstPolynomialLabel = new JLabel("first polynomial");
        firstPolynomialField = new JTextField();
        firstPolynomialField.setColumns(30);

        secondPolynomialLabel = new JLabel("second polynomial");
        secondPolynomialField = new JTextField();

        resultLabel = new JLabel("RESULT");
        resultArea = new JTextArea();

        additionButton = new JButton("+");
        subtractionButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        derivateButton = new JButton("'");
        integrateButton = new JButton("∫");

        calculatorPanel.add(firstPolynomialLabel);
        calculatorPanel.add(firstPolynomialField);
        calculatorPanel.add(new JLabel(" "));
        calculatorPanel.add(secondPolynomialLabel);
        calculatorPanel.add(secondPolynomialField);
        calculatorPanel.add(new JLabel(" "));
        calculatorPanel.add(resultLabel);
        calculatorPanel.add(resultArea);
        calculatorPanel.add(new JLabel(" "));
        calculatorPanel.add(additionButton);
        calculatorPanel.add(subtractionButton);
        calculatorPanel.add(multiplyButton);
        calculatorPanel.add(divideButton);
        calculatorPanel.add(derivateButton);
        calculatorPanel.add(integrateButton);

        calculatorFrame.setVisible(true);
        calculatorFrame.setLayout(new FlowLayout());
        calculatorFrame.add(calculatorPanel);
        calculatorFrame.pack();
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * method that adds an action listener to the add button
     */
    public void initializeAddButton()
    {
        this.addAddButtonPressedActionListener(e -> {
            resultArea.setText("");

            Polynomial polynomial1 = Controller.readPolynomial(firstPolynomialField.getText());
            Polynomial polynomial2 = Controller.readPolynomial(secondPolynomialField.getText());

            Polynomial result = Controller.addTwoPolynomials(polynomial1, polynomial2);

            for(Monomial monomial : result.getPolynomial())
                resultArea.append(monomial.toString());
        });
    }

    /**
     * method that adds an action listener to the subtract button
     */
    public void initializeSubtractButton()
    {
        this.addSubtractButtonPressedActionListener(e -> {
            resultArea.setText("");

            Polynomial polynomial1 = Controller.readPolynomial(firstPolynomialField.getText());
            Polynomial polynomial2 = Controller.readPolynomial(secondPolynomialField.getText());

            Polynomial result = Controller.subtractTwoPolynomials(polynomial1, polynomial2);

            for(Monomial monomial : result.getPolynomial())
                resultArea.append(monomial.toString());
        });
    }

    /**
     * method that adds an action listener to the multiply button
     */
    public void initializeMultiplyButton()
    {
        this.addMultiplyButtonPressedActionListener(e -> {
            resultArea.setText("");

            Polynomial polynomial1 = Controller.readPolynomial(firstPolynomialField.getText());
            Polynomial polynomial2 = Controller.readPolynomial(secondPolynomialField.getText());

            Polynomial result = Controller.multiplyTwoPolynomials(polynomial1, polynomial2);

            for(Monomial monomial : result.getPolynomial())
                resultArea.append(monomial.toString());
        });
    }

    /**
     * method that adds an action listener to the derivate button
     */
    public void initializeDerivateButton()
    {
        this.addDerivateButtonPressedActionListener(e -> {
            resultArea.setText("");

            Polynomial polynomial = Controller.readPolynomial(firstPolynomialField.getText());

            Polynomial result = Controller.derivatePolynomial(polynomial);

            for(Monomial monomial : result.getPolynomial())
                resultArea.append(monomial.toString());
        });
    }

    /**
     * method that adds an action listener to the integrate button
     */
    public void initializeIntegrateButton()
    {
        this.addIntegrateButtonPressedActionListener(e -> {
            resultArea.setText("");

            Polynomial polynomial = Controller.readPolynomial(firstPolynomialField.getText());

            Polynomial result = Controller.integratePolynomial(polynomial);

            for(Monomial monomial : result.getPolynomial())
                resultArea.append(monomial.toString());
            resultArea.append(" + C");
        });
    }

    public void addAddButtonPressedActionListener(ActionListener actionListener) {
        additionButton.addActionListener(actionListener);
    }

    public void addSubtractButtonPressedActionListener(ActionListener actionListener) {
        subtractionButton.addActionListener(actionListener);
    }

    public void addMultiplyButtonPressedActionListener(ActionListener actionListener) {
        multiplyButton.addActionListener(actionListener);
    }

    public void addDerivateButtonPressedActionListener(ActionListener actionListener) {
        derivateButton.addActionListener(actionListener);
    }

    public void addIntegrateButtonPressedActionListener(ActionListener actionListener) {
        integrateButton.addActionListener(actionListener);
    }
}
