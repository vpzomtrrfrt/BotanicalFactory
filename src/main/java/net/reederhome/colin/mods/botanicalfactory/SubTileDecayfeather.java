package net.reederhome.colin.mods.botanicalfactory;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.util.ChunkCoordinates;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.api.lexicon.LexiconEntry;

public class SubTileDecayfeather extends SubTileFunctional {
	private static final int RADIUS = 4;
	private static final int DELAY = 4;
	private static final int MANA_USE = 2;
	
	@Override
	public boolean acceptsRedstone() {
		return true;
	}

	@Override
	public LexiconEntry getEntry() {
		return BotanicalFactory.entryDecayfeather;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(redstoneSignal > 0 || mana == 0 || ticksExisted % DELAY > 0) return;
		ArrayList<ChunkCoordinates> poss = new ArrayList<ChunkCoordinates>();
		for(int x = -RADIUS; x <= RADIUS; x++) {
			int cx = supertile.xCoord+x;
			for(int z = -RADIUS; z <= RADIUS; z++) {
				int cz = supertile.zCoord+z;
				int cy = supertile.yCoord;
				while(true) {
					if(cy>255) {
						break;
					}
					Block b = supertile.getWorldObj().getBlock(cx, cy, cz);
					if(b instanceof BlockLeavesBase) {
						poss.add(new ChunkCoordinates(cx, cy, cz));
						break;
					}
					cy++;
				}
			}
		}
		if(poss.size()>0) {
			ChunkCoordinates cc = poss.get(new Random().nextInt(poss.size()));
			supertile.getWorldObj().getBlock(cc.posX, cc.posY, cc.posZ).updateTick(supertile.getWorldObj(), cc.posX, cc.posY, cc.posZ, new Random());
			mana -= MANA_USE;
		}
	}
	
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RADIUS);
	}
}
