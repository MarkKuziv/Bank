package com.example.bank.dto;

public class CardDto {
    private int fromCard;
    private int toCard;
    private int summa;

    public CardDto(int fromCard, int toCard, int summa) {
        this.fromCard = fromCard;
        this.toCard = toCard;
        this.summa = summa;
    }

    public int getFromCard() {
        return fromCard;
    }

    public void setFromCard(int fromCard) {
        this.fromCard = fromCard;
    }

    public int getToCard() {
        return toCard;
    }

    public void setToCard(int toCard) {
        this.toCard = toCard;
    }

    public int getSumma() {
        return summa;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }
}
