

package com.aurora.corona.model.casetime;

import java.util.List;

import lombok.Data;

@Data
public class CaseReport {
    private List<Cases_time_series> cases_time_series;

    private List<Statewise> statewise;

    private List<Tested> tested;

    public List<Cases_time_series> getCases_time_series() {
        return cases_time_series;
    }

    public List<Statewise> getStatewise() {
        return statewise;
    }

    public List<Tested> getTested() {
        return tested;
    }
}
