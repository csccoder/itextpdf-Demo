package config;

import vo.SccQrqcStartingVO;

import java.util.ArrayList;
import java.util.List;

public class StartingBaseModuleConfig extends BaseModuleConfig {
    private static final StartingBaseModuleConfig moduleConfig = new StartingBaseModuleConfig();

    private StartingBaseModuleConfig() {
        super("启动", SccQrqcStartingVO.class);
        //第一行标题
        List<FieldConfig> firstHeaderFieldConfig = new ArrayList<FieldConfig>();
        firstHeaderFieldConfig.add(new FieldConfig("某某工单","orderName"));
        firstHeaderFieldConfig.add(new FieldConfig("问题的影响范围","influence"));
        firstHeaderFieldConfig.add(new FieldConfig("不良数","badNum"));
        firstHeaderFieldConfig.add(new FieldConfig("元件位置","unitPosition"));
        firstHeaderFieldConfig.add(new FieldConfig("是否停线","hasStop"));
        firstHeaderFieldConfig.add(new FieldConfig("事件地点","site"));
        firstHeaderFieldConfig.add(new FieldConfig("事件线体","lineName"));
        firstHeaderFieldConfig.add(new FieldConfig("事件岗位","position"));
        //第二行标题
        List<FieldConfig> secondHeaderFieldConfig = new ArrayList<FieldConfig>();
        secondHeaderFieldConfig.add(new FieldConfig("预警问题类别","questionType"));
        secondHeaderFieldConfig.add(new FieldConfig("预警问题描述","questionDesc",7));

        setFieldConfigList(new List[]{firstHeaderFieldConfig,secondHeaderFieldConfig});

        setFieldWidths(new float[]{7,13,11,7,9,7,10,10,10});
    }

    public static BaseModuleConfig getInstance(){
        return moduleConfig;
    }
}
