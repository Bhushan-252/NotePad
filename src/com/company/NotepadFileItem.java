package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class NotepadFileItem extends NotepadDesign implements ActionListener {
    String fileName;
    String FileAddress;
    String title = "Untitled - Notepad";

    NotepadFileItem() {
        super();
        optionNew.addActionListener(this);
        optionOpen.addActionListener(this);
        optionSave.addActionListener(this);
        optionSaveAs.addActionListener(this);
        optionExit.addActionListener(this);
        new NotepadEditItem(this,title);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                checkOnExit();
            }
        });
        checkChange();

    }

    public void newFile() {
        if (saveOrNot()){
            save();
        }
        this.textArea.setText("");
        title = "Untitled - Notepad";
        this.setTitle(title);
        fileName = null;
        FileAddress = null;

    }

    public void open() {
        if (saveOrNot()){
            save();
        }
        FileDialog fd = new FileDialog(this, "open", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fileName = fd.getFile();
            FileAddress = fd.getDirectory();
            title = fileName + " - Notepad";
            this.setTitle(title);
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(FileAddress + fileName));
            this.textArea.setText("");
            String line;
            while ((line = br.readLine()) != null) {
                this.textArea.append(line + "\n");
            }
            br.close();
        } catch (Exception e) {
            System.out.println("file not open");
            e.printStackTrace();

        }
    }

    public void save() {
        if (fileName == null) {
            saveAs();

        } else {
            try {
                FileWriter fw = new FileWriter(FileAddress + fileName);
                fw.write(this.textArea.getText());
                title = fileName + " - Notepad";
                this.setTitle(title);

                fw.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void exit() {
        checkOnExit();


    }

    public void  saveAs() {
        FileDialog fd = new FileDialog(this, "save", FileDialog.SAVE);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            fileName = fd.getFile();
            FileAddress = fd.getDirectory();
            title = fileName + " - Notepad";
            this.setTitle(title);

        }
        try {
            FileWriter fw = new FileWriter(FileAddress + fileName);
            fw.write(this.textArea.getText());
            fw.close();

        } catch (Exception e) {
            System.out.println("something is wrong");
            e.printStackTrace();
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "new" -> this.newFile();
            case "open" -> this.open();
            case "save" -> this.save();
            case "saveAs" -> this.saveAs();
            case "exit" -> this.exit();
            default -> throw new IllegalStateException("Unexpected value: " + command);
        }
    }

    public void checkChange() {
        this.textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                NotepadFileItem.super.setTitle("*" + title);
            }
        });


    }

    public void checkOnExit() {
        int userInputOnExit = 3;
        if (this.getTitle().startsWith("*")) {
            userInputOnExit = JOptionPane.showConfirmDialog(this, "You want to save file");
        } else {
            System.exit(0);
        }
        if (userInputOnExit == 0) {
            save();
            System.exit(0);
        } else if (userInputOnExit == 1) {
            System.exit(0);
        }

    }
    public boolean saveOrNot(){
        int userInputOnExit;
        if (this.getTitle().startsWith("*")) {
            userInputOnExit = JOptionPane.showConfirmDialog(this, "You want to save file");
            if (userInputOnExit == 0) {
                return true;
            } else if (userInputOnExit == 1) {
               return false;
            }

        }
        return false;
    }

}
