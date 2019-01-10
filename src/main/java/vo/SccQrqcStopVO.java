package vo;

import java.util.Date;

public class SccQrqcStopVO {
    private String workcell;
    private String stop;
    private String producer;
    private String outuser;
    private String operator;
    private Date creationDate;

    public String getWorkcell() {
        return workcell;
    }

    public void setWorkcell(String workcell) {
        this.workcell = workcell;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getOutuser() {
        return outuser;
    }

    public void setOutuser(String outuser) {
        this.outuser = outuser;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
