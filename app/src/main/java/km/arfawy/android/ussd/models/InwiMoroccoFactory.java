package km.arfawy.android.ussd.models;

public class InwiMoroccoFactory implements CountryFactory {
    @Override
    public Country getInstance() {
        return new InwiMoroccoCountry();
    }
}
