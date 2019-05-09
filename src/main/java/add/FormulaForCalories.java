package add;

import enteties.User;
import login.DBManager;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;

public final class FormulaForCalories {
    private static Map<String, Double> activityToCoefficient = new HashMap<String, Double>() {

        {
            put(("Минимальный"), 1.2);
            put(("Низкий"), 1.375);
            put(("Средний"), 1.55);
            put(("Высокий"), 1.725);
            put(("Очень высокий"), 1.9);
        }
    };

    private FormulaForCalories() {
    }

    public static double calculateCalorie(Message message){
        DBManager dbManager = DBManager.getInstance();
        User user = dbManager.getUser(Math.toIntExact(message.getChatId()));
        return calculateCalorieIntake(user);
    }

    private static double calculateCalorieIntake(User user) {
        String sex = user.getSex();
        String activity = user.getActivity();
        double age = extractAverageNumber(user.getAge());
        double height = extractAverageNumber(user.getHeight());
        double weight = extractAverageNumber(user.getWeight());

        double firstAddendCoef;
        double secondAddendCoef;
        double thirdAddendCoef;
        double finalAddenCoef;
        if (sex.equals("Мужской")){
            firstAddendCoef = 13.4;
            secondAddendCoef = 4.8;
            thirdAddendCoef = 5.7;
            finalAddenCoef = 88.36;
        } else {
            firstAddendCoef = 9.2;
            secondAddendCoef = 3.1;
            thirdAddendCoef = 4.3;
            finalAddenCoef = 447.6;
        }
        double firstAdden = firstAddendCoef * weight;
        double secondAdden = secondAddendCoef * height;
        double thirdAdden = thirdAddendCoef * age;
        double BMR = finalAddenCoef + firstAdden + secondAdden - thirdAdden;
        return activityToCoefficient.get(activity) * BMR;
    }

    private static double extractAverageNumber(String field){
        String[] arrayOfStrings = field.split("-");
        int firstNumber = Integer.parseInt(arrayOfStrings[0]);
        int secondNumber = Integer.parseInt(arrayOfStrings[1]);
        return (firstNumber + secondNumber) / 2;
    }
    }
