package com.github.NoctuaAstrum.ChemLib.core.asset.element.data;

/*import com.hypixel.hytale.codec.codecs.EnumCodec;*/

public enum UnitTypes {
    PBB,
    U;

    //public static EnumCodec<UnitTypes> CODEC = new EnumCodec<>(UnitTypes.class);

    public static String getInfoFor(UnitTypes unitType){
        switch (unitType){
            case PBB -> {
                return "Name: Parts per Billion ;Math: 1·10[-6] g/kg";
            }
            case U -> {
                return "Name: Atomical Unit ;Math: 1.66·10[-27] kg ; Additional: Is the Mass of a Neutron, Proton";
            }
            case null, default -> {
                return "No Info given";
            }
        }
    }
    public static UnitTypes getUnitFromString(String s){
        switch (s){
            case "pbb" -> {return PBB;}
            case "u" -> {return U;}
            case null, default -> {return null;}
        }
    }
}
