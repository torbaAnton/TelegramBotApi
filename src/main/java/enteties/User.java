package enteties;

public class User {
    private int chatId;
    private String name;
    private String realName;
    private String sex;
    private String age;
    private String trainProgramId;
    private String dietProgramId;

    public User() {
    }

    public User(int chatId, String name, String realName, String sex, String age, String trainProgramId, String dietProgramId) {
        this.chatId = chatId;
        this.name = name;
        this.realName = realName;
        this.sex = sex;
        this.age = age;
        this.trainProgramId = trainProgramId;
        this.dietProgramId = dietProgramId;
    }

    public int getChatId() {
        return chatId;
    }

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
}
