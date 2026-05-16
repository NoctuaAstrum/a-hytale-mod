package com.github.NoctuaAstrum.ChemLib.core.asset.element;


/*import com.hypixel.hytale.assetstore.AssetExtraInfo;
import com.hypixel.hytale.assetstore.AssetRegistry;
import com.hypixel.hytale.assetstore.AssetStore;
import com.hypixel.hytale.assetstore.codec.AssetBuilderCodec;
import com.hypixel.hytale.assetstore.map.DefaultAssetMap;
import com.hypixel.hytale.assetstore.map.JsonAssetWithMap;
import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.codecs.map.EnumMapCodec;

import javax.annotation.Nullable;*/
import com.github.NoctuaAstrum.ChemLib.core.JsonConverter;
import com.github.NoctuaAstrum.ChemLib.core.asset.element.data.UnitHolder;
import com.github.NoctuaAstrum.ChemLib.core.asset.element.enums.*;


import java.util.Arrays;
import java.util.Map;

public class ElementAsset /*implements JsonAssetWithMap<String, DefaultAssetMap<String,ElementAsset>>*/ {
    public static final JsonConverter.Serializer<ElementAsset>  SERIALIZER = new JsonConverter.Serializer<>();
    public static final String DEFAULT_ID = "Default";
    /*private static AssetStore<String, ElementAsset, DefaultAssetMap<String, ElementAsset>> ASSET_STORE;

    public static final AssetBuilderCodec<String,ElementAsset> CODEC = AssetBuilderCodec.<String,ElementAsset>builder(
            ElementAsset.class,
            ElementAsset::new,
                    Codec.STRING,
                    (element, id) -> element.id = id,
                    element -> element.id,
                    (element, data) -> element.data = data,
                    element -> element.data
            )
            .append(new KeyedCodec<>(
                    "Abundance",
                    new EnumMapCodec<>(AbundancePlace.class,UnitHolder.CODEC)),
                    (element,abundance)-> element.abundance = abundance,
                    (element) -> element.abundance
            )
            .add()
            .append(new KeyedCodec<>(
                            "Appearance",
                            Codec.STRING
                    ),
                    (element,appearance)-> element.appearance = appearance,
                    (element) -> element.appearance
            )
            .add()
            .append(new KeyedCodec<>(
                            "AtomicMass",
                            UnitHolder.CODEC
                    ),
                    (element,atomicMass)-> element.atomicMass = atomicMass,
                    (element) -> element.atomicMass
            )
            .add()
            .build();


    private AssetExtraInfo.Data data;*/
    private String id;


    private Map<AbundancePlace, UnitHolder> abundance;
    private String appearance;
    private UnitHolder atomicMass = new UnitHolder();
    private OrbitalShellBlocks orbitalShellBlock;
    private int column;
    //private HashMap<String, UnitHolder> electrical; //maybe later down the drain
    //private String electron_config; //maybe later down the drain
    private int group;
    //private HazardData hazard; //maybe later down the drain
    private String name;
    private int number;
    private String oxidationState;
    //private String oxide_character; //maybe later down the drain
    private String phase;
    private int possibleBounds;
    private String[] properties;
    private String radioactivity;
    private ElementSet set;
    private int[] shell;
    private UnitHolder standardPotential = new UnitHolder();
    private String symbol;
    private int valenceElectrons;

    public ElementAsset(Map<AbundancePlace,
                            UnitHolder> abundance,
                        String appearance,
                        UnitHolder atomicMass,
                        OrbitalShellBlocks orbitalShellBlock,
                        int column,
                        int group,
                        String name,
                        int number,
                        String oxidationState,
                        String phase,
                        int possibleBounds,
                        String[] properties,
                        String radioactivity,
                        ElementSet set,
                        int[] shell,
                        UnitHolder standardPotential,
                        String symbol,
                        int valenceElectrons) {

        this.abundance = abundance;
        this.appearance = appearance;
        this.atomicMass = atomicMass;
        this.orbitalShellBlock = orbitalShellBlock;
        this.column = column;
        this.group = group;
        this.name = name;
        this.number = number;
        this.oxidationState = oxidationState;
        this.phase = phase;
        this.possibleBounds = possibleBounds;
        this.properties = properties;
        this.radioactivity = radioactivity;
        this.set = set;
        this.shell = shell;
        this.standardPotential = standardPotential;
        this.symbol = symbol;
        this.valenceElectrons = valenceElectrons;
    }
    public ElementAsset(){}

    public String getName() {
        return name;
    }

    public Map<AbundancePlace, UnitHolder> getAbundance() {
        return abundance;
    }

    public String getAppearance() {
        return appearance;
    }

    public UnitHolder getAtomicMass() {
        return atomicMass;
    }

    public OrbitalShellBlocks getOrbitalShellBlock() {
        return orbitalShellBlock;
    }

    public int getColumn() {
        return column;
    }

    public int getGroup() {
        return group;
    }

    public int getNumber() {
        return number;
    }

    public String getOxidationState() {
        return oxidationState;
    }

    public String getPhase() {
        return phase;
    }

    public int getPossibleBounds() {
        return possibleBounds;
    }

    public String[] getProperties() {
        return properties;
    }

    public String getRadioactivity() {
        return radioactivity;
    }

    public ElementSet getSet() {
        return set;
    }

    public int[] getShell() {
        return shell;
    }

    public UnitHolder getStandardPotential() {
        return standardPotential;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getValenceElectrons() {
        return valenceElectrons;
    }

    /*public static AssetStore<String, ElementAsset, DefaultAssetMap<String, ElementAsset>> getAssetStore() {
        if (ASSET_STORE == null) {
            ASSET_STORE = AssetRegistry.getAssetStore(ElementAsset.class);
        }
        return ASSET_STORE;
    }

    @Nullable
    public static DefaultAssetMap<String, ElementAsset> getAssetMap() {
        AssetStore<String, ElementAsset, DefaultAssetMap<String, ElementAsset>> store = getAssetStore();
        if (store == null) {
            return null;
        }
        return store.getAssetMap();
    }

    @Nullable
    public static ElementAsset getDefaultAsset() {
        DefaultAssetMap<String, ElementAsset> assetMap = getAssetMap();
        if (assetMap == null) {
            return null;
        }
        return assetMap.getAsset(DEFAULT_ID);
    }

    @Override
    public String getId() {
        return id;
    }*/

    @Override
    public String toString(){
        return "{"+
                "Id="+id+
                ",Abundance="+abundance+
                ",Appearance=\""+appearance+
                "\",AtomicMass="+atomicMass.toString()+
                ",OrbitalShellBlock=\""+orbitalShellBlock+
                "\",Column."+column+
                ",Group="+group+
                ",Name=\""+name+
                "\",Number="+number+
                ",OxidationState=\""+ oxidationState +
                "\",Phase=\""+phase+
                "\",PossibleBounds="+possibleBounds+
                ",Properties="+Arrays.toString(properties) +
                ",Radioactivity=\""+radioactivity+
                "\",Set=\""+set+
                "\",Shell="+Arrays.toString(shell) +
                ",StandardPotential="+ standardPotential.toString() +
                ",Symbol=\""+symbol+
                "\",ValenceElectrons="+valenceElectrons+
                "}";
    }
}
