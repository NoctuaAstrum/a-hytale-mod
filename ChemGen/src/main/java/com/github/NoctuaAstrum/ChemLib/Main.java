package com.github.NoctuaAstrum.ChemLib;

import com.github.NoctuaAstrum.ChemLib.core.FileIO;
import com.github.NoctuaAstrum.ChemLib.core.PSE;
import com.github.NoctuaAstrum.ChemLib.core.asset.element.ElementAsset;

public class Main {

    static void main(){
        importPSE();

        exportAllElementsAsAssets();
    }

    public static void importPSE(){
        PSE.createFromFile("ChemGen/src/main/resources/ElementImport/PSE.json");
    }
    public static void exportAllElementsAsAssets(){
        for(ElementAsset e : PSE.table.values()){
            String jsonAsset = ElementAsset.SERIALIZER.from(e);

            FileIO.write(jsonAsset, "ChemGen/src/main/resources/Elements/" + e.getName() + ".json");
        }
    }
}
