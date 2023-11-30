package uz.gita.lesson8.mvp.models;

import uz.gita.lesson8.data.model.QuestionData;
import uz.gita.lesson8.mvp.contracts.MainContract;
import uz.gita.lesson8.repository.Repository;

public class MainModel implements MainContract.Model {
    private final Repository repository;

    public MainModel(){
        repository = Repository.getRepository();
    }


    @Override
    public QuestionData getCurrentQuestionData() {
        return repository.getCurrentQuestion();
    }

    @Override
    public int getCurrentQuestionPos() {
        return repository.getCurrentQuestionPos();
    }
}
