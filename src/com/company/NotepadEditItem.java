package com.company;

public class NotepadEditItem {

    public NotepadEditItem(NotepadDesign edit, String title) {
        try {
            edit.optionCut.addActionListener(ae -> edit.textArea.cut());
            edit.optionCopy.addActionListener(ae -> {
                edit.textArea.copy();
                edit.setTitle("*" + title);
            });
            edit.optionPaste.addActionListener(le -> {
                edit.textArea.paste();
                edit.setTitle("*" + title);
            });
            edit.optionSelectAll.addActionListener(e -> {
                edit.textArea.selectAll();
                edit.setTitle("*" + title);
            });
//        edit.textArea.addCaretListener(new CaretListener() {
//            public void caretUpdate(CaretEvent ce) {
//                System.out.println("All text: " + textArea.getText());
//                if (textArea.getSelectedText() != null)
//                    System.out.println("Selected text: " +textArea.getSelectedText());
//                else
//                    System.out.println("Selected text: ");
//            }
//        });
        } catch (Exception e) {
            System.out.println("Error is " + e);
        }

    }

}
