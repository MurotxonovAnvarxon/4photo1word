package uz.gita.lesson8.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import uz.gita.lesson8.R;
import uz.gita.lesson8.data.localStorge.LocalStorage;
import uz.gita.lesson8.data.model.QuestionData;

public  class Repository {
     static Repository repository;
    private final LocalStorage storage;
     List<QuestionData> questions;

    private Repository() {
        storage = LocalStorage.getStorage();
        questions = new ArrayList<>();
        initQuestions();
    }

    public static Repository getRepository() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    public void saveCurrentQuestionPos(int pos) {
        storage.saveQuestionPos(pos);
    }

    public int getCurrentQuestionPos() {
        return storage.getQuestionPos();
    }

    public QuestionData getCurrentQuestion() {
        return questions.get(getCurrentQuestionPos());
    }

    private void initQuestions() {
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.pic1_1,
                        R.drawable.pic1_2,
                        R.drawable.pic1_3,
                        R.drawable.pic1_4),
                "WORK",
                generateVariant("WORK")
        ));
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.pic2_1,
                        R.drawable.pic2_2,
                        R.drawable.pic2_3,
                        R.drawable.pic2_4),
                "SPEC",
                generateVariant("SPEC"))
        );

        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.pic3_1,
                        R.drawable.pic3_2,
                        R.drawable.pic3_3,
                        R.drawable.pic3_4),
                "SWIM",
                generateVariant("SWIM"))
        );
//        questions.add(new QuestionData(
//                Arrays.asList(
//                        R.drawable.pic4_1,
//                        R.drawable.pic4_2,
//                        R.drawable.pic4_3,
//                        R.drawable.pic4_4),
//                "PANDA",
//                generateVariant("PANDA")
//        ));
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.pizza1,
                        R.drawable.pizza2,
                        R.drawable.pizza3,
                        R.drawable.pizza4),
                "PIZZA",
                generateVariant("PIZZA"))
        );

        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.rabbit1,
                        R.drawable.rabbit2,
                        R.drawable.rabbit3,
                        R.drawable.rabbit4),
                "RABBIT",
                generateVariant("RABBIT"))
        );
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.ruler1,
                        R.drawable.ruler2,
                        R.drawable.ruler3,
                        R.drawable.ruler4),
                "RULER",
                generateVariant("RULER")
        ));
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.shark1,
                        R.drawable.shark2,
                        R.drawable.shark3,
                        R.drawable.shark4),
                "SHARK",
                generateVariant("SHARK"))
        );

        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.teacher1,
                        R.drawable.teacher2,
                        R.drawable.teacher3,
                        R.drawable.teacher4),
                "TEACHER",
                generateVariant("TEACHER"))
        );
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.doctor1,
                        R.drawable.doctor2,
                        R.drawable.doctor3,
                        R.drawable.doctor4),
                "DOCTOR",
                generateVariant("DOCTOR")
        ));
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.eagle1,
                        R.drawable.eagle2,
                        R.drawable.eagle3,
                        R.drawable.eagle4),
                "EAGLE",
                generateVariant("EAGLE"))
        );

        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.eraser1,
                        R.drawable.eraser2,
                        R.drawable.eraser3,
                        R.drawable.eraser4),
                "ERASER",
                generateVariant("ERASER"))
        );
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.farmer1,
                        R.drawable.farmer2,
                        R.drawable.farmer3,
                        R.drawable.farmer4),
                "FARMER",
                generateVariant("FARMER")
        ));
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.fish1,
                        R.drawable.fish2,
                        R.drawable.fish3,
                        R.drawable.fish4),
                "FISH",
                generateVariant("FISH"))
        );

        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.goose1,
                        R.drawable.goose2,
                        R.drawable.goose3,
                        R.drawable.goose4),
                "GOOSE",
                generateVariant("GOOSE"))
        );
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.season1,
                        R.drawable.season2,
                        R.drawable.season3,
                        R.drawable.season4),
                "SEASON",
                generateVariant("SEASON"))
        );
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.window1,
                        R.drawable.window2,
                        R.drawable.window3,
                        R.drawable.window4),
                "WINDOW",
                generateVariant("WINDOW"))
        );
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.clothes1,
                        R.drawable.clothes2,
                        R.drawable.clothes3,
                        R.drawable.clothes4),
                "CLOTHES",
                generateVariant("CLOTHES"))
        );
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.train1,
                        R.drawable.train2,
                        R.drawable.train3,
                        R.drawable.train4),
                "TRAIN",
                generateVariant("TRAIN"))
        );
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.lamp1,
                        R.drawable.lamp2,
                        R.drawable.lamp3,
                        R.drawable.lamp4),
                "LAMP",
                generateVariant("LAMP"))
        );
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.door1,
                        R.drawable.door2,
                        R.drawable.door5,
                        R.drawable.door4),
                "DOOR",
                generateVariant("DOOR"))
        );
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.books1,
                        R.drawable.books2,
                        R.drawable.books3,
                        R.drawable.books4),
                "BOOKS",
                generateVariant("BOOKS"))
        );
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.mouse1,
                        R.drawable.mouse2,
                        R.drawable.mouse3,
                        R.drawable.mouse4),
                "MOUSE",
                generateVariant("MOUSE"))
        );
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.smoke1,
                        R.drawable.smoke2,
                        R.drawable.smoke3,
                        R.drawable.smoke),
                "SMOKE",
                generateVariant("SMOKE"))
        );
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.gas1,
                        R.drawable.gas2,
                        R.drawable.gas3,
                        R.drawable.gas4),
                "GAS",
                generateVariant("GAS"))
        );
        questions.add(new QuestionData(
                Arrays.asList(
                        R.drawable.wood1,
                        R.drawable.wood2,
                        R.drawable.wood3,
                        R.drawable.wood4),
                "WOOD",
                generateVariant("WOOD"))
        );


    }

    private String generateVariant(String answer) {
        List<Character> variant = new ArrayList<>(14);
        List<Character> letters = Arrays.asList(
                'A', 'B', 'C', 'D', 'E', 'F', 'G',
                'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                'V', 'W', 'X', 'Y', 'Z'
        );
        for (int i = 0; i < answer.length(); i++) {
            variant.add(answer.charAt(i));
        }
        Random random = new Random();
        for (int i = answer.length(); i < 12; i++) {
            variant.add(letters.get(random.nextInt(26)));
        }
        Collections.shuffle(variant);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < variant.size(); i++) {
            sb.append(variant.get(i));
        }
        return sb.toString();
    }

    public int levelCount() {
        return questions.size();
    }
}
