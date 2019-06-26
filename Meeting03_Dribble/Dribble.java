package Meeting03_Dribble;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
/*
    MatFis pertemuan 3
    Collision between parabolically moving object against wall
    TODO:
     0. Review about elastic and inelastic collisions. What happened when you change the coefficient of resistution (COR)?
     1. Add more balls with different colors, sizes, and velocities
     2. Create UI to add new balls and delete some instances
     3. Add COR field to the UI, so user can choose between using different COR than the default or not
     4. Turn all balls into linearly moving ones (apply Newton's first law here).
     5. Create diagonal walls and modify the calculation to adjust with diagonal walls
     6. Create UI to customize the walls
 */

public class Dribble {
    private JFrame frame;
    private DrawingArea drawingArea;

    private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<Ball> balls = new ArrayList<>();

    public Dribble() {
        //configure the main canvas
        frame = new JFrame("Dribbling Balls");
        button = new JButton("Add Ball");
        button.setBounds(50, 100, 95, 30);
        frame.add(button);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        JFrame frame = new JFrame("Ball Creator & Killer")
        label = new JLabel("Ball Atribute");     
        textField = new JTextField("Ball Radius");
        textField.setBounds(10, 30, 95, 30);
        textField = new JTextField("Ball Position X");
        textField.setBounds(10, 70, 95, 30);
        textField = new JTextField("Ball Position Y");
        textField.setBounds(10, 110, 95, 30);
        textField = new JTextField("Ball Velocity X");
        textField.setBounds(10, 150, 95, 30);
        textField = new JTextField("Ball Velocity Y");
        textField.setBounds(10, 190, 95, 30);
        textField = new JTextField("Ball Color");
        textField.setBounds(10, 230, 95, 30);
        
        JFrame corFrame = new JFrame("Coefficient of Restitution");
        corFrame.setBounds(350,30,150,30);
        f.add(corFrame);

        JLabel info = new JLabel("If empty, COR will remain at 0.7");
        info.setBounds(430,70,400,50);
        f.add(info);

        JLabel info2 = new JLabel("COR change will take effect on the next ball");
        info2.setBounds(403,90,430,50);
        f.add(info2);

        JTextField corValue = new JTextField();
        corValue.setBounds(550,30,100,30);
        f.add(corValue);
        
        frame.add(ballRadiusLabel);
        frame.add(ballPosXLabel);
        frame.add(ballPosYLabel);
        frame.add(ballVelXLabel);
        frame.add(ballVelYLabel);
        frame.add(ballColorLabel);

        JButton button = new JButton("Generate Ball");
        button.setBounds(300, 300, 100, 20);

        JButton def = new JButton("Spawn default balls");
        def.setBounds(420,300,200,20);
        def.addActionListener(new DefaultSpawn());

        JButton kill = new JButton("Kill a ball");  //Ball killer
        kill.setBounds(300,300,100,20);
        kill.addActionListener(new CustomActionListener());
       
        //Listener to generate a new ball
        b.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(corValue.getText().isEmpty());   //Test output to detect COR field change
                //If COR field is filled
                if (corValue.getText().isEmpty() == false)
                {
                    newCor = Double.parseDouble(corValue.getText());    //Get new COR value from the COR field
                    if (newCor != 0.7 && newCor >= 0.0 && newCor <= 100.0)
                    {
                        cor = newCor;   //Set current COR with the new COR
                    }
                }
                double ballCounter = balls.size();
                System.out.println(ballCounter);
                Color color2 = Color.black; 
                double radius = Double.parseDouble(radius.getText());
                double posX = Double.parseDouble(positionX.getText());
                double posY = Double.parseDouble(positionY.getText());
                double velX = Double.parseDouble(velocityX.getText());
                double velY = Double.parseDouble(velocityY.getText());
                String col = colorSet.getText();
                switch(col.toLowerCase())
                {
                    case "black":
                        color2 = color.black;
                        break;
                    case "blue":
                        color2 = color.blue;
                        break;
                    case "red":
                        color2 = color.red;
                        break;
                    case "green":
                        color2 = color.green;
                        break;
                    case "yellow":
                        color2 = color.yellow;
                        break;
                    case "orange":
                        color2 = color.orange;
                        break;    
                    case "gray":
                        color2 = color.gray;
                        break;
                }

                balls.add(new Ball(radius, posX, posY, velX, velY, color2));
            }
        };
        
        f.add(b);
        f.add(kill);
        f.add(def);

        f.setSize(700,400);
        f.setLayout(null);
        f.setVisible(true);
       
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // create the walls
        createWalls();
        
        drawingArea = new DrawingArea(frame.getWidth(), frame.getHeight(), balls, walls);
        frame.add(drawingArea);
        drawingArea.start();
    }

    private void createWalls() {
        // vertical wall must be defined in clockwise direction
        // horizontal wall must be defined in counter clockwise direction

        walls.add(new Wall(1300, 100, 50, 100, Color.black));	// horizontal top
        walls.add(new Wall(50, 600, 1300, 600, Color.black));  // horizontal bottom
        walls.add(new Wall(1300, 100, 1300, 600, Color.black));  // vertical right
        walls.add(new Wall(50, 600, 50, 100, Color.black));  // vertical left
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(Dribble::new);
    }
}
