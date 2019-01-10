package Config;

import converter.Converter;

import java.util.List;

public abstract class ModuleConfig {
    private String moduleName;
    private Class clazz;
    private List<FieldConfig> fieldConfigList;
    private float[] fieldWidths;

    public ModuleConfig(String moduleName,Class clazz) {
        this.moduleName = moduleName;
        this.clazz = clazz;
    }

    public ModuleConfig(String moduleName, List<FieldConfig> fieldConfigList) {
        this.moduleName = moduleName;
        this.fieldConfigList = fieldConfigList;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public List<FieldConfig> getFieldConfigList() {
        return fieldConfigList;
    }

    public void setFieldConfigList(List<FieldConfig> fieldConfigList) {
        this.fieldConfigList = fieldConfigList;
    }

    public float[] getFieldWidths() {
        return fieldWidths;
    }

    public void setFieldWidths(float[] fieldWidths) {
        this.fieldWidths = fieldWidths;
    }

    public class FieldConfig{
        private String exportName;
        private String fieldName;
        private Converter converter;

        public FieldConfig(String exportName, String fieldName, Converter converter) {
            this.exportName = exportName;
            this.fieldName = fieldName;
            this.converter = converter;
        }

        public FieldConfig(String exportName, String fieldName) {
            this.exportName = exportName;
            this.fieldName = fieldName;
        }

        public String getExportName() {
            return exportName;
        }

        public void setExportName(String exportName) {
            this.exportName = exportName;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public Converter getConverter() {
            return converter;
        }

        public void setConverter(Converter converter) {
            this.converter = converter;
        }
    }

}
