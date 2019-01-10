package Config;

import converter.Converter;
import vo.SccQrqcStopVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StopModuleConfig extends ModuleConfig{
    private static final ModuleConfig moduleConfig = new StopModuleConfig();
    private StopModuleConfig(){
        super("停/复线记录", SccQrqcStopVO.class);
        List<FieldConfig> fieldConfigList = new ArrayList<FieldConfig>();
        fieldConfigList.add(new FieldConfig("升级为work cell QRQC","workcell"));
        fieldConfigList.add(new FieldConfig("停/复线","stop"));
        fieldConfigList.add(new FieldConfig("产生原因分析人","producer"));
        fieldConfigList.add(new FieldConfig("流出原因分析人","outuser"));
        fieldConfigList.add(new FieldConfig("提交人","operator"));
        fieldConfigList.add(new FieldConfig("提交时间","creationDate",new Converter(){

            public String doConverter(Object rawValue) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                return dateFormat.format(rawValue);
            }
        }));

        setFieldConfigList(fieldConfigList);

        setFieldWidths(new float[]{5,20,10,10,10,10,10});
    }

    public static ModuleConfig getInstance(){
        return moduleConfig;
    }
}
