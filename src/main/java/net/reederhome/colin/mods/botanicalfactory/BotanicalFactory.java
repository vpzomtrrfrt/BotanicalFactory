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
	
	public static LexiconEntry entryChopperhock;
	
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
		entryChopperhock = new FactoryLexiconEntry("flower.chopperhock", BotaniaAPI.categoryFunctionalFlowers);
		entryChopperhock.addPage(BotaniaAPI.internalHandler.textPage("botanicalfactory.lexicon.flower.chopperhock.1"));
		entryChopperhock.addPage(BotaniaAPI.internalHandler.petalRecipePage("botanicalfactory.lexicon.flower.chopperhock.crafting", recipeChopperhock));
		entryChopperhock.setIcon(BotaniaAPI.internalHandler.getSubTileAsStack("chopperhock"));
		entryChopperhock.setKnowledgeType(BotaniaAPI.elvenKnowledge);
		BotaniaAPI.addEntry(entryChopperhock, BotaniaAPI.categoryFunctionalFlowers);
	}
}