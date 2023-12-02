package use_case.quiz.take_quiz;

import entity.Question;
import entity.Quiz;
import use_case.quiz.QuizDataAccessInterface;

import java.util.Iterator;

public class TakeQuizInteractor implements TakeQuizInputBoundary {

    final QuizDataAccessInterface quizDataAccessObject;
    final TakeQuizOutputBoundary takeQuizPresenter;
    private Iterator<Question<?>> questionIterator;

    public TakeQuizInteractor(QuizDataAccessInterface quizDataAccessObject, TakeQuizOutputBoundary takeQuizPresenter) {
        this.quizDataAccessObject = quizDataAccessObject;
        this.takeQuizPresenter = takeQuizPresenter;
    }


    @Override
    public void start(TakeQuizInputData takeQuizInputData) {
        Quiz quiz = quizDataAccessObject.getQuiz(takeQuizInputData.getTitle());
        TakeQuizOutputData outputData = new TakeQuizOutputData(quiz);

        takeQuizPresenter.prepareStartSuccessView(outputData);
    }

    @Override
    public void execute(TakeQuizInputData takeQuizInputData) {

        TakeQuizOutputData outputData = new TakeQuizOutputData();

        takeQuizPresenter.prepareExecuteSuccessView(outputData);
    }

    @Override
    public void next() {
//        Question<?> nextQuestion = questionIterator.next();
        TakeQuizOutputData outputData = new TakeQuizOutputData();

        takeQuizPresenter.prepareNextSuccessView(outputData);
    }

}
