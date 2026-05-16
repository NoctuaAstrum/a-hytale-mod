package com.github.NoctuaAstrum.ChemLib.core.data;

public record TemperatureData(float value, float[] range, boolean bool, String unit, StandardValueData atSymbol, float deviation) {
}
