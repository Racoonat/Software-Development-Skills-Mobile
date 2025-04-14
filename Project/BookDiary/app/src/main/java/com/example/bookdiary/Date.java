package com.example.bookdiary;

import java.io.Serializable;

public class Date implements Serializable {
    private Integer day;
    private Integer month;
    private Integer year;

    // Constructor que acepta int (para que se use con DatePickerDialog)
    public Date(Integer day, Integer month, Integer year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Constructor que acepta String (como lo tenías anteriormente)
    public Date(String dia, String mes, String anio) {
        this.day = dia.isEmpty() ? null : Integer.parseInt(dia);
        this.month = mes.isEmpty() ? null : Integer.parseInt(mes);
        this.year = anio.isEmpty() ? null : Integer.parseInt(anio);
    }

    // Método para obtener la fecha con el formato adecuado
    public String getFormatted() {
        if (year == null) return "Fecha no especificada";
        if (month == null) return String.valueOf(year);
        if (day == null) return String.format("%02d/%d", month, year);
        return String.format("%02d/%02d/%d", day, month, year);
    }

    // Métodos getters para acceder a los valores de la fecha si es necesario
    public Integer getDay() {
        return day;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    @Override
    public String toString() {
        return getFormatted();
    }
}