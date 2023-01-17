import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class IPFinder extends JFrame {
    private JButton buttonIP, buttonMAC;
    private JLabel label;
    private JPanel panel;

    public IPFinder() {
        // Create the buttons
        buttonIP = new JButton("Get IP");
        buttonIP.addActionListener(new ButtonIPListener());
        
        buttonMAC = new JButton("Get MAC");
        buttonMAC.addActionListener(new ButtonMACListener());

        // Create the label
        label = new JLabel("");

        // Create the panel and add the buttons and label
        panel = new JPanel();
        panel.add(buttonIP);
        panel.add(buttonMAC);
        panel.add(label);

        // Add the panel to the frame
        add(panel);

        // Set the frame properties
        setTitle("IP and MAC Finder");
        setSize(400, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class ButtonIPListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                // Call the ipify API
                URL url = new URL("https://api.ipify.org/");
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String publicIP = reader.readLine();
                
                // Get the local host
                InetAddress localHost = InetAddress.getLocalHost();
                String privateIP = localHost.getHostAddress();

                // Update the label with the

                                // Update the label with the IP addresses
                label.setText("Your public IP is: " + publicIP + " and your private IP is: " + privateIP);
            } catch (Exception ex) {
                label.setText("Error: " + ex.getMessage());
            }
        }
    }
    
    private class ButtonMACListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                // Get the user's MAC address
                InetAddress address = InetAddress.getLocalHost();
                NetworkInterface ni = NetworkInterface.getByInetAddress(address);
                byte[] mac = ni.getHardwareAddress();

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                String macAddress = sb.toString();

                // Update the label with the MAC address
                label.setText("Your MAC is: " + macAddress);
            } catch (Exception ex) {
                label.setText("Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new IPFinder();
    }
}
