package com.github.NoctuaAstrum.ChemLib.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.NoctuaAstrum.ChemLib.core.asset.element.ElementAsset;
import com.github.NoctuaAstrum.ChemLib.core.asset.element.data.UnitHolder;
import com.github.NoctuaAstrum.ChemLib.core.asset.element.data.UnitTypes;
import com.github.NoctuaAstrum.ChemLib.core.asset.element.enums.AbundancePlace;
import com.github.NoctuaAstrum.ChemLib.core.asset.element.enums.ElementSet;
import com.github.NoctuaAstrum.ChemLib.core.asset.element.enums.OrbitalShellBlocks;
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

    public static String asString(){
        StringBuilder sb = new StringBuilder("PSE{\n");
        for(ElementAsset e: table.values()){
            sb.append(e.getSymbol());
            sb.append(":\n");
            sb.append(e);
        }
        sb.append("}");
        return sb.toString();
    }
    public static class NamedPSE {
        public static final JsonConverter.Deserializer<NamedPSE> DESERIALIZER = new JsonConverter.Deserializer<>();
        public LinkedHashMap<String, Element> elements = new LinkedHashMap<>();

        private void toAssetPSE(){
            for(Element e :elements.values()){
                table.put(e.number,e.toAsset());
            }
        }
    }
    public static class Element {
        public String modified;
        public HashMap<String, StandardValueData> abundance;
        public HashMap<String, String> appearance;
        public StandardValueData atomic_mass;
        public String basicity;
        public String block;
        public HashMap<String, ClassificationData[]> classification;
        public int column;
        public String crystal_structure;
        public StandardValueData curie_point;
        public ExtensiveValueData[] density;
        public DiscoveryData discovery;
        public ElasticityData elastic;
        public HashMap<String, StandardValueData> electrical;
        public String electron_config;
        public HashMap<String, StandardValueData> enthalpy;
        public String era;
        public String goldschmidt;
        public int group;
        public HardnessData hardness;
        public HazardData hazard;
        public HashMap<String, ExtensiveValueData[]> heat;
        public ImageData image;
        public IonizationData ionization;
        public String magnetic_ordering;
        public StandardConditionData magnetic_susceptibility;
        public MolarVolumeData molar_volume;
        public HashMap<String,String> names;
        public String natural_occurrence;
        public StandardValueData neel_point;
        public NegativityData negativity;
        public int number;
        @JsonIgnore
        public OpticalData optical;
        public String oxidation_state;
        public String oxide_character;
        public int period;
        public String phase;
        @JsonIgnore
        public StandardValueData price;
        public boolean radioactive;
        public String radioactivity;
        public String[] properties;
        public HashMap<String, DeviableValueData> radius;
        public String set;
        public int[] shell;
        public StandardConditionData[] sound_speed;
        public AtomicWeightData standard_atomic_weight;
        public StandardValueData standard_potential;
        public String superconductivity;
        public String symbol;
        public HashMap<String, TemperatureData> temperature;
        public ToxicityData[] toxicity;
        public WebLinkData[] weblinks;
        public HashMap<String,String> wiki;

        public ElementAsset toAsset(){
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
            } else if (group >=3 && group <= 18) {
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
    public static class BoundsList{
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
