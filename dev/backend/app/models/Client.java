package models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Client extends BaseModel {
    @Column()
    private String msisdn;
    @Column()
    private String promoId;
    @Column()
    private String carrier;
    @Column()
    private String country;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
