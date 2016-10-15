package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.ItemHammerMana;
import com.brightspark.sparkshammers.reference.Names;

/**
 * @author CreeperShift
 * com.brightspark.sparkshammers.init
 * Oct 15, 2016
 */

/**
 * 
 * Seperate class to register Botania tools because it now requires Botania to
 * not crash. This way the ItemHammerMana class isn't loaded when Botania is not
 * there
 * 
 */

public class SHItemsBotania {

	public static void registerManasteel() {

		SHItems.regItem(SHModItems.hammerManasteel = new ItemHammerMana(Names.EnumMaterials.MANASTEEL));
		SHItems.regItem(SHModItems.excavatorManasteel = new ItemHammerMana(Names.EnumMaterials.MANASTEEL, true));
	}

	public static void registerTerrasteel() {
		SHItems.regItem(SHModItems.hammerTerrasteel = new ItemHammerMana(Names.EnumMaterials.TERRASTEEL));
		SHItems.regItem(SHModItems.excavatorTerrasteel = new ItemHammerMana(Names.EnumMaterials.TERRASTEEL, true));

	}

	public static void registerElementium() {
		SHItems.regItem(SHModItems.hammerElementium = new ItemHammerMana(Names.EnumMaterials.ELEMENTIUM));
		SHItems.regItem(SHModItems.excavatorElementium = new ItemHammerMana(Names.EnumMaterials.ELEMENTIUM, true));

	}

}
