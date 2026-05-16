package com.github.NoctuaAstrum.ChemLib.core.data;


import java.util.HashMap;

public record HazardData(String[] h, String[] p, String[] hazard, String[] ghs, String[] adr, HashMap<String,String> nfpa) {
}
