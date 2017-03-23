package km.arfawy.android.ussd.models;

public class Country {
    private String name;
    private String[] starts;
    private int minDigit;

    public Country(String name, int minDigit, String... starts) {
        this.name = name;
        this.starts = starts;
        this.minDigit = minDigit;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String[] getStarts() {
        return starts;
    }

    public int getMinDigit() {
        return minDigit;
    }

    public void setMinDigit(int minDigit) {
        this.minDigit = minDigit;
    }

    public void setStarts(String[] starts) {
        this.starts = starts;
    }
}
