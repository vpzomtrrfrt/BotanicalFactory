package net.reederhome.colin.mods.botanicalfactory;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.IAddonEntry;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;

public class FactoryLexiconEntry extends LexiconEntry implements IAddonEntry {
	
	private String tinyName;
	
	public FactoryLexiconEntry(String name, LexiconCategory category) {
		super("tile.botania:"+name+".name", category);
		tinyName = name;
		BotaniaAPI.addEntry(this, category);
	}
	
	@Override
	public String getSubtitle() {
		return "["+BotanicalFactory.MODID+"]";
	}
	
	@Override
	public String getTagline() {
		return "botanicalfactory.lexicon."+tinyName+".tagline";
	}
}