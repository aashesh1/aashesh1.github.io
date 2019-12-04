package com.bb2.aasheshkumar.mobilebanking.enums;

public enum AccountEnum {

    SAVING{
        @Override
        public Integer getAccountEnumId() {
            return 1;
        }

        @Override
        public String getAccountEnumValue() {
            return "Saving";
        }
    },
    CURRENT{
        @Override
        public Integer getAccountEnumId() {
            return 2;
        }

        @Override
        public String getAccountEnumValue() {
            return "Current";
        }
    };

    public abstract Integer getAccountEnumId();
    public abstract String getAccountEnumValue();
}
