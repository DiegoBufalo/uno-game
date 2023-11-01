export class NameGenerator {
    private names: string[] = [];

    constructor() {

        this.names = [
            "BotBot", "RoboNome", "ByteBuddy", "MechMente", "ChipChatter",
            "Circuitinho", "DataDrone", "BinaryBuddy", "PixelPals", "ByteBotic",
            "ByteBabble", "CogniCode", "InfoBot", "LogicLink", "MegaMind",
            "NanoNomes", "QuantumQuirk", "SparkBot", "TechTicker", "WaveWizard",
            "ZenZap", "ChatChap", "ByteBlitz", "RoboRiddle", "PulseBot", "EchoElf",
            "MindMingle", "AlgoAmigo", "DataDroid", "MechMate", "CodeCrafty", "AIAlly",
            "CerebroChat", "LogicLoom", "BotBuzz", "ByteBurst", "NanoNatter", "BitBuddy",
            "InfoImpulse", "WaveWhiz", "SparkySynth", "ZenZing", "ChatCircuit", "RoboRamble",
            "PulsePuppet", "EchoEnigma", "MindMixer", "AlgoArtist", "DataDynamo", "MechMystery",
        ];
    }

    getNomeRandom(): string {
        const randomIndex = Math.floor(Math.random() * this.names.length);
        const randomName = this.names[randomIndex];
        this.names.splice(randomIndex, 1);

        return randomName;
    }
}