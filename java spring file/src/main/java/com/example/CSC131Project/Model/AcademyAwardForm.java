package com.example.CSC131Project.Model;

import javax.validation.constraints.*;

public class AcademyAwardForm
{
    @NotNull
    @Min(1928)
    @Max(2020)
    private Integer year;

    public Integer getYear()
    {
        return year;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }
}
