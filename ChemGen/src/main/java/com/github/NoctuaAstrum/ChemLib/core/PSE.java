package com.github.NoctuaAstrum.ChemLib.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.NoctuaAstrum.ChemLib.core.asset.element.ElementAsset;
import com.github.NoctuaAstrum.ChemLib.core.asset.element.data.*;
import com.github.NoctuaAstrum.ChemLib.core.asset.element.enums.*;
import com.github.NoctuaAstrum.ChemLib.core.data.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;


public class PSE {
    public static TreeMap<Integer,ElementAsset> table = new TreeMap<>();

    private PSE(){}

    public static ElementAsset getElement(int nr){
        return table.get(nr);
    }

    public static void createFromFile(String filepath){
        NamedPSE.DESERIALIZER.from(FileIO.read(filepath), NamedPSE.class).toAssetPSE();
    }

    public static void createFromAssets(String directoryPath){
        String[] fileDatas = FileIO.readDirectory(directoryPath);
        if (fileDatas == null) return;
        for(String data : fileDatas){
            ElementAsset e = JsonConverter.Deserializer.ELEMENT_ASSET.from(data, ElementAsset.class);
            table.put(e.getNumber(),e);
        }
    }

    static class NamedPSE {
        static final JsonConverter.Deserializer<NamedPSE> DESERIALIZER = new JsonConverter.Deserializer<>();
        LinkedHashMap<String, Element> elements = new LinkedHashMap<>();

        private void toAssetPSE(){
            for(Element e :elements.values()){
                table.put(e.number,e.toAsset());
            }
        }
    }
    static class Element {
        String modified;
        HashMap<String, StandardValueData> abundance;
        HashMap<String, String> appearance;
        StandardValueData atomic_mass;
        String basicity;
        String block;
        HashMap<String, ClassificationData[]> classification;
        int column;
        String crystal_structure;
        StandardValueData curie_point;
        ExtensiveValueData[] density;
        DiscoveryData discovery;
        ElasticityData elastic;
        HashMap<String, StandardValueData> electrical;
        String electron_config;
        HashMap<String, StandardValueData> enthalpy;
        String era;
        String goldschmidt;
        int group;
        HardnessData hardness;
        HazardData hazard;
        HashMap<String, ExtensiveValueData[]> heat;
        ImageData image;
        IonizationData ionization;
        String magnetic_ordering;
        StandardConditionData magnetic_susceptibility;
        MolarVolumeData molar_volume;
        HashMap<String,String> names;
        String natural_occurrence;
        StandardValueData neel_point;
        NegativityData negativity;
        int number;
        @JsonIgnore
        OpticalData optical;
        String oxidation_state;
        String oxide_character;
        int period;
        String phase;
        @JsonIgnore
        StandardValueData price;
        boolean radioactive;
        String radioactivity;
        String[] properties;
        HashMap<String, DeviableValueData> radius;
        String set;
        int[] shell;
        StandardConditionData[] sound_speed;
        AtomicWeightData standard_atomic_weight;
        StandardValueData standard_potential;
        String superconductivity;
        String symbol;
        HashMap<String, TemperatureData> temperature;
        ToxicityData[] toxicity;
        WebLinkData[] weblinks;
        HashMap<String,String> wiki;

        ElementAsset toAsset(){
            int valenceElectrons = calculateValenceElectrons();
            String nameEng = getEngDesc(names);
            return new ElementAsset(
                    convertAbundance(),
                    getEngDesc(appearance),
                    convertUnit(DataType.ATOMIC_MASS),
                    OrbitalShellBlocks.getOrbitalShellBlockFromString(block),
                    column,
                    group,
                    nameEng,
                    number,
                    oxidation_state,
                    phase,
                    BoundsList.get(nameEng.toLowerCase()),
                    properties,
                    radioactivity,
                    ElementSet.getElementSetFromString(set),
                    shell,
                    convertUnit(DataType.STANDARD_POTENTIAL),
                    symbol,
                    valenceElectrons
            );
        }

        private HashMap<AbundancePlace, UnitHolder> convertAbundance(){
            if(abundance == null) return null;
            HashMap<AbundancePlace,UnitHolder> converted = new HashMap<>();
            for(Map.Entry<String,StandardValueData> e : abundance.entrySet()){
                StandardValueData data = e.getValue();
                AbundancePlace abundancePlace = AbundancePlace.getAbundancePlaceFromString(e.getKey());
                if(abundancePlace != null){
                    converted.put(
                            abundancePlace,
                            new UnitHolder(
                                    data.value(),
                                    UnitTypes.getUnitFromString(data.unit().toLowerCase())
                                    ,data.info()
                            )
                    );
                }
            }
            return converted;
        }
        private int calculatePossibleBindings(int valenceElectrons){
            if (valenceElectrons < 0){
                return -1;
            }
            return 8-valenceElectrons;
        }
        private int calculateValenceElectrons(){
            if(group == 1 || group == 2){
                return group;
            } else if (group >=13 && group <= 18) {
                return group -10;
            }
            return -1;
        }

        private UnitHolder convertUnit(DataType dataType){
            switch (dataType){
                case ATOMIC_MASS -> {
                    if(atomic_mass == null) return null;
                    return new UnitHolder(
                            atomic_mass.value(),
                            UnitTypes.getUnitFromString((atomic_mass.unit())),
                            atomic_mass.info()
                    );
                }
                case STANDARD_POTENTIAL -> {
                    if(standard_potential == null) return null;
                    return new UnitHolder(
                            standard_potential.value(),
                            UnitTypes.getUnitFromString(standard_potential.unit()),
                            standard_potential.info()
                    );
                }
                case null, default -> {
                    return null;
                }
            }
        }

        private String getEngDesc(HashMap<String,String> lang){
            if(lang == null) return null;
            return lang.get("en");
        }
        private enum DataType{
            ATOMIC_MASS,
            STANDARD_POTENTIAL
        }
    }
    private static class BoundsList{
        private static final HashMap<String,Integer> bounds = new HashMap<>(120);

        static {
            importBounds();
        }

        private static void importBounds(){
            String boundsFile = FileIO.read("ChemGen/src/main/resources/ElementImport/Bounds.txt");
            if (boundsFile == null) throw new RuntimeException("Couldn't import ElementBound! Aborting ...");
            String[] boundsString = boundsFile.split("--");
            for(int i = 0; i< boundsString.length; i+=2){
                String key = boundsString[i].toLowerCase();
                String value = boundsString[i+1];
                int amount;
                if (value.startsWith("N/A")){
                    amount = -1;
                }else {
                    amount = Integer.parseInt(value);
                }
                //System.out.println(key+amount);
                bounds.put(key,amount);
            }
        }
        public static Integer get(String element){
            return bounds.get(element);
        }
    }
}
