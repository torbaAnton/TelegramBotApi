package enteties;

public class Program {
    private String bulk;
    private String fatLoss;
    private String fit;

    public Program() {
    }

    public Program(String bulk, String fatLoss, String fit) {
        this.bulk = bulk;
        this.fatLoss = fatLoss;
        this.fit = fit;
    }

    public String getBulk() {
        return bulk;
    }

    public void setBulk(String bulk) {
        this.bulk = bulk;
    }

    public String getFatLoss() {
        return fatLoss;
    }

    public void setFatLoss(String fatLoss) {
        this.fatLoss = fatLoss;
    }

    public String getFit() {
        return fit;
    }

    public void setFit(String fit) {
        this.fit = fit;
    }
}
