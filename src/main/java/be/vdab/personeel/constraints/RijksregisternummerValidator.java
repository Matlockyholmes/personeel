package be.vdab.personeel.constraints;

import be.vdab.personeel.forms.RijksregisternummerForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class RijksregisternummerValidator implements ConstraintValidator<Rijksregisternummer, RijksregisternummerForm> {

    @Override
    public void initialize(Rijksregisternummer rijksregisternummer) {

    }

    @Override
    public boolean isValid(RijksregisternummerForm rijksregisternummerForm, ConstraintValidatorContext context) {
        LocalDate geboorteDatum = rijksregisternummerForm.getGeboorteDatum();
        long rijksregisternummer = rijksregisternummerForm.getRijksregisternummer();
        long controlegetal = rijksregisternummer % 100;

        boolean correctJaar = (geboorteDatum.getYear() % 100) == (rijksregisternummer / 1000000000);
        boolean correctMaand = geboorteDatum.getMonthValue() == ((rijksregisternummer / 10000000) % 100);
        boolean correctDag = geboorteDatum.getDayOfMonth() == ((rijksregisternummer / 100000) % 100);

        if(correctDag && correctMaand && correctJaar && (geboorteDatum.getYear()) > 2000){
            return controlegetal == 97 - ((2000000000 + (rijksregisternummer / 100)) % 97);
        } else if(correctDag && correctMaand && correctJaar){
            return controlegetal == 97 - ((rijksregisternummer / 100) % 97);
        }
        return false;
    }
}
