package beans;

import javax.mail.Address;
import java.util.Date;

/**
 * Created by tgranbacka on 15-05-12.
 */
public class MessageDTO {
    private Arendetyp arendetyp = Arendetyp.SAKNAS;
    private String elwisArendeId;
    private String ahArendeId;

    private String avsandare;
    private Date datumMottaget;
    private Date datumSkickat;
    private String arende;
    private Object content;

    public String getAvsandare() {
        return avsandare;
    }

    public void setAvsandare(String avsandare) {
        this.avsandare = avsandare;
    }

    public Date getDatumMottaget() {
        return datumMottaget;
    }

    public void setDatumMottaget(Date datumMottaget) {
        this.datumMottaget = datumMottaget;
    }

    public Date getDatumSkickat() {
        return datumSkickat;
    }

    public void setDatumSkickat(Date datumSkickat) {
        this.datumSkickat = datumSkickat;
    }

    public String getArende() {
        return arende;
    }

    public void setArende(String arende) {
        this.arende = arende;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Arendetyp getArendetyp() {
        return arendetyp;
    }

    public void setArendetyp(Arendetyp arendetyp) {
        this.arendetyp = arendetyp;
    }

    public String getElwisArendeId() {
        return elwisArendeId;
    }

    public void setElwisArendeId(String elwisArendeId) {
        this.elwisArendeId = elwisArendeId;
    }

    public String getAhArendeId() {
        return ahArendeId;
    }

    public void setAhArendeId(String ahArendeId) {
        this.ahArendeId = ahArendeId;
    }
}
