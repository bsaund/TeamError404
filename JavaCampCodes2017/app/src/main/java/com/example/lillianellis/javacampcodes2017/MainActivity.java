package com.example.lillianellis.javacampcodes2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     int playerLevel = 1;
     String playerName = "Lillian";
     double playerHealth = 10.0;
     Scanner = userInput = new Scanner(System.in);
     Random randomGenerator = new Random();
     int rockDamage;
        System.out.println("Enter your name");
        playerName = userInput.nextLine();
        System.out.println("Welcome" + playerName + "to this puzzle game!");
        System.out.println("Level 1");
        Enter ();
    }


public static void Enter () {
    System.out.println("< press enter to continue >");
    try {
        System.in.read();
    } catch (Exception e) {
    }
}
    public static void Enter2 () {
        //System.out.println("< press enter to continue >");
        try {
            System.in.read();
        } catch (Exception e) {
        }

    }