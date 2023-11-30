package uz.gita.lesson8.mvp.models;

import uz.gita.lesson8.data.model.QuestionData;
import uz.gita.lesson8.mvp.contracts.GameContract;
import uz.gita.lesson8.repository.Repository;

public class GameModel implements GameContract.Model {
    private final Repository repository;

    public GameModel() {
        repository = Repository.getRepository();
    }

    @Override
    public QuestionData getQuestionData() {
        if (repository.getCurrentQuestionPos()==repository.levelCount()){
            repository.saveCurrentQuestionPos(0);
        }
        return repository.getCurrentQuestion();
    }

    @Override
    public int getCurrentQuestionPos() {
        return repository.getCurrentQuestionPos();
    }

    @Override
    public void saveCurrentQuestionPos(int pos) {
        repository.saveCurrentQuestionPos(pos);
    }

}
