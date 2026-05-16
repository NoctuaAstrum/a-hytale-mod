package com.github.NoctuaAstrum.ChemLib.core.asset.element.enums;

//import com.hypixel.hytale.codec.codecs.EnumCodec;

public enum AbundancePlace{
    UNIVERSE,
    SUN,
    METEORITE,
    CRUST,
    WATER,
    STREAM,
    HUMAN;

    //public static EnumCodec<AbundancePlace> CODEC = new EnumCodec<>(AbundancePlace.class);

    public static AbundancePlace getAbundancePlaceFromString(String s) {
        switch (s.toLowerCase()){
            case "universe" -> {
                return UNIVERSE;
            }
            case "sun" -> {
                return SUN;
            }
            case "meteorite" -> {
                return METEORITE;
            }
            case "crust" -> {
                return CRUST;
            }
            case "water" -> {
                return WATER;
            }
            case "stream" -> {
                return STREAM;
            }
            case "human" -> {
                return HUMAN;
            }
            default -> {
                return null;
            }
        }
    }
}
