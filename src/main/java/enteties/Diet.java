package enteties;

public class Diet {
    private String dietBulk;
    private String dietFit;
    private String dietFatLoss;

    public Diet() {
    }

    public Diet(String dietBulk, String dietFit, String dietFatLoss) {
        this.dietBulk = dietBulk;
        this.dietFit = dietFit;
        this.dietFatLoss = dietFatLoss;
    }

    public String getDietBulk() {
        return dietBulk;
    }

    public void setDietBulk(String dietBulk) {
        this.dietBulk = dietBulk;
    }

    public String getDietFit() {
        return dietFit;
    }

    public void setDietFit(String dietFit) {
        this.dietFit = dietFit;
    }

    public String getDietFatLoss() {
        return dietFatLoss;
    }

    public void setDietFatLoss(String dietFatLoss) {
        this.dietFatLoss = dietFatLoss;
    }
}
