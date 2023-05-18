package com.gcu.baima.Enum;

/**
 * 试课时间段枚举类
 *
 * @author xiu
 * @create 2023-05-18 15:17
 */
public enum TrialLessonPeriod {
    moring(0, "09:00-11:00"), mid(1, "13:00-17:00"), night(2, "20:00-22:00");
    private Integer value;
    private String tiemDuration;

    private TrialLessonPeriod(Integer value, String tiemDuration) {
        this.value = value;
        this.tiemDuration = tiemDuration;
    }

    public static TrialLessonPeriod valueOf(Integer value) {
        switch (value) {
            case 0:
                return moring;
            case 1:
                return mid;
            case 2:
                return night;
            default:
                return null;
        }
    }

    public Integer value() {
        return this.value;
    }

    public int getValue() {
        return value;
    }

    public String getTiemDuration() {
        return tiemDuration;
    }

    public void setTiemDuration(String tiemDuration) {
        this.tiemDuration = tiemDuration;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
