package km.arfawy.android.ussd.models;

public abstract class Country {

    protected String name;
    protected String[] starts;
    protected int minDigit;
    protected String recharge_request;
    protected String prepaid_recharge;
    protected String transfert;

    public abstract boolean isNationalNumber(String number);
    public abstract String simpleNumber(String number);

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String[] getStarts() {
        return starts;
    }
    public void setStarts(String[] starts) {
        this.starts = starts;
    }
    public int getMinDigit() {
        return minDigit;
    }
    public void setMinDigit(int minDigit) {
        this.minDigit = minDigit;
    }
    public String getRecharge_request() {
        return recharge_request;
    }
    public void setRecharge_request(String recharge_request) {
        this.recharge_request = recharge_request;
    }
    public String getPrepaid_recharge() {
        return prepaid_recharge;
    }
    public void setPrepaid_recharge(String prepaid_recharge) {
        this.prepaid_recharge = prepaid_recharge;
    }
    public String getTransfert() {
        return transfert;
    }
    public void setTransfert(String transfert) {
        this.transfert = transfert;
    }
}
