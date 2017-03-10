package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 20-Dec-16.
 */
public class CheckScheduleParameters {
    private int schedulePeriod;
    private boolean enableScheduler;

    public CheckScheduleParameters(int schedulePeriod, boolean enableScheduler) {
        this.schedulePeriod = schedulePeriod;
        this.enableScheduler = enableScheduler;
    }

    public CheckScheduleParameters() {
    }

    public int getSchedulePeriod() {
        return schedulePeriod;
    }

    public void setSchedulePeriod(int schedulePeriod) {
        this.schedulePeriod = schedulePeriod;
    }

    public boolean isEnableScheduler() {
        return enableScheduler;
    }

    public void setEnableScheduler(boolean enableScheduler) {
        this.enableScheduler = enableScheduler;
    }
}
