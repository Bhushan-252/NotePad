package com.company;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.*;

public class NotepadFormatItem extends JDialog {
    JPanel inside;
    NotepadDesign design;


    public NotepadFormatItem(NotepadDesign design) {
        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
        this.design = design;
        this.setTitle("Font");
        this.setResizable(false);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle
                (
                        JRootPane.FRAME
                );
        DefaultMetalTheme z = new DefaultMetalTheme() {
            @Override
            public ColorUIResource getWindowTitleForeground() {
                return new ColorUIResource(design.skinBackground);
            }


            @Override
            public ColorUIResource getWindowTitleBackground() {
                return new ColorUIResource(design.skinForeground);
            }

            @Override
            public ColorUIResource
            getMenuSelectedBackground() {
                return new ColorUIResource
                        (design.skinForeground);
            }

            @Override
            public ColorUIResource
            getMenuSelectedForeground() {
                return new ColorUIResource
                        (design.skinBackground);
            }

            //            start ActiveBumps
//            public ColorUIResource
//            getPrimaryControlHighlight()
//            {
//                return new ColorUIResource
//                         (design.skinForeground);
//            }
            public ColorUIResource
            getPrimaryControlDarkShadow() {
                return new ColorUIResource
                        (design.skinBackground);
            }

            public ColorUIResource
            getPrimaryControl() {
                return new ColorUIResource
                        (design.skinForeground);
            }


            //end ActiveBumps


            @Override
            public ColorUIResource
            getMenuBackground() {
                return new ColorUIResource
                        (design.skinBackground);
            }

            //
            @Override
            public ColorUIResource
            getMenuForeground() {
                return new ColorUIResource
                        (design.skinForeground);
            }
            //end ActiveBumps
        };

        MetalLookAndFeel.setCurrentTheme(z);
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);
        this.setBackground(design.skinBackground);
        this.setVisible(true);

    }

}

@SuppressWarnings("ALL")
class InsidePanel extends NotepadFormatItem {
    List fontBox, fontStyleBox, fontSizeBox;
    JTextArea textArea;
    TextField fontField, fontStyleField, fontSizeField;
    JLabel label;
    JButton buttonOk, buttonCancle;
    int fontStyle, fontSize;
    String fontFamily;
    String[] fontDetailsStore;
    NotepadDesign s;


    public InsidePanel(JTextArea textArea, String[] fontDetailsStore, NotepadDesign s) {
        super(s);
        this.textArea = textArea;
        this.s =s ;
        this.fontDetailsStore = fontDetailsStore;
        System.out.println(fontDetailsStore[0] + Integer.parseInt(fontDetailsStore[1]) + Integer.parseInt(fontDetailsStore[2]));
        fontFamily = fontDetailsStore[0];
        fontStyle = Integer.parseInt(fontDetailsStore[1]) < 0 ? 1 : Integer.parseInt(fontDetailsStore[1]);
        fontSize = Integer.parseInt(fontDetailsStore[2]);
        inside = new JPanel();
        inside.setSize(this.getWidth() - 10, this.getHeight() - 10);
        inside.setLayout(null);
        inside.setOpaque(true);
        inside.setBackground(s.skinBackground);
        inside.setForeground(s.skinForeground);
        this.add(inside);
        fontList();
        fontStyleList();
        fontSizeList();
        onClose();
        label = new JLabel("AbCdEf");
        label.setBounds(50, 200, 450, 200);
        label.setForeground(s.skinForeground);
        inside.add(label);
        sample();
        buttonDesings();
        buttonActions();


    }


    public void fontList() {
        fontField = new TextField();
        fontField.setBounds(20, 80, 150, 25);
        fontField.setBackground(s.skinBackground);
        fontField.setForeground(s.skinForeground);

        inside.add(fontField);

        fontBox = new List(5, false);
        fontBox.setBounds(20, 110, 150, 120);
        String Fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (String s:
             Fonts) {
            fontBox.add(s);
        }
        fontBox.setBackground(s.skinBackground);
        fontBox.setForeground(s.skinForeground);
        inside.add(fontBox);
        fontField.setText(
                fontFamily
        );
        this.fontBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                fontField.setText(fontBox.getSelectedItem());
                sample();
            }
        });


    }

    public void fontStyleList() {
        fontStyleField = new TextField();
        fontStyleField.setBounds(200, 80, 150, 25);
        fontStyleField.setBackground(s.skinBackground);
        fontStyleField.setForeground(s.skinForeground);

        inside.add(fontStyleField);

        fontStyleBox = new List(5, false);
        fontStyleBox.setBounds(200, 110, 150, 120);
        fontStyleBox.addItem("Regular");
        fontStyleBox.addItem("Bold");
        fontStyleBox.addItem("Italic");

        fontStyleBox.setBackground(s.skinBackground);
        fontStyleBox.setForeground(s.skinForeground);
        inside.add(fontStyleBox);
        fontStyleField.setText(
              fontStyleBox.getItem(fontStyle)
        );
        this.fontStyleBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                fontStyleField.setText(fontStyleBox.getSelectedItem());
                sample();
            }
        });
    }

    public void fontSizeList() {
        fontSizeField = new TextField();
        fontSizeField.setBounds(370, 80, 50, 25);
        fontSizeField.setBackground(s.skinBackground);
        fontSizeField.setForeground(s.skinForeground);
        inside.add(fontSizeField);

        fontSizeBox = new List(5, false);
        fontSizeBox.setBounds(370, 110, 50, 120);
        for (int i = 8; i <= 72; i++) {
            addItemsM(i);
            i++;
        }

        fontSizeBox.setBackground(s.skinBackground);
        fontSizeBox.setForeground(s.skinForeground);
        inside.add(fontSizeBox);
        fontSizeField.setText(
                String.valueOf(fontSize)
        );
        this.fontSizeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                fontSizeField.setText(fontSizeBox.getSelectedItem());
            }
        });
        this.fontSizeField.addTextListener(new TextListener() {
            @Override
            public void textValueChanged(TextEvent e) {
                int valueOfSize = 0;
                fontSizeField= (TextField)e.getSource();
                fontSizeField.setCaretPosition(fontSizeField.getText().length());
//                if (fontSizeField.getText().equals("8")){
//                    fontSizeField.setCaretPosition(fontSizeField.getText().length()+1);
//                }
               try {
                   valueOfSize = Integer.parseInt(fontSizeField.getText());
               }catch (NumberFormatException n){

                   valueOfSize = 8;

               }
                    if(valueOfSize > 128 || valueOfSize == 0 ){
                        valueOfSize = 128;
                    }

                fontSizeField.setText(String.valueOf(valueOfSize));
                sample();
            }
        });
    }

    public void addItemsM(int i) {
        fontSizeBox.addItem(String.valueOf(i));
    }

    public void buttonDesings() {
        buttonOk = new JButton("OK");
        buttonOk.setBounds(getWidth() - 200, getHeight() - 70, 80, 25);
        buttonOk.setBackground(design.skinBackground);
        buttonOk.setForeground(design.skinForeground);
        inside.add(buttonOk);
        buttonCancle = new JButton("CANCLE");
        buttonCancle.setBounds(getWidth() - 105, getHeight() - 70, 80, 25);
        buttonCancle.setBackground(design.skinBackground);
        buttonCancle.setForeground(design.skinForeground);
        inside.add(buttonCancle);
    }

    public void buttonActions() {
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fontDetailsStore[0] = fontField.getText().toString();
                fontDetailsStore[1] = String.valueOf(fontStyleBox.getSelectedIndex());
                fontDetailsStore[2] = fontSizeField.getText();

                textArea.setFont(new Font(fontDetailsStore[0],
                        Integer.parseInt(fontDetailsStore[1]),
                        Integer.parseInt(fontDetailsStore[2])));

                dispose();
                s.enable();
                s.requestFocus();

            }
        });
        buttonCancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setFont(new Font(fontFamily, fontStyle, fontSize));
                dispose();
                s.enable();
                s.requestFocus();


            }
        });
    }

    public void sample() {
        label.setFont(new Font(fontField.getText().toString(),
                fontStyleBox.getSelectedIndex(),
                Integer.parseInt(fontSizeField.getText())));
        textArea.setFont(new Font(fontField.getText().toString(),
                fontStyleBox.getSelectedIndex(),
                Integer.parseInt(fontSizeField.getText())));

    }

    private void onClose() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                textArea.setFont(new Font(fontFamily, fontStyle, fontSize));
                s.enable();
                s.requestFocus();
            }
        });
    }


}
