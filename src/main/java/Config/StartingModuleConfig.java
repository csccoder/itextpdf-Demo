package Config;

import vo.SccQrqcStartingVO;

import java.util.ArrayList;
import java.util.List;

public class StartingModuleConfig extends ModuleConfig{
    private static final StartingModuleConfig moduleConfig = new StartingModuleConfig();

    private StartingModuleConfig() {
        super("启动", SccQrqcStartingVO.class);
        List<FieldConfig> fieldConfigList = new ArrayList<FieldConfig>();
        fieldConfigList.add(new FieldConfig("深南工单","orderName"));
        fieldConfigList.add(new FieldConfig("预警问题类别","questionType"));
        fieldConfigList.add(new FieldConfig("预警问题描述","questionDesc"));
        fieldConfigList.add(new FieldConfig("问题的影响范围","influence"));
        fieldConfigList.add(new FieldConfig("不良数","badNum"));
        fieldConfigList.add(new FieldConfig("元件位置","unitPosition"));
        fieldConfigList.add(new FieldConfig("是否停线","hasStop"));
        fieldConfigList.add(new FieldConfig("事件地点","site"));
        fieldConfigList.add(new FieldConfig("事件线体","lineName"));
        fieldConfigList.add(new FieldConfig("事件岗位","position"));

        setFieldConfigList(fieldConfigList);

        setFieldWidths(new float[]{8,20,10,10,10,10,10,10,10,10,10});
    }

    public static ModuleConfig getInstance(){
        return moduleConfig;
    }
}
