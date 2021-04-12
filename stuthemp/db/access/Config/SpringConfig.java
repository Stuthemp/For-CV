package stuthemp.db.access.Config;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SpringConfig implements WebMvcConfigurer {


    public static Connection connection;

    public static DataSource dataSource(){

            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Uni","postgres", "postgres");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/Uni");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        return dataSource;
    }

    public static void createNumbersOnlyField(JTextField jTextField){
        jTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char a = e.getKeyChar();
                if (!Character.isDigit(a)
                        && (a != '.')
                        && (a != '\b')) {
                    e.consume();
                }
            }
        });
    }

    public static void createOnlyTwoGendersField(JTextField jTextField){
        jTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char a = e.getKeyChar();
                if (!Character.isDigit(a)
                        && (a != 'М')
                        && (a != 'Ж')) {
                    e.consume();
                }
            }
        });
    }

    public static void setDialogText(JTextField jTextField){
        jTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
        jTextField.setHorizontalAlignment(JTextField.LEFT);
    }

    public static JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
//    public static JdbcTemplate jdbcTemplate(){
//        return new JdbcTemplate(dataSource());
//    }
}
