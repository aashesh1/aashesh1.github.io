package com.bb2.aasheshkumar.mobilebanking.enums;

public enum GenderEnum {

    FEMALE{
        @Override
        public String getAccountEnumId() {
            return "F";
        }

        @Override
        public String getAccountEnumValue() {
            return "Female";
        }
    },
    MALE{
        @Override
        public String getAccountEnumId() {
            return "M";
        }

        @Override
        public String getAccountEnumValue() {
            return "Male";
        }
    };

    public abstract String getAccountEnumId();
    public abstract String getAccountEnumValue();

}
