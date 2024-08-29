import crud.view.GUI;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {
        System.out.println(java.time.LocalDate.now());

        JFrame frame = new JFrame("Crud Form");
        frame.setContentPane(new GUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(1200,750);
    }
}
