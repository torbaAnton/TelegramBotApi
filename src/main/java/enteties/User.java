package enteties;

public class User {
    private int chatId;
    private String name;
    private String realName;
    private String sex;
    private String age;
    private String trainProgramId;
    private String dietProgramId;
    private String height;
    private String weight;
    private String activity;

    public User() {
    }

    public User(int chatId, String name, String realName, String sex, String age, String trainProgramId, String dietProgramId, String height, String weight, String activity) {
        this.chatId = chatId;
        this.name = name;
        this.realName = realName;
        this.sex = sex;
        this.age = age;
        this.trainProgramId = trainProgramId;
        this.dietProgramId = dietProgramId;
        this.height = height;
        this.weight = weight;
        this.activity = activity;
    }

    public int getChatId() { return chatId; }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTrainProgramId() {
        return trainProgramId;
    }

    public void setTrainProgramId(String trainProgramId) {
        this.trainProgramId = trainProgramId;
    }

    public String getDietProgramId() {
        return dietProgramId;
    }

    public void setDietProgramId(String dietProgramId) {
        this.dietProgramId = dietProgramId;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
