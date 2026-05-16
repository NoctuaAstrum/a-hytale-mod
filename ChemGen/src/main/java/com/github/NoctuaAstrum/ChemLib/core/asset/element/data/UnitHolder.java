package com.github.NoctuaAstrum.ChemLib.core.asset.element.data;

/*import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;*/

public class UnitHolder{

    /*public static final BuilderCodec<UnitHolder> CODEC = BuilderCodec.<UnitHolder>builder(
            UnitHolder.class,UnitHolder::new
            )
            .append(new KeyedCodec<>(
                    "Value",
                    Codec.FLOAT
            ),
                    (unitHolder,value)-> unitHolder.value = value,
                    (unitHolder) -> unitHolder.value
            )
            .add()
            .append(new KeyedCodec<>(
                            "UnitType",
                            UnitTypes.CODEC
                    ),
                    (unitHolder,unitType)-> unitHolder.unitType = unitType,
                    (unitHolder) -> unitHolder.unitType
            )
            .add()
            .append(new KeyedCodec<>(
                            "AdditionalInfo",
                            Codec.STRING
                    ),
                    (unitHolder,info)-> unitHolder.additionalInfo = info,
                    (unitHolder) -> unitHolder.additionalInfo
            )
            .add()
            .build();*/


    float value;
    UnitTypes unitType;
    String additionalInfo;

    public UnitHolder(float value, UnitTypes unitType, String additionalInfo) {
        this.value = value;
        this.unitType = unitType;
        this.additionalInfo = additionalInfo;
    }

    public UnitHolder(){}

    @Override
    public String toString(){
        return "{"+
            "Value:"+value+
            ",UnitType:\""+unitType+
            "\",AdditionalInfo:\""+additionalInfo+
            "\"}";
    }
}