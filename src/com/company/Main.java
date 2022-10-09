package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ReadInput reader = new ReadInput();
        int testNo = 4; //sunt 10 teste diferite
        try {
            testNo = testNo % 10;
            Antrenor a1 = reader.ReadAntrenor(testNo);
            Antrenor a2 = reader.ReadAntrenor(testNo + 1);

            Battle.ArenaBattle(a1, a2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
