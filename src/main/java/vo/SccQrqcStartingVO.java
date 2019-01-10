package vo;

public class SccQrqcStartingVO {
    private String orderName;
    private String questionType;
    private String questionDesc;
    private int influence;
    private int badNum;
    private String unitPosition;
    private String hasStop;
    private String site;
    private String lineName;
    private String position;

    public int getInfluence() {
        return influence;
    }

    public void setInfluence(int influence) {
        this.influence = influence;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public int getBadNum() {
        return badNum;
    }

    public void setBadNum(int badNum) {
        this.badNum = badNum;
    }

    public String getUnitPosition() {
        return unitPosition;
    }

    public void setUnitPosition(String unitPosition) {
        this.unitPosition = unitPosition;
    }

    public String getHasStop() {
        return hasStop;
    }

    public void setHasStop(String hasStop) {
        this.hasStop = hasStop;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * fieldConfigList.add(new FieldConfig("深南工单","orderName"));
     *         fieldConfigList.add(new FieldConfig("预警问题类别","questionType"));
     *         fieldConfigList.add(new FieldConfig("预警问题描述","questionDesc"));
     *         fieldConfigList.add(new FieldConfig("问题的影响范围","influence"));
     *         fieldConfigList.add(new FieldConfig("不良数","badNum"));
     *         fieldConfigList.add(new FieldConfig("元件位置","unitPostion"));
     *         fieldConfigList.add(new FieldConfig("是否停线","hasStop"));
     *         fieldConfigList.add(new FieldConfig("事件地点","site"));
     *         fieldConfigList.add(new FieldConfig("事件线体","lineName"));
     *         fieldConfigList.add(new FieldConfig("事件岗位","position"));
     */
}
