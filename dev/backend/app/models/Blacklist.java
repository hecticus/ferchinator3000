package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Blacklist extends BaseModel {
    @ManyToOne
    protected PromoContainer promoContainer;

    @Column()
    private String msisdn;

    public PromoContainer getPromoContainer() {
        return promoContainer;
    }

    public void setPromoContainer(PromoContainer promoContainer) {
        this.promoContainer = promoContainer;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
}