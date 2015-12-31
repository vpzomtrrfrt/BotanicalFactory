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
	public static LexiconEntry entryDecayfeather;
	public static LexiconEntry entryFloatus;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		registerSubTiles();
		setupCraftingAndLexiconEntries();
	}
	
	public void registerSubTiles() {
		BotaniaAPI.registerSubTile("chopperhock", SubTileChopperhock.class);
		BotaniaAPI.addSubTileToCreativeMenu("chopperhock");
		BotaniaAPI.registerSubTile("decayfeather", SubTileDecayfeather.class);
		BotaniaAPI.addSubTileToCreativeMenu("decayfeather");
		BotaniaAPI.registerSubTile("floatus", SubTileFloatus.class);
		BotaniaAPI.addSubTileToCreativeMenu("floatus");
	}
	
	public void setupCraftingAndLexiconEntries() {
		RecipePetals recipeChopperhock = BotaniaAPI.registerPetalRecipe(BotaniaAPI.internalHandler.getSubTileAsStack("chopperhock"),
				"petalLime",
				"petalLime",
				"petalGreen",
				"petalWhite",
				"petalBrown",
				"powderMana",
				"runeSpringB",
				"redstoneRoot",
				"elvenPixieDust");
		RecipePetals recipeDecayfeather = BotaniaAPI.registerPetalRecipe(BotaniaAPI.internalHandler.getSubTileAsStack("decayfeather"),
				"petalPurple",
				"petalGreen",
				"petalGreen",
				"petalBrown",
				"powderMana",
				"runeAutumnB",
				"redstoneRoot",
				"elvenPixieDust");
		RecipePetals recipeFloatus = BotaniaAPI.registerPetalRecipe(BotaniaAPI.internalHandler.getSubTileAsStack("floatus"),
				"petalPurple",
				"petalPurple",
				"petalWhite",
				"petalGreen",
				"powderMana",
				"runeAirB",
				"redstoneRoot",
				"elvenPixieDust");
		entryChopperhock = new FactoryLexiconEntry("flower.chopperhock", BotaniaAPI.categoryFunctionalFlowers);
		entryChopperhock.addPage(BotaniaAPI.internalHandler.textPage("botanicalfactory.lexicon.flower.chopperhock.1"));
		entryChopperhock.addPage(BotaniaAPI.internalHandler.petalRecipePage("botanicalfactory.lexicon.flower.chopperhock.crafting", recipeChopperhock));
		entryChopperhock.setIcon(BotaniaAPI.internalHandler.getSubTileAsStack("chopperhock"));
		entryChopperhock.setKnowledgeType(BotaniaAPI.elvenKnowledge);

		entryDecayfeather = new FactoryLexiconEntry("flower.decayfeather", BotaniaAPI.categoryFunctionalFlowers);
		entryDecayfeather.addPage(BotaniaAPI.internalHandler.textPage("botanicalfactory.lexicon.flower.decayfeather.1"));
		entryDecayfeather.addPage(BotaniaAPI.internalHandler.petalRecipePage("botanicalfactory.lexicon.flower.decayfeather.crafting", recipeDecayfeather));
		entryDecayfeather.setIcon(BotaniaAPI.internalHandler.getSubTileAsStack("decayfeather"));
		entryDecayfeather.setKnowledgeType(BotaniaAPI.elvenKnowledge);

		entryFloatus = new FactoryLexiconEntry("flower.floatus", BotaniaAPI.categoryFunctionalFlowers);
		entryFloatus.addPage(BotaniaAPI.internalHandler.textPage("botanicalfactory.lexicon.flower.floatus.1"));
		entryFloatus.addPage(BotaniaAPI.internalHandler.petalRecipePage("botanicalfactory.lexicon.flower.floatus.crafting", recipeFloatus));
		entryFloatus.setIcon(BotaniaAPI.internalHandler.getSubTileAsStack("floatus"));
		entryFloatus.setKnowledgeType(BotaniaAPI.elvenKnowledge);
	}
}
