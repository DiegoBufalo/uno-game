package com.usjt.a3.unogame.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NameGenerator {
    private List<String> names = new ArrayList<String>();

    public NameGenerator() {
        super();
        this.names.addAll(Arrays.asList("BotBot", "RoboNome", "ByteBuddy", "MechMente", "ChipChatter",
                "Circuitinho", "DataDrone", "BinaryBuddy", "PixelPals", "ByteBotic",
                "ByteBabble", "CogniCode", "InfoBot", "LogicLink", "MegaMind",
                "NanoNomes", "QuantumQuirk", "SparkBot", "TechTicker", "WaveWizard",
                "ZenZap", "ChatChap", "ByteBlitz", "RoboRiddle", "PulseBot", "EchoElf",
                "MindMingle", "AlgoAmigo", "DataDroid", "MechMate", "CodeCrafty", "AIAlly",
                "CerebroChat", "LogicLoom", "BotBuzz", "ByteBurst", "NanoNatter", "BitBuddy",
                "InfoImpulse", "WaveWhiz", "SparkySynth", "ZenZing", "ChatCircuit", "RoboRamble",
                "PulsePuppet", "EchoEnigma", "MindMixer", "AlgoArtist", "DataDynamo", "MechMystery"));
    }

    public String getNomeRandom() {
        Random random = new Random();
        int randomIndex = random.nextInt(names.size() - 1);
        String randomName = this.names.get(randomIndex);
        this.names.remove(randomIndex);

        return randomName;
    }

}