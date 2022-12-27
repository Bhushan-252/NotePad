package com.company;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

@SuppressWarnings("ALL")
public class NotepadDesign extends JFrame {
    public JMenuItem optionNew, optionOpen, optionSave, optionSaveAs, optionExit;
    public JMenuItem optionCut, optionCopy, optionPaste, optionSelectAll;
    public JMenuItem optionFont;
    public JMenuItem optionSkin;
    public JTextArea textArea;
    public JScrollPane scrollPane;
    JMenuBar jMenuBar;
    public JCheckBoxMenuItem wrapWord;
    DefaultMetalTheme z;
    Font font;
    public String[] fontDetailsStore = new String[3];
    public Color skinBackground =new Color(255, 255, 255);
    public Color skinForeground =new Color(0, 0, 0);
    private JMenu fileMenu, editMenu, formatMenu, aboutMenu;

    public NotepadDesign() throws HeadlessException, IOException {
        this.setSize(500, 500);
        this.setTitle("Untitled - Notepad");
        createTextArea();
        menuDesign();
        this.setUndecorated(true);
        changeTheme();

    }

    public void changeTheme(){
        this.setLocationRelativeTo(null);

        this.getRootPane().setWindowDecorationStyle
                (
                        JRootPane.FRAME
                );
        z = new DefaultMetalTheme() {
            public ColorUIResource
            getWindowTitleBackground() {
                return new ColorUIResource
                        (skinForeground);
            }

            @Override
            public ColorUIResource getWindowTitleForeground() {
                return new ColorUIResource(skinBackground);
            }

            @Override
            public ColorUIResource
            getMenuSelectedBackground() {
                return new ColorUIResource
                        (skinForeground);
            }

            @Override
            public ColorUIResource
            getMenuSelectedForeground() {
                return new ColorUIResource
                        (skinBackground);
            }

            //            start ActiveBumps
//            public ColorUIResource
//            getPrimaryControlHighlight()
//            {
//                return new ColorUIResource
//                        (skinForeground);
//            }
            public ColorUIResource
            getPrimaryControlDarkShadow() {
                return new ColorUIResource
                        (skinBackground);
            }

            public ColorUIResource
            getPrimaryControl() {
                return new ColorUIResource
                        (skinForeground);
            }



            //end ActiveBumps


            @Override
            public ColorUIResource
            getMenuBackground() {
                return new ColorUIResource
                        (skinBackground);
            }
            //
            @Override
            public ColorUIResource
            getMenuForeground() {
                return new ColorUIResource
                        (skinForeground);
            }

        };

        MetalLookAndFeel.setCurrentTheme(z);
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);
        this.setVisible(true);

        // Menu Bar
        jMenuBar.setBorderPainted(true);
        jMenuBar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(skinForeground, 1),
                BorderFactory.createEmptyBorder()));
        jMenuBar.setOpaque(true);
        jMenuBar.setBackground(skinBackground);
        // This Desing For Text Area

        textArea.setCaretColor(skinForeground);
        textArea.setSelectedTextColor(skinBackground);
        textArea.setSelectionColor(skinForeground);
        textArea.setBackground(skinBackground);
        textArea.setForeground(skinForeground);
    }

    /*========================================================== Start The MenuBar Design ===================================================== */
    //This Method Create Design of MenuBar And All Items.
    public final void menuDesign() {

        jMenuBar = new JMenuBar();
        jMenuBar.setSize(100, 100);
        jMenuBar.setBorderPainted(true);
        jMenuBar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(skinForeground, 1),
                BorderFactory.createEmptyBorder()));
        jMenuBar.setOpaque(true);
        jMenuBar.setBackground(skinBackground);

        this.setJMenuBar(jMenuBar);
        fileDesign();
        editDesign();
        formatDesign();
        aboutDesign();
        jMenuBar.add(fileMenu);
        jMenuBar.add(editMenu);
        jMenuBar.add(formatMenu);
        jMenuBar.add(aboutMenu);
//        new JMenuBar().add(editMenu);


    }

    public final void createTextArea() throws IOException {
        textArea = new JTextArea();

//        textArea.setLineWrap(true);
        try{
        FileInputStream stream = new FileInputStream("test.obj");
        ObjectInputStream inputStream = new ObjectInputStream(stream);
                font = (Font) inputStream.readObject();
                changeFont(font);
                inputStream.close();

        }
        catch(Exception e) {
            e.printStackTrace();
            font = new Font("Verdana", Font.PLAIN, 12);
            changeFont(font);
        }
        textArea.setFont(font);


        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textArea.setCaretColor(skinForeground);
        textArea.setSelectedTextColor(skinBackground);
        textArea.setSelectionColor(skinForeground);
        textArea.setBackground(skinBackground);
        textArea.setForeground(skinForeground);
//        textArea.setBorder(BorderFactory.createMatteBorder(0,1,1,1,Color.green));

        this.add(scrollPane);
        zoom();

//        this.add(textArea);
    }

    /* ********************************* MenuBars -> File -> Menu And Item *************************************  */
    // This Method Make GUI Design of  MenuBars -> File.
    public final void fileDesign() {

        //Create object of JMenu for FileMenu
        fileMenu = new JMenu("File");
        fileMenu.setFocusPainted(false);
        fileMenu.setSize(100, 100);


        // Create and Add FileMenu -> Item
        //add 1st Item
        optionNew = new JMenuItem("NEW");
        optionNew.setOpaque(true);
        optionNew.setSize(90, 90);
        optionNew.setActionCommand("new");
        fileMenu.add(optionNew);
        //add 2nd Item
        optionOpen = new JMenuItem("OPEN");
        optionOpen.setOpaque(true);

        optionOpen.setActionCommand("open");

        fileMenu.add(optionOpen);
        //add 3rd Item
        optionSave = new JMenuItem("SAVE");
        optionSave.setOpaque(true);
        optionSave.setActionCommand("save");


        fileMenu.add(optionSave);
        //add 4th Item
        optionSaveAs = new JMenuItem("SAVE AS ");
        optionSaveAs.setOpaque(true);
        optionSaveAs.setActionCommand("saveAs");
        fileMenu.add(optionSaveAs);
        // Add separator
        fileMenu.addSeparator();
        //add 5th Item
        optionExit = new JMenuItem("EXIT");
        optionExit.setActionCommand("exit");
        fileMenu.add(optionExit);


    }

    /* ********************************* MenuBars -> Edit -> Menu And Item *************************************  */
    // This Method Make GUI Design of  MenuBars -> Edit
    public final void editDesign() {
        editMenu = new JMenu("Edit");
        editMenu.setFocusPainted(false);
        editMenu.setSize(100, 100);

        optionCut = new JMenuItem("CUT");
        editMenu.add(optionCut);

        optionCopy = new JMenuItem("COPY ");
        editMenu.add(optionCopy);

        optionPaste = new JMenuItem("PASTE ");
        editMenu.add(optionPaste);

        optionSelectAll= new JMenuItem("SELECT-ALL ");
        editMenu.add(optionSelectAll);


    }

    /* ********************************* MenuBars -> Format -> Menu And Item *************************************  */
    // This Method Make GUI Design of  MenuBars -> Format
    public final void formatDesign() {
        formatMenu = new JMenu("Format");
        formatMenu.setFocusPainted(false);
        formatMenu.setSize(100, 100);

        // Create and Add FormatMenu -> Item
        //add 1st Item
        wrapWord = new JCheckBoxMenuItem("Word Wrap");
        wrapWord.setState(false);
        wrapWord.setOpaque(true);
        wrapWord.addItemListener(e -> {
            if (wrapWord.getState()) {
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
            } else {
                textArea.setLineWrap(false);
                textArea.setWrapStyleWord(false);
            }
        });
        formatMenu.add(wrapWord);
        //add 2nd Item
        optionFont = new JMenuItem("Font...");
        optionFont.setOpaque(true);
        optionFont.addActionListener(e -> {
            disable();
            openFormatBox();

        });
        formatMenu.add(optionFont);
    }

    private void openFormatBox() {
        new InsidePanel(textArea,  fontDetailsStore, this);
    }


    /* ********************************* MenuBars -> About -> Menu And Item *************************************  */
    // This Method Make GUI Design of  MenuBars -> About
    public final void aboutDesign() {
        aboutMenu = new JMenu("About");
        JColorChooser chooser = new JColorChooser();
        aboutMenu.setFocusPainted(false);
        aboutMenu.setSize(100, 100);

        optionSkin = new JMenu("Skins");
        changeThemeOption(optionSkin, new JMenuItem("Black And White"), Color.WHITE,Color.BLACK);
        changeThemeOption(optionSkin, new JMenuItem("Green And Black"), Color.GREEN,Color.BLACK);
        changeThemeOption(optionSkin, new JMenuItem("White And Cyan"),Color.cyan,Color.WHITE);
        changeThemeOption(optionSkin, new JMenuItem("Default"), Color.BLACK, Color.WHITE);
        changeThemeOption(optionSkin, new JMenuItem("Custom"), chooser);



        System.out.println();
//        System.out.println(chooser.getColor());

        aboutMenu.add(optionSkin);

    }


    final void changeThemeOption(JMenuItem Parent,JMenuItem item,Color ForeCol,Color BackCol){

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSkinForeground(ForeCol);
                setSkinBackground(BackCol);
                changeTheme();
            }
        });
        Parent.add(item);

    }
    final void changeThemeOption(JMenuItem Parent,JMenuItem item,JColorChooser chooser){


        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    try {
        Color ForeCol = chooser.showDialog(NotepadDesign.this, "Choose Foreground Color", Color.BLACK);
        Color BackCol = chooser.showDialog(NotepadDesign.this, "Choose Background Color", Color.WHITE);
        int dx = ForeCol.getBlue() + ForeCol.getRed() + ForeCol.getGreen();
        int dy = BackCol.getBlue() + BackCol.getGreen() + BackCol.getGreen();
        if (dy != dx && Math.abs(dy-dx) >= 300   ) {
            setSkinForeground(ForeCol);
            setSkinBackground(BackCol);
            changeTheme();
        } else {
            JOptionPane.showMessageDialog(chooser, "The both color can't be Same or Not Visibal to Eye", "Color Error", JOptionPane.WARNING_MESSAGE);
        }
    }catch(Exception es){

    }
            }
        });
        Parent.add(item);

    }


    public void setSkinForeground(Color skinForeground) {
        this.skinForeground = skinForeground;
    }

    public void setSkinBackground(Color skinBackground) {
        this.skinBackground = skinBackground;
    }

    /*======================================================== End of MenuBar Design ======================================================== */

    /*
     * key bord action and shortcuts
     * */

    public void zoom() {
        textArea.registerKeyboardAction(e -> {
            font = textArea.getFont();

            if (font.getSize()<=100)
                textArea.setFont(new Font(font.getFamily(), font.getStyle(), font.getSize() + 2));
        }, KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.CTRL_DOWN_MASK)
                , JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        textArea.registerKeyboardAction(e -> {
                    font = textArea.getFont();

                    if (font.getSize()>=8)
                        textArea.setFont(new Font(font.getFamily(), font.getStyle(), font.getSize() - 2));
                }, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK)
                , JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);



    }
    private final void changeFont(Font font){
        fontDetailsStore[0] = font.getFamily();
        fontDetailsStore[1] = String.valueOf(font.getStyle());
        fontDetailsStore[2] = String.valueOf(font.getSize());
    }
}
