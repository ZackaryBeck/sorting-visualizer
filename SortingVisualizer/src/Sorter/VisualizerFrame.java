package Sorter;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class VisualizerFrame extends JFrame{

    private final int MAX_SIZE = 500;
    private final int MIN_SIZE = 10;
    private final int DEFAULT_SIZE = 250;
    private final String[] sortAlgs = {"Bubble", "Selection", "Insertion", "Merge", "Quick"};

    private int sizeModifier;

    private JPanel wrapper;
    private JPanel arrayWrapper;
    private JPanel buttonWrapper;
    private JPanel[] rectangles;
    private JButton startButton;
    private JButton shuffleButton;
    private JButton showArrayButton;
    private JComboBox<String> dropDownMenu;
    private JSlider sizeSlider;
    private JLabel sizeValue;
    private GridBagConstraints constraints;
    private JCheckBox incremental;

    public VisualizerFrame(){
        super("Sorting Visualization");

        wrapper = new JPanel();
        arrayWrapper = new JPanel();
        buttonWrapper = new JPanel();
        startButton = new JButton("Sort");
        shuffleButton = new JButton("Shuffle");
        showArrayButton = new JButton("Show Array");
        dropDownMenu = new JComboBox<String>();
        sizeSlider = new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_SIZE);
        sizeValue = new JLabel("Size: " + DEFAULT_SIZE);
        constraints = new GridBagConstraints();
        incremental = new JCheckBox("Incremental Array");
        rectangles = new JPanel[SortingVisualization.sizeOfArray];

        for(var s : sortAlgs){
            dropDownMenu.addItem(s);
        }

        arrayWrapper.setLayout(new GridBagLayout());
        wrapper.setLayout(new BorderLayout());

        constraints.insets = new Insets(0, 1, 0, 1);
        constraints.anchor = GridBagConstraints.SOUTH;

        startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                SortingVisualization.startSorting((String)dropDownMenu.getSelectedItem());
            }
        });
        shuffleButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                SortingVisualization.resetArray();
            }
        });
        showArrayButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                SortingVisualization.resetArray();
                
                JButton source = (JButton)e.getSource();
                source.setEnabled(false);
            }
        });
        incremental.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                SortingVisualization.incremental = incremental.isSelected();
            }
        });
        sizeSlider.setMinorTickSpacing(10);
        sizeSlider.setMajorTickSpacing(240);
        sizeSlider.setPaintTicks(true);

        sizeSlider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent arg0){
                sizeValue.setText("Size: " + Integer.toString(sizeSlider.getValue()) + " values");
                validate();
                SortingVisualization.sizeOfArray = sizeSlider.getValue();
            }
        });

        buttonWrapper.add(incremental);
        buttonWrapper.add(sizeValue);
        buttonWrapper.add(sizeSlider);
        buttonWrapper.add(dropDownMenu);
        buttonWrapper.add(showArrayButton);
        buttonWrapper.add(shuffleButton);
        buttonWrapper.add(startButton);

        wrapper.add(buttonWrapper, BorderLayout.NORTH);
        wrapper.add(arrayWrapper);

        add(wrapper);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        addComponentListener(new ComponentListener(){
            @Override
            public void componentResized(ComponentEvent c){
                sizeModifier = (int)(getHeight() * 0.9) / (rectangles.length);
            }
            @Override
            public void componentMoved(ComponentEvent c){
                // Do nothing
            }
            @Override
            public void componentShown(ComponentEvent c){
                // Do nothing
            }
            @Override
            public void componentHidden(ComponentEvent c){
                // Do nothing
            }
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
    public void preDrawArray(Integer[] panels){
        rectangles = new JPanel[SortingVisualization.sizeOfArray];
        arrayWrapper.removeAll();

        sizeModifier = (int)(getHeight() * 0.9) / (rectangles.length);
        for(int i = 0; i < SortingVisualization.sizeOfArray; i++){
            rectangles[i] = new JPanel();
            rectangles[i].setPreferredSize(new Dimension(SortingVisualization.blockWidth, panels[i] * sizeModifier));
            rectangles[i].setBackground(Color.BLUE);
            arrayWrapper.add(rectangles[i], constraints);
        }
        repaint();
        validate();
    }
    public void reDrawArray(Integer[] x){
        reDrawArray(x, -1);
    }
    public void reDrawArray(Integer[] x, int y){
        reDrawArray(x, y, -1);
    }
    public void reDrawArray(Integer[] x, int y, int z){
        reDrawArray(x, y, z, -1);
    }
    public void reDrawArray(Integer[] panels, int working, int comparing, int reading){
        arrayWrapper.removeAll();

        for(int i = 0; i < rectangles.length; i++){
            rectangles[i] = new JPanel();
            rectangles[i].setPreferredSize(new Dimension(SortingVisualization.blockWidth, panels[i] * sizeModifier));

            if(i == working){
                rectangles[i].setBackground(Color.CYAN);
            } else if(i == comparing){
                rectangles[i].setBackground(Color.ORANGE);
            } else if(i == reading){
                rectangles[i].setBackground(Color.CYAN);
            } else {
                rectangles[i].setBackground(Color.BLUE);
            }
            arrayWrapper.add(rectangles[i], constraints);
        }
        repaint();
        validate();
    }
}
