import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverter extends JFrame implements ActionListener {
    // 1. Declare Components
    private JTextField usDollarField, canadianDollarField;
    private JButton convertButton;
    // Assuming a fixed conversion rate for simplicity
    private final double CONVERSION_RATE = 1.35; 

    public CurrencyConverter() {
        super("Convert US Dollars to Canadian Dollars");
        
        // 2. Set the main JFrame Layout to BorderLayout
        setLayout(new BorderLayout());

        // 3. Create the Center Panel (for Labels and Text Fields)
        // Use GridLayout(2, 2) to get 2 rows and 2 columns
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 15, 20)); // 10px horizontal and vertical gaps

        usDollarField = new JTextField(10);
        canadianDollarField = new JTextField(10);
        // The output field should not be editable by the user
        canadianDollarField.setEditable(false); 

        centerPanel.add(new JLabel("US Dollars:"));
        centerPanel.add(usDollarField);
        centerPanel.add(new JLabel("Canadian Dollars:"));
        centerPanel.add(canadianDollarField);
        
        // Add some empty border for padding/margin
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10,10, 10, 10)); 

        // 4. Create the South Panel (for the Convert button)
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align the button to the right
        convertButton = new JButton("Convert");
        convertButton.addActionListener(this); // Register the event handler
        southPanel.add(convertButton);

        // 5. Add the Panels to the main JFrame
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        // 6. JFrame 
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //(); // Size the frame to fit the components
        setVisible(true);
    }

    // 7. Event Handling for the 'Convert' button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            try {
                // Read input value from the US Dollars field
                double usd = Double.parseDouble(usDollarField.getText());
                
                // Perform the conversion calculation
                double cad = usd * CONVERSION_RATE;
                
                // Display the result (formatted to 2 decimal places)
                canadianDollarField.setText(String.format("%.2f", cad)); 
                
            } catch (NumberFormatException ex) {
                // Show an error if the user enters non-numeric data
                JOptionPane.showMessageDialog(this, 
                    "Please enter a valid number in the US Dollars field.", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
                canadianDollarField.setText(""); // Clear the output field
            }
        }
    }

    public static void main(String[] args) {
        // Best practice: Run the GUI code on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> new CurrencyConverter());
    }
}
