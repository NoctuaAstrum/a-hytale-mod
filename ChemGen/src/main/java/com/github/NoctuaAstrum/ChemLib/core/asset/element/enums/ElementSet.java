package com.github.NoctuaAstrum.ChemLib.core.asset.element.enums;

//import com.hypixel.hytale.codec.codecs.EnumCodec;

public enum ElementSet{
    NONMETAL,
    NOBLEGAS,
    ALKALIMETAL,
    ALKALINEEEARTHMETAL,
    METALLOID,
    HALOGEN,
    METAL,
    TRANSITIONMETAL,
    LANTHANOIDE,
    ACTINOIDE,
    UNKNOWN;

    //public static EnumCodec<ElementSet> CODEC = new EnumCodec<>(ElementSet.class);

    public static ElementSet getElementSetFromString(String s) {
        switch (s.toLowerCase()){
            case "nonmetal" -> {
                return NONMETAL;
            }
            case "noblegas" -> {
                return NOBLEGAS;
            }
            case "alkalimetal" -> {
                return ALKALIMETAL;
            }
            case "alkalineearthmetal" -> {
                return ALKALINEEEARTHMETAL;
            }
            case "metalloid" -> {
                return METALLOID;
            }
            case "halogen" -> {
                return HALOGEN;
            }
            case "metal" -> {
                return METAL;
            }
            case "transitionmetal" -> {
                return TRANSITIONMETAL;
            }
            case "lanthanoide" -> {
                return LANTHANOIDE;
            }
            case "actinoide" -> {
                return ACTINOIDE;
            }
            default -> {
                return UNKNOWN;
            }
        }
    }
}
