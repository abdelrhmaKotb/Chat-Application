package gov.iti.jets.enums;

public class EnumsUtil {
    public static Gender fromOrdinalToGender(int ordinal){
        return Gender.values()[ordinal];
    }

    public static Mood fromOrdinalToStatus(int ordinal){
        return Mood.values()[ordinal];
    }

}
