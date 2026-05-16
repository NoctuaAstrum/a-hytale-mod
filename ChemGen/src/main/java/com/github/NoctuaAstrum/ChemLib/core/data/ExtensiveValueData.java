package com.github.NoctuaAstrum.ChemLib.core.data;

public record ExtensiveValueData(String label, boolean bool, double value, String unit, float[] range, StandardValueData[] condition) {
}
