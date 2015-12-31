package net.reederhome.colin.mods.botanicalfactory;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.IAddonEntry;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;

public class FactoryLexiconEntry extends LexiconEntry implements IAddonEntry {
	
	private String tinyName;
	
	public FactoryLexiconEntry(String name, LexiconCategory category) {
		this("tile.botania:"+name+".name", name, category);
	}

	public FactoryLexiconEntry(String name, String tinyName, LexiconCategory category) {
		super(name, category);
		this.tinyName = tinyName;
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