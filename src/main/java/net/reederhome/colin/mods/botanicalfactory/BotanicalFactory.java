package net.reederhome.colin.mods.botanicalfactory;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.recipe.RecipePetals;

@Mod(name = "Botanical Factory", modid = BotanicalFactory.MODID, dependencies = "required-after:Botania")
public class BotanicalFactory {
	public static final String MODID = "botanicalfactory";
	
	public static LexiconEntry entryChopperhock;
	public static LexiconEntry entryDecayfeather;
	public static LexiconEntry entryFloatus;
	public static LexiconEntry entryOpenChest;

	public static Block openChest;

	public static FactoryCreativeTab tab = new FactoryCreativeTab();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		registerSubTiles();
		setupBlocksAndItems();
		setupCraftingAndLexiconEntries();
	}

	public void addSubTileToTab(String name) {
		tab.addStack(BotaniaAPI.internalHandler.getSubTileAsStack(name));
	}
	
	public void registerSubTiles() {
		BotaniaAPI.registerSubTile("chopperhock", SubTileChopperhock.class);
		addSubTileToTab("chopperhock");
		BotaniaAPI.registerSubTile("decayfeather", SubTileDecayfeather.class);
		addSubTileToTab("decayfeather");
		BotaniaAPI.registerSubTile("floatus", SubTileFloatus.class);
		addSubTileToTab("floatus");
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
		IRecipe recipeOpenChest = new ShapedOreRecipe(openChest,
				"lll",
				"lcl",
				"ldl",
				'l', "livingwood",
				'c', "chestWood",
				'd', Blocks.dropper);
		GameRegistry.addRecipe(recipeOpenChest);

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

		entryOpenChest = new FactoryLexiconEntry("tile.openChest.name", "tile.openChest", BotaniaAPI.categoryDevices);
		entryOpenChest.addPage(BotaniaAPI.internalHandler.textPage("botanicalfactory.lexicon.tile.openChest.1"));
		entryOpenChest.addPage(BotaniaAPI.internalHandler.craftingRecipePage("botanicalfactory.lexicon.tile.openChest.crafting", recipeOpenChest));
		entryOpenChest.setIcon(new ItemStack(openChest));
		entryOpenChest.setKnowledgeType(BotaniaAPI.basicKnowledge);
	}

	public void setupBlocksAndItems() {
		openChest = new BlockOpenChest();
		GameRegistry.registerBlock(openChest, "openChest");
		GameRegistry.registerTileEntity(TileEntityOpenChest.class, "OpenChest");
	}
}
