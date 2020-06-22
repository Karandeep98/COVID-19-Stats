

package com.aurora.corona.model.casetime;

import lombok.Data;

@Data
public class Cases_time_series {
    private String dailyconfirmed;

    private String dailydeceased;

    private String dailyrecovered;

    public String getDailyconfirmed() {
        return dailyconfirmed;
    }

    public String getDailydeceased() {
        return dailydeceased;
    }

    public String getDailyrecovered() {
        return dailyrecovered;
    }

    public String getDate() {
        return date;
    }

    public String getTotalconfirmed() {
        return totalconfirmed;
    }

    public String getTotaldeceased() {
        return totaldeceased;
    }

    public String getTotalrecovered() {
        return totalrecovered;
    }

    private String date;

    private String totalconfirmed;

    private String totaldeceased;

    private String totalrecovered;
}









