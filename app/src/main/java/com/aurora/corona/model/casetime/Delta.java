

package com.aurora.corona.model.casetime;

import lombok.Data;

@Data
public class Delta {
    private int active;

    private int confirmed;

    private int deaths;

    private int recovered;
}