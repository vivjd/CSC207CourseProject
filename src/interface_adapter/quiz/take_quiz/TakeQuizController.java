package interface_adapter.quiz.take_quiz;

import org.bson.types.ObjectId;
import use_case.quiz.take_quiz.TakeQuizInputBoundary;
import use_case.quiz.take_quiz.TakeQuizInputData;

import java.util.List;

public class TakeQuizController {

    final TakeQuizInputBoundary takeQuizInteractor;
    public TakeQuizController(TakeQuizInputBoundary takeQuizInteractor) {
        this.takeQuizInteractor = takeQuizInteractor;
    }

    public void execute(String title){
        System.out.println(title);
        TakeQuizInputData takeQuizInputData = new TakeQuizInputData(title);
        takeQuizInteractor.execute(takeQuizInputData);
    }

    public void start(String title) {
        System.out.println(title);
        TakeQuizInputData takeQuizInputData = new TakeQuizInputData(title);
        takeQuizInteractor.start(takeQuizInputData);
    }
}
