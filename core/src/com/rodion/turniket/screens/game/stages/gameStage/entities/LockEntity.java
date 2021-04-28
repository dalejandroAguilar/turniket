package com.rodion.turniket.screens.game.stages.gameStage.entities;

import com.rodion.turniket.basics.AnimatedEntity;
import com.rodion.turniket.utilities.AssetManagerMaster;

public class LockEntity extends AnimatedEntity {
    public LockEntity() {
        super(.02f);
        prepareAssets();
    }

       @Override
    public void setAssetAddress() {
        super.setAssetAddress();
        setAssetManager(AssetManagerMaster.game);
        assetPath = "game";

        assetNames = new String[16];
        assetNames[0]  = "lock01";
        assetNames[1]  = "lock02";
        assetNames[2]  = "lock03";
        assetNames[3]  = "lock04";
        assetNames[4]  = "lock05";
        assetNames[5]  = "lock06";
        assetNames[6]  = "lock07";
        assetNames[7]  = "lock08";
        assetNames[8]  = "lock09";
        assetNames[9]  = "lock10";
        assetNames[10] = "lock11";
        assetNames[11] = "lock12";
        assetNames[12] = "lock13";
        assetNames[13] = "lock14";
        assetNames[14] = "lock15";
        assetNames[15] = "lock16";
//        assetNames[16] = "lock17";
    }

//    on

}
