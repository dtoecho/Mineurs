package com.newlecmineursprj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Color {
    private long id;
    private String korName;
    private String engName;
    private String hexCode;
}
