package com.nero.socialmedia.analysis.instagram.domain;

import javax.persistence.*;

@Entity(name = "setting")
public class Setting {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "multiple", nullable = false)
    private boolean multiple;

    @Column(name = "field_name", nullable = false)
    private String fieldName;

    @Column(name = "value", nullable = false)
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static SettingBuilder builder() {
        return new SettingBuilder();
    }

    public static class SettingBuilder {
        private final Setting setting = new Setting();

        private SettingBuilder() {}

        public SettingBuilder multiple(boolean multiple) {
            setting.setMultiple(multiple);
            return this;
        }

        public SettingBuilder fieldName(String fieldName) {
            setting.setFieldName(fieldName);
            return this;
        }

        public SettingBuilder value(String value) {
            setting.setValue(value);
            return this;
        }

        public Setting build() {
            return setting;
        }
    }
}
