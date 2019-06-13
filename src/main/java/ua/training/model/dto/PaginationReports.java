package ua.training.model.dto;

import ua.training.model.entities.Report;

import java.util.List;

public class PaginationReports {
    private List<Report> list; // tax return must contain id, category, lastUpdate, status; for inspectors also username
    private int firstNumber;
    private int lastNumber;
    private int total;

    public List<Report> getList() {
        return list;
    }

    public void setList(List<Report> list) {
        this.list = list;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(int lastNumber) {
        this.lastNumber = lastNumber;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
