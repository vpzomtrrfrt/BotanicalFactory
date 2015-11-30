package net.reederhome.colin.mods.botanicalfactory;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.recipe.RecipePetals;

@Mod(name = "Botanical Factory", modid = BotanicalFactory.MODID, dependencies = "required-after:Botania")
public class BotanicalFactory {
	public static final String MODID = "botanicalfactory";
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		registerSubTiles();
		setupCraftingAndLexiconEntries();
	}
	
	public void registerSubTiles() {
		BotaniaAPI.registerSubTile("chopperhock", SubTileChopperhock.class);
		BotaniaAPI.addSubTileToCreativeMenu("chopperhock");
	}
	
	public void setupCraftingAndLexiconEntries() {
		RecipePetals recipeChopperhock = BotaniaAPI.registerPetalRecipe(BotaniaAPI.internalHandler.getSubTileAsStack("chopperhock"),
					"petalLime",
					"petalGreen",
					"petalWhite",
					"manaPetalLime",
					"manaPetalBrown",
					"runeSpringB",
					"redstoneRoot",
					"elvenPixieDust"
				);
		LexiconEntry entry = new LexiconEntry("Chopperhock", BotaniaAPI.categoryFunctionalFlowers);
		entry.addPage(BotaniaAPI.internalHandler.textPage("botanicalfactory.lexicon.chopperhock.1"));
		entry.addPage(BotaniaAPI.internalHandler.petalRecipePage("botanicalfactory.lexicon.chopperhock.crafting", recipeChopperhock));
		entry.setIcon(BotaniaAPI.internalHandler.getSubTileAsStack("chopperhock"));
		entry.setKnowledgeType(BotaniaAPI.elvenKnowledge);
		BotaniaAPI.addEntry(entry, BotaniaAPI.categoryFunctionalFlowers);
	}
}