package com.company;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("ALL")
public class NotepadDesign extends JFrame {
    public JMenuItem optionNew, optionOpen, optionSave, optionSaveAs, optionExit;
    public JMenuItem optionCut, optionCopy, optionPaste, optionSelectAll;
    public JMenuItem optionFont;
    public JTextArea textArea;
    public JScrollPane scrollPane;
    public JCheckBoxMenuItem wrapWord;
    Font font;
    public String[] fontDetailsStore = {"Arial","0","22"};
    public Color skinBackground =new Color(0,0,0);
    public Color skinForeground =new Color(0, 225, 0);
    private JMenu fileMenu, editMenu, formatMenu, aboutMenu;

    public NotepadDesign() throws HeadlessException {
        this.setSize(500, 500);
        this.setTitle("Untitled - Notepad");
        createTextArea();
        menuDesign();

        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle
                (
                        JRootPane.FRAME
                );
        DefaultMetalTheme z = new DefaultMetalTheme() {
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



    }


    /*========================================================== Start The MenuBar Design ===================================================== */
    //This Method Create Design of MenuBar And All Items.
    public final void menuDesign() {

        JMenuBar jMenuBar = new JMenuBar();
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

    public final void createTextArea() {
        textArea = new JTextArea();

//        textArea.setLineWrap(true);

        textArea.setFont(new Font("Verdana", Font.PLAIN, 24));
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

        optionCut = new JMenuItem("CUT                       Ctrl+X");
        editMenu.add(optionCut);

        optionCopy = new JMenuItem("COPY                    Ctrl+C");
        editMenu.add(optionCopy);

        optionPaste = new JMenuItem("PASTE                  Ctrl+V");
        editMenu.add(optionPaste);

        optionSelectAll= new JMenuItem("SELECT-ALL       Ctrl+A");
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
        aboutMenu.setFocusPainted(false);
        aboutMenu.setSize(100, 100);

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
}
