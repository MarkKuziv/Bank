package com.example.bank.dto;

import com.example.bank.Enum.CardEnum;

public class UserDto {
    private long identificationCode;
    private CardEnum cardEnum;
    private long summa;

    public UserDto(long identificationCode, CardEnum cardEnum, long summa) {
        this.identificationCode = identificationCode;
        this.cardEnum = cardEnum;
        this.summa = summa;
    }

    public long getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(long identificationCode) {
        this.identificationCode = identificationCode;
    }

    public CardEnum getCardEnum() {
        return cardEnum;
    }

    public void setCardEnum(CardEnum cardEnum) {
        this.cardEnum = cardEnum;
    }

    public long getSumma() {
        return summa;
    }

    public void setSumma(long summa) {
        this.summa = summa;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "identificationCode=" + identificationCode +
                ", cardEnum=" + cardEnum +
                ", summa=" + summa +
                '}';
    }
}
