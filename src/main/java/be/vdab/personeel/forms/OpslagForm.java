package be.vdab.personeel.forms;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class OpslagForm {
    @Min(1)
    private final BigDecimal opslag;

    public OpslagForm(@Min(1) BigDecimal opslag) {
        this.opslag = opslag;
    }

    public BigDecimal getOpslag() {
        return opslag;
    }
}
