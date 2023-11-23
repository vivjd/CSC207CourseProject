package view;

import interface_adapter.note.SaveNoteController;
import interface_adapter.note.NoteState;
import interface_adapter.note.NoteViewModel;

import interface_adapter.quiz.DisplayQuizzesViewModel;
import interface_adapter.quiz.DisplayQuizzesController;
import interface_adapter.quiz.DisplayQuizzesState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NoteView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "note";

    private final JTextArea userInputNote = new JTextArea("enter your notes here");
    private final JTextField userInputTitle = new JTextField("enter title here", 30);

    private final JButton save;
    private final JButton allQuizzes;

    private final JButton allNotes;

    private final JButton generateQuiz;


    private final SaveNoteController saveNoteController;
    private final NoteViewModel noteViewModel;
    private final DisplayQuizzesController displayQuizzesController;
    private final DisplayQuizzesViewModel displayQuizzesViewModel;

    public NoteView(SaveNoteController saveNoteController, NoteViewModel noteViewModel,
                    DisplayQuizzesController displayQuizzesController, DisplayQuizzesViewModel displayQuizzesViewModel) {
        this.saveNoteController = saveNoteController;
        this.noteViewModel = noteViewModel;
        this.displayQuizzesController = displayQuizzesController;
        this.displayQuizzesViewModel = displayQuizzesViewModel;
        displayQuizzesViewModel.addPropertyChangeListener(this);
        noteViewModel.addPropertyChangeListener(this);

        //creating title and note box
        Box textPanes = Box.createVerticalBox();
        userInputNote.setColumns(50);
        userInputNote.setLineWrap(true);
        userInputNote.setRows(20);
        LabelTextPanel noteInfo = new LabelTextPanel(
                new JLabel(NoteViewModel.NOTE_LABEL), userInputNote);
        LabelTextPanel titleInfo = new LabelTextPanel(
                new JLabel(NoteViewModel.TITLE_LABEL), userInputTitle);

        JScrollPane scrollbar = new JScrollPane(userInputNote,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        textPanes.add(titleInfo);
        textPanes.add(Box.createVerticalStrut(10));
        textPanes.add(noteInfo);
        textPanes.add(scrollbar);

        //creating buttons
        Box buttons = Box.createVerticalBox();
        allQuizzes = new JButton(NoteViewModel.ALL_QUIZZES_LABEL);
        save = new JButton(NoteViewModel.SAVE_LABEL);
        generateQuiz = new JButton(NoteViewModel.SUBMIT_BUTTON_LABEL);
        allNotes = new JButton(NoteViewModel.ALL_NOTES_LABEL);

        buttons.add(save);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(allQuizzes);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(allNotes);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(generateQuiz);

        save.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(save)) {
                            NoteState currentState = noteViewModel.getState();

                            saveNoteController.execute(
                                    currentState.getTitle(),
                                    currentState.getNote());

                            if (currentState.getEmptyNoteError().isEmpty()) {
                                showSavedPopup();
                            }
                        }
                    }
                }
        );

        allQuizzes.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(allQuizzes)) {
                           DisplayQuizzesState currentState = displayQuizzesViewModel.getState();
                           //TODO: change DAO and input table here
                           //currentState.setQuizzesTable();
                            displayQuizzesController.execute();
                        }
                    }
                }
        );

        userInputNote.addKeyListener(
                new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {
                        NoteState currentState = noteViewModel.getState();
                        String text = userInputNote.getText() + e.getKeyChar();
                        currentState.setNote(text);
                        noteViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        userInputTitle.addKeyListener(
                new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {
                        NoteState currentState = noteViewModel.getState();
                        String text = userInputTitle.getText() + e.getKeyChar();
                        currentState.setTitle(text);
                        noteViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        this.add(textPanes);
        this.add(buttons);

    }

    private void showSavedPopup(){
        JOptionPane.showMessageDialog(this, "Note Saved.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //TODO: perhaps this is the issue?
//        NoteState state = (NoteState) evt.getNewValue();
//        if (state.getEmptyNoteError() != null) {
//            JOptionPane.showMessageDialog(this, state.getEmptyNoteError());
//        }
//        Object newValue = evt.getNewValue();
//        if (newValue instanceof DisplayQuizzesState) {
//            DisplayQuizzesState state = (DisplayQuizzesState) newValue;
//            if (state.getEmptyQuizzesError() != null) {
//                JOptionPane.showMessageDialog(this, state.getEmptyQuizzesError());
//            }
//        } else if (newValue instanceof NoteState) {
//            NoteState state = (NoteState) newValue;
//            if (state.getEmptyNoteError() != null) {
//                JOptionPane.showMessageDialog(this, state.getEmptyNoteError());
//            }
//        }

    }
}
