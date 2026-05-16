package com.github.NoctuaAstrum.ChemLib.core.asset.element.enums;

//import com.hypixel.hytale.codec.codecs.EnumCodec;

public enum OrbitalShellBlocks {
    S,
    P,
    D,
    F;

    //public static EnumCodec<OrbitalShellBlocks> CODEC = new EnumCodec<>(OrbitalShellBlocks.class);

    public static OrbitalShellBlocks getOrbitalShellBlockFromString(String s){
        switch (s.toLowerCase()){
            case "s" -> {return S;}
            case "p" -> {return P;}
            case "d" -> {return D;}
            case "f" -> {return F;}
            default -> {return null;}
        }
    }
}
