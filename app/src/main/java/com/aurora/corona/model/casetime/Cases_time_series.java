/*
 * Corona Stats
 * Copyright (C) 2020, Rahul Kumar Patel <auroraoss.dev@gmail.com>
 *
 * Aurora Store is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 * Corona Stats is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Aurora Store.  If not, see <http://www.gnu.org/licenses/>.
 */

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









